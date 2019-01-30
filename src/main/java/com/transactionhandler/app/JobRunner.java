package com.transactionhandler.app;



import java.util.Date;

import javax.sql.DataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.transactionhandler.config.BatchConfig;
import com.transactionhandler.dal.*;
import com.transactionhandler.dom.AccountTransaction;

@SpringBootApplication(scanBasePackages = "app") 
@EnableJpaRepositories("com.transactionhandler.*")
//@ComponentScan(basePackages = { "com.transactionhandler.*" })
@EntityScan("com.transactionhandler.*") 
//@Import({BatchConfig.class })
@EnableBatchProcessing
public class JobRunner {


   // @Autowired
	
   // public JobLauncher jobLauncher;
	
	@Autowired
	
    public AccountTransactionRepository repository;
	

	

      
  
	
	public static void main(String[] args) throws Exception {
		
		

		SpringApplication.run(JobRunner.class);
		/*
		   CommandLineJobRunner.main(
		            new String[]{BatchConfig.class.getName(), "preprocessJob"}
		            );
*/
	   
	}
	
	
	@Bean

	public CommandLineRunner populateAccountTransaction(AccountTransactionRepository repository) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		Date date = format.parse ("01/21/2012");  

		
		AccountTransactionEntity entity = new AccountTransactionEntity();
		
		entity.setAccount_transaction_origination_dt(date);
		entity.setAccount_transaction_sys_id("Sysid54");
		entity.setAccount_transaction_cd(1);
		entity.setAccount_transaction_dt(date);
		entity.setAccount_transaction_trans_subtyp_cd(5);
		entity.setCorrespondence_dt(date);
		entity.setQuality_cd(2);
		entity.setQuality_sys_id("Sysidqual");
		entity.setDocument_number("doc005");
		entity.setExt_source("file");
		entity.setIdentification_cd(3);
		entity.setInput_file("test_input_file");
		entity.setPartition_sys_id("partid3");
		entity.setPeriod_end_dt(date);
		entity.setPosted_cyc_id("2019");
		entity.setPin("1234");
		entity.setUnpostable_cd(4);
		entity.setUnpostable_rsn_cd(2);
		entity.setUpdated_by_trans("test");
		entity.setUpdated_ts(date);
		entity.setValidity_cd(2);
		
				
		/*
				new AccountTransactionEntity(sDate.parse("01/21/2012"),"Sysid54",1,sDate.parse("3/23/2019"),
					2,sDate.parse("1/23/2019"),3,"Sysidqual","doc005","file",5,"test_input_file","partid3",
				sDate.parse("1/23/2019"),"2019","1234",4,2,"test",sDate.parse("1/23/2019"),2);
		*/
		return (args) -> {

			repository.save(entity);
			
			repository.saveAndFlush(entity);
		};
		
		
		
	}
	

	
	}

	
 
	
	

	



