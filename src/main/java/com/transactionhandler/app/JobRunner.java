package com.transactionhandler.app;

import java.util.Date;

import javax.sql.DataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.transactionhandler.config.BatchConfig;
import com.transactionhandler.config.BatchScheduler;
import com.transactionhandler.dal.*;
import com.transactionhandler.dom.AccountTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;

@SpringBootApplication
// @EnableScheduling
@Import({ BatchConfig.class })
public class JobRunner implements CommandLineRunner {

	@Autowired
	SimpleJobLauncher jobLauncher;

	@Autowired
	Job job;

	private static Log logger = LogFactory.getLog(JobRunner.class);

	public static void main(String[] args) throws Exception {

		SpringApplication.run(JobRunner.class, args);

	}



	@Override
	public void run(String... args) throws Exception {
			int exitCode = 0;
			try {
				JobParameters params = new JobParametersBuilder()
						.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
				jobLauncher.run(job, params);
			} catch (Exception e) {
				logger.error("Encountered exception.", e);
				exitCode = -1;
			}
			System.exit(exitCode);
	
	}

	

}
