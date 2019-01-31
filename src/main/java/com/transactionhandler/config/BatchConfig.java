package com.transactionhandler.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;

import org.springframework.batch.core.repository.JobRepository;

import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.Scheduled;

import com.transactionhandler.dal.AccountTransactionEntity;
import com.transactionhandler.dal.AccountTransactionRepository;
import com.transactionhandler.dom.AccountTransaction;
import com.transactionhandler.dal.AccountTransactionEntity;
import com.transactionhandler.batch.Writer;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableBatchProcessing
@Import({ BatchScheduler.class })
@ComponentScan(basePackages = {"com.transactionhandler.batch"})
@EntityScan(basePackages = {"com.transactionhandler.dal"} )
@EnableJpaRepositories(basePackages = {"com.transactionhandler.dal"})
public class BatchConfig {



	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;



	@Autowired
	Writer writer;

	@Value("${input.file}") 
	Resource resource;
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("preprocessJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<AccountTransactionEntity, AccountTransactionEntity>chunk(1).reader(reader())
				.writer(writer).build();
	}

	@Bean
	public FlatFileItemReader<AccountTransactionEntity> reader() {
		FlatFileItemReader<AccountTransactionEntity> reader = new FlatFileItemReader<AccountTransactionEntity>();
		reader.setResource(resource);
		reader.setLineMapper(new DefaultLineMapper<AccountTransactionEntity>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "account_transaction_origination_dt", "account_transaction_sys_id",
								"account_transaction_cd", "account_transaction_dt",
								"account_transaction_trans_subtyp_cd", "correspondence_dt", "quality_cd",
								"quality_sys_id", "document_number", "ext_source", "identification_cd", "input_file",
								"partition_sys_id", "period_end_dt", "posted_cyc_id", "pin", "unpostable_cd",
								"unpostable_rsn_cd", "updated_by_trans", "updated_ts", "validity_cd" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<AccountTransactionEntity>() {
					{
						setTargetType(AccountTransactionEntity.class);
					}
				});

			}
		});
		System.out.println("Item values String");
		
		return reader;
	}


}
