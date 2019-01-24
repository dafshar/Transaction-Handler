package app;



import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.batch.core.launch.support.JvmSystemExiter;
import org.springframework.batch.core.launch.support.SystemExiter;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import config.BatchConfig;

public class JobRunner {
	

    @Autowired
	
    public JobLauncher jobLauncher;
      
  
	
	public static void main(String[] args) throws Exception {
		
		   CommandLineJobRunner.main(
		            new String[]{BatchConfig.class.getName(), "preprocessJob"}
		            );

	   
	}

	
 
	
	

	

}

