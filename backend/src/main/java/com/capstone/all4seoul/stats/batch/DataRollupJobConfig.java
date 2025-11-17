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
@EnableBatchProcessing // Spring Batch 활성화
@RequiredArgsConstructor
public class DataRollupJobConfig {

    private final DataRollupService dataRollupService;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    /**
     * 일일 통계 롤업 Job 정의
     */
    @Bean
    public Job dataRollupJob() {
        return new JobBuilder("dataRollupJob", jobRepository)
                .start(dataRollupStep()) // 'dataRollupStep' 1개로 구성됨
                .build();
    }

    /**
     * 롤업 Step 정의 (Tasklet 방식)
     */
    @Bean
    public Step dataRollupStep() {
        return new StepBuilder("dataRollupStep", jobRepository)
                .tasklet(dataRollupTasklet(), transactionManager) // Tasklet 실행
                .build();
    }

    /**
     * 실제 로직을 실행할 Tasklet (핵심) 이 Tasklet이 DataRollupService를 호출합니다.
     */
    @Bean
    public Tasklet dataRollupTasklet() {
        return (contribution, chunkContext) -> {
            log.info("[TASKLET-START] dataRollupTasklet started.");

            // (중요) Job 파라미터가 아닌, 실행 시점의 '어제' 날짜를 사용
            // Job 파라미터를 사용하면 더 정교하게 제어 가능 (예: 특정 날짜 재실행)
            // 여기서는 단순함을 위해 실행 시점 기준 '어제'로 고정
            LocalDate yesterday = LocalDate.now().minusDays(1);

            // 2단계에서 만든 서비스 로직 호출
            dataRollupService.runDailyStatisticsRollup(yesterday);

            log.info("[TASKLET-END] dataRollupTasklet finished.");
            return RepeatStatus.FINISHED; // 작업 완료
        };
    }
}