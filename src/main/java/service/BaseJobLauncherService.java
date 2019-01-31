package service;

import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNumeric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;

import gov.irs.irsm.events.core.EventExecutor;
import gov.irs.irsm.events.core.EventFactory;
import gov.irs.irsm.events.core.IRSException;
import gov.irs.irsm.events.core.exception.InvalidDataException;

/**
 * The base job launcher service. 
 * This will launch spring batch job and return the status.
 */
public abstract class BaseJobLauncherService {

	//private static final EventExecutor EXECUTOR = EventFactory.getExecutor(BaseJobLauncherService.class);
	
	protected static final String JOB_EXECUTION_ERROR_MSG = "An error occurred executing the job";
	protected static final String MISSING_REQUIRED_ARGUMENTS_ERROR_MSG = "Missing required arguments";
	protected static final String TAX_YEAR_ARGUMENT_INDEX_INVALID_ERROR_MSG = "Tax year argument is missing at index";
	protected static final String TAX_YEAR_ARGUMENT_INVALID_ERROR_MSG = "Tax year must be four digits";
	protected static final String MAX_RECORDS_INVALID_ERROR_MSG = "Max records must be greater than zero";
	protected static final String MAX_RECORDS_ARGUMENT_INDEX_INVALID_ERROR_MSG = "Max records argument is missing at index";
	protected static final String NUMERIC_INVALID_ERROR_MSG = "{0} is not numeric";
	protected static final String MISSING_ARGUMENT_INDEX_ERROR_MSG = "{0} argument is missing at index";
	protected static final String NUMERIC_UNDER_MIN_VALUE_ERROR_MSG = "{0} is less than minimum value";
	protected static final String DATE_INVALID_FORMAT_ERROR_MSG = "{0} has an invalid date format";
	protected static final int MIN_MAX_RECORDS_SIZE = 1;
	protected static final String MAX_RECORDS_ARG_NAME_FOR_ERROR_MSG = "Max records";
	protected static final String CHUNK_SIZE_ARG_NAME_FOR_ERROR_MSG = "Chunk size";
	protected static final int MIN_CHUNK_SIZE = 1;
	protected static final String DATE_FORMAT = "MMddyyyyHH";
	
	private final JobLauncher jobLauncher;
	private final ApplicationContext applicationContext;
	private final AtomicInteger exitCode = new AtomicInteger(1);
	
	/**
	 * @param jobLauncherIn the spring batch job launcher
	 * @param applicationContextIn the spring application context
	 */
	public BaseJobLauncherService(final JobLauncher jobLauncherIn, final ApplicationContext applicationContextIn) {
		
		jobLauncher = jobLauncherIn;
		applicationContext = applicationContextIn;
	}

	/**
	 * This will launch a spring batch job with job parameters.
	 * 
	 * @param jobArguments the array of job arguments used to create the job parameters
	 * @return a zero for success else one for failure
	 */
	public int launch(final String[] jobArguments) {
		
		try {

			if(missingRequiredJobArguments(jobArguments)) {
				throw new Exception("Missing Arguments");
			}
			JobParameters jobParameters = buildJobParameters(jobArguments);

			try {
				
				EXECUTOR.info("Running {} Job", getJobName());
				Job job = applicationContext.getBean(Job.class);
				Instant start = Instant.now();
				JobExecution jobExecution = jobLauncher.run(job, jobParameters);
				Instant end = Instant.now();
				long elapsedTime = Duration.between(start, end).getSeconds();
				ExitStatus exitStatus = jobExecution.getExitStatus();
				
				if(isExitStatusComplete(exitStatus)) {
					exitCode.set(0);
				}
				
				EXECUTOR.info("{} Job Done. Exit Code: {}, Elapsed Time: {} seconds",
						getJobName(),
						exitCode,
						elapsedTime);
			} catch(Exception e) {
				throw EXECUTOR.processingException(JOB_EXECUTION_ERROR_MSG, e);
			}
		} catch(Exception e) {
			
			if(e instanceof InvalidDataException) {
				EXECUTOR.info(usageMessage());
			}
		}
		
		return exitCode.get();
	}
	
	/**
	 * @return the name of the job used for logging
	 */
	protected abstract String getJobName();
	
	/**
	 * Builds the job parameters used to launch the job with
	 * @param jobArguments the arguments containing job parameter values
	 * @return the job parameters to launch the job with
	 * @throws IRSException thrown if an error occurs constructing the job parameters
	 */
	protected abstract JobParameters doBuildJobParameters(final String[] jobArguments) throws Exception;
	
	/**
	 * @return the expected number of job parameters, if not met an IRSException will be thrown
	 */
	protected abstract int expectedJobParameterCount();
	
	/**
	 * @return usage message to display if there are missing job parameters
	 */
	protected abstract String usageMessage();
	
	/**
	 * @param jobArguments an array of job arguments
	 * @param taxYearArgumentIndex the index of tax year
	 * @return job parameter with the tax year
	 * @throws IRSException either the argument is missing or invalid tax year
	 */
	protected JobParameter parseTaxYearJobArgument(final String[] jobArguments, final int taxYearArgumentIndex) throws IRSException {
		
        if(doesNotContainIndex(jobArguments, taxYearArgumentIndex)) {
        	throw EXECUTOR.invalidDataException(TAX_YEAR_ARGUMENT_INDEX_INVALID_ERROR_MSG);
        }
		
        String taxYear = jobArguments[taxYearArgumentIndex].trim();
		if(taxYear.length() != 4 || !isNumeric(taxYear)) {
            throw EXECUTOR.invalidDataException(TAX_YEAR_ARGUMENT_INVALID_ERROR_MSG);
        }
        
        return new JobParameter(Long.parseLong(taxYear));
	}

