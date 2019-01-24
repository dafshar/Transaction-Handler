package config;











import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;


import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import dom.AccountTransaction;







//@Configuration
//@EnableTransactionManagement
//@ComponentScan
@EnableBatchProcessing

public class BatchConfig  extends DefaultBatchConfigurer{
	
@Autowired
public DataSource dataSource;

@Autowired
public JobBuilderFactory jobBuilderFactory;

@Autowired
public StepBuilderFactory stepBuilderFactory;





@Bean
public ResourcelessTransactionManager transactionManager() {
    return new ResourcelessTransactionManager();
}

@Bean
public JobRepository jobRepository(ResourcelessTransactionManager transactionManager) throws Exception {
    MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean(transactionManager);
    mapJobRepositoryFactoryBean.setTransactionManager(transactionManager);
    return mapJobRepositoryFactoryBean.getObject();
}

@Bean
@Primary
public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
    SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
    simpleJobLauncher.setJobRepository(jobRepository);
    return simpleJobLauncher;
}



@Bean
public DataSource dataSource() {
 final DriverManagerDataSource dataSource = new DriverManagerDataSource();
 dataSource.setDriverClassName("com.mysql.jdbc.Driver");
 dataSource.setUrl("jdbc:mysql://localhost:3306/world");
 dataSource.setUsername("root");
 dataSource.setPassword("password");
 
 return dataSource;
}

@Bean
public FlatFileItemReader<AccountTransaction> reader(){
 FlatFileItemReader<AccountTransaction> reader = new FlatFileItemReader<AccountTransaction>();
 reader.setResource(new ClassPathResource("account_transaction.csv"));
 reader.setLineMapper(new DefaultLineMapper<AccountTransaction>() {{
  setLineTokenizer(new DelimitedLineTokenizer() {{
   setNames(new String[] { "account_transaction_origination_dt", "account_transaction_sys_id", "account_transaction_cd", "account_transaction_dt", "account_transaction_trans_subtyp_cd", "correspondence_dt","quality_cd", "quality_sys_id", "document_number", "ext_source", "identification_cd", "input_file","partition_sys_id", "period_end_dt", "posted_cyc_id", "pin", "unpostable_cd", "unpostable_rsn_cd", "updated_by_trans", "updated_ts", "validity_cd"});
  }});
  setFieldSetMapper(new BeanWrapperFieldSetMapper<AccountTransaction>() {{
   setTargetType(AccountTransaction.class);
  }});
  
 }});
 
 return reader;
}

@Bean
public JdbcBatchItemWriter<AccountTransaction> writer(){
 JdbcBatchItemWriter<AccountTransaction> writer = new JdbcBatchItemWriter<AccountTransaction>();
 writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AccountTransaction>());
 writer.setDataSource(dataSource);
 writer.setSql("INSERT INTO account_transaction VALUES (:account_transaction_origination_dt, :account_transaction_sys_id, :account_transaction_cd,:account_transaction_dt, :account_transaction_trans_subtyp_cd, :correspondence_dt, :quality_cd, :quality_sys_id, :document_number, :ext_source, :identification_cd, :input_file, :partition_sys_id, :period_end_dt, :posted_cyc_id, :pin, :unpostable_cd, :unpostable_rsn_cd, :updated_by_trans, :updated_ts, :validity_cd)");
 writer.afterPropertiesSet();
 
 return writer;
}

@Bean
public Step step1() {
 return stepBuilderFactory.get("step1").<AccountTransaction, AccountTransaction> chunk(1)
   .reader(reader())
   .writer(writer())
   .build();
}

@Bean
public Job preprocessJob() {
 return jobBuilderFactory.get("preprocessJob")
   .incrementer(new RunIdIncrementer())
   .flow(step1())
   .end()
   .build();
}

}




