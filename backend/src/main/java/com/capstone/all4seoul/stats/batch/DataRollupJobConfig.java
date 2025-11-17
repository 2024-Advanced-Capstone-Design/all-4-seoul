package com.capstone.all4seoul.stats.batch;

import com.capstone.all4seoul.seoulCityData.service.DataRollupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataRollupJobConfig {

    private final DataRollupService dataRollupService;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job dataRollupJob() {
        return new JobBuilder("dataRollupJob", jobRepository)
                .start(dataRollupStep())
                .build();
    }

    @Bean
    public Step dataRollupStep() {
        return new StepBuilder("dataRollupStep", jobRepository)
                .tasklet(dataRollupTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet dataRollupTasklet() {
        return (contribution, chunkContext) -> {
            log.info("[TASKLET-START] dataRollupTasklet started.");

            LocalDate yesterday = LocalDate.now().minusDays(1);
            dataRollupService.runDailyStatisticsRollup(yesterday);

            log.info("[TASKLET-END] dataRollupTasklet finished.");
            return RepeatStatus.FINISHED;
        };
    }
}