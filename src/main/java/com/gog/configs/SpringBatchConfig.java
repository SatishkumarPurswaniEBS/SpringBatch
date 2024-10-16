package com.gog.configs;

import com.gog.batch.ListUserBatchProcessor;
import com.gog.batch.ListUserWriter;
import com.gog.batch.UserReaderFromDB;
import com.gog.model.MyFinalUser;
import com.gog.model.MyUser;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean(name = "firstBatchJob")
    public Job job(JobRepository jobRepository, @Qualifier("step1") Step step1) {
        return new JobBuilder("firstBatchJob", jobRepository)/*.preventRestart()*/.start(step1).build();
    }

    @Bean(name = "secondBatchJob")
    public Job secondBatchJob(JobRepository jobRepository, /*Flow flow1*/ @Qualifier("step2") Step step2) {
        return new JobBuilder("secondBatchJob", jobRepository)
                .start(step2)
                .preventRestart()
//                .end()
                .build();
    }


    @Bean
    public Step step1(ItemReader<MyUser> itemReader, ItemProcessor<MyUser, MyUser> itemProcessor, ItemWriter<MyUser> itemWriter, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<MyUser, MyUser>chunk(4, transactionManager)
                .reader(itemReaderOne())
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
    
    @Bean
    @Qualifier("step2")
    public Step step2(ItemReader<List<MyFinalUser>> userReaderFromDB, ItemProcessor<List<MyFinalUser>, List<MyFinalUser>> listUserBatchProcessor, ItemWriter<List<MyFinalUser>> listUserWriter, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .<List<MyFinalUser>, List<MyFinalUser>>chunk(1, transactionManager)
                .reader(userReaderFromDB)
                .processor(listUserBatchProcessor)
                .writer(listUserWriter)
                .build();
    }

    @Bean
    @Qualifier("itemReaderOne")
    @Primary
    public FlatFileItemReader<MyUser> itemReaderOne() {

        FlatFileItemReader<MyUser> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("C:\\SatishkumarPurswani\\EngX\\learnspringbatch\\src\\main\\resources\\test.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<MyUser> lineMapper() {

        DefaultLineMapper<MyUser> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false); // if true, then the number of tokens in the line must match the number of tokens in the names array.
        lineTokenizer.setNames("id", "name", "department", "salary");

        BeanWrapperFieldSetMapper<MyUser> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(MyUser.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    /*@Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }*/

    @Bean
    public Flow flow1(Step step1, Step step2) {
        return new FlowBuilder<SimpleFlow>("MyFlow")
//                .from(step1).on("COMPLETED").to(step2)
                .start(step2)
                .build();

    }
}