	/**
	 * @param jobArguments an array of job arguments
	 * @param maxRecordsArgumentIndex index of max records
	 * @return job parameter with the max records
	 * @throws IRSException either the argument is missing or invalid max records
	 */
	protected JobParameter parseMaxRecordsJobArgument(
			final String[] jobArguments,
			final int maxRecordsArgumentIndex) throws IRSException {

		return parseNumericJobArgument(
				jobArguments, 
				maxRecordsArgumentIndex, 
				MIN_MAX_RECORDS_SIZE,
				MAX_RECORDS_ARG_NAME_FOR_ERROR_MSG);
	}
	
	/**
	 * @param jobArguments an array of job arguments
	 * @param chunkSizeArgumentIndex index of chunk size
	 * @return job parameter with chunk size
	 * @throws IRSException either the argument is missing or invalid chunk size
	 */
	protected JobParameter parseChunkSizeJobArgument(
			final String[] jobArguments, 
			final int chunkSizeArgumentIndex) throws IRSException {
		
		return parseNumericJobArgument(
				jobArguments, 
				chunkSizeArgumentIndex, 
				MIN_CHUNK_SIZE, 
				CHUNK_SIZE_ARG_NAME_FOR_ERROR_MSG);
	}
	
	/**
	 * @param jobArguments an array of job arguments
	 * @param argumentIndex index of argument
	 * @param minValue the minimum value the argument must be
	 * @param argNameForErrorMessage name of the argument
	 * @return job parameter with number
	 * @throws IRSException either the argument is missing, value is less than minimum value, or invalid number
	 */
	protected JobParameter parseNumericJobArgument(
			final String[] jobArguments, 
			final int argumentIndex, 
			final int minValue,
			final String argNameForErrorMessage) throws IRSException {
		
		if(doesNotContainIndex(jobArguments, argumentIndex)) {
        	throw EXECUTOR.invalidDataException(format(MISSING_ARGUMENT_INDEX_ERROR_MSG, argNameForErrorMessage));
        }
		
		String numericArgStr = jobArguments[argumentIndex].trim();
		if(!isNumeric(numericArgStr)) {
			throw EXECUTOR.invalidDataException(format(NUMERIC_INVALID_ERROR_MSG, argNameForErrorMessage));
		}
		
		long numericArg = Long.parseLong(numericArgStr);
		if(numericArg < minValue) {
			throw EXECUTOR.invalidDataException(format(NUMERIC_UNDER_MIN_VALUE_ERROR_MSG, argNameForErrorMessage));
		}
        
		return new JobParameter(numericArg);
	}
	
	/**
	 * @param jobArguments an array of job arguments
	 * @param dateArgumentIndex index of date
	 * @param argNameForErrorMessage name of the argument
	 * @return job parameter with the date
	 * @throws IRSException either the argument is missing or invalid date format
	 */
	protected JobParameter parseDateJobArgument(
			final String[] jobArguments, 
			final int dateArgumentIndex, 
			final String argNameForErrorMessage) throws IRSException {
		
		if(doesNotContainIndex(jobArguments, dateArgumentIndex)) {
        	throw EXECUTOR.invalidDataException(format(MISSING_ARGUMENT_INDEX_ERROR_MSG, argNameForErrorMessage));
		}
		
		try {
			Date date = new SimpleDateFormat(DATE_FORMAT).parse(jobArguments[dateArgumentIndex]);
			return new JobParameter(date);
		} catch (ParseException e) {
			throw EXECUTOR.invalidDataException(format(DATE_INVALID_FORMAT_ERROR_MSG, argNameForErrorMessage));
		}
	}
	
	/**
	 * @param jobArguments
	 *            an array of job arguments
	 * @param stringArgumentIndex
	 *            index of the string
	 * @param argNameForErrorMessage
	 *            name of the argument
	 * @return job parameter with the string
	 * @throws IRSException
	 *             either the argument is missing or invalid
	 */
	protected JobParameter parseStringJobArgument(
			final String[] jobArguments, 
			final int stringArgumentIndex,
			final String argNameForErrorMessage) throws IRSException {

		if (doesNotContainIndex(jobArguments, stringArgumentIndex)) {
			throw EXECUTOR.invalidDataException(format(MISSING_ARGUMENT_INDEX_ERROR_MSG, argNameForErrorMessage));
		}

		return new JobParameter(jobArguments[stringArgumentIndex]);
	}
	
	private boolean missingRequiredJobArguments(final String[] jobArguments) {
		return jobArguments.length < expectedJobParameterCount();
	}

	private JobParameters buildJobParameters(final String[] jobArguments) throws Exception {
		return doBuildJobParameters(jobArguments);
	}

	private boolean doesNotContainIndex(final String[] jobArguments, final int argumentIndex) {
		return jobArguments.length == 0 || jobArguments.length < argumentIndex;
	}

	private boolean isExitStatusComplete(final ExitStatus exitStatus) {
		return ExitStatus.COMPLETED.getExitCode().equals(exitStatus.getExitCode());
	}

}
