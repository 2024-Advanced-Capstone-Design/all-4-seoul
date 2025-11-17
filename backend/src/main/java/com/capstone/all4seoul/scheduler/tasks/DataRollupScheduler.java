package com.capstone.all4seoul.scheduler.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class DataRollupScheduler {

    private final JobLauncher jobLauncher;
    private final Job dataRollupJob;

    @Scheduled(cron = "0 5 0 * * *")
    public void runDataRollupJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("launchTime", LocalDateTime.now().toString())
                    .toJobParameters();

            log.info("[SCHEDULER] Starting dataRollupJob...");
            jobLauncher.run(dataRollupJob, jobParameters);

        } catch (Exception e) {
            log.error("[SCHEDULER-FAIL] Failed to launch dataRollupJob. Error: {}", e.getMessage());
        }
    }
}