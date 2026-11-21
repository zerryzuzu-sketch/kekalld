package com.interview.lld;

import com.interview.lld.dto.Job;
import com.interview.lld.repository.JobRepository;
import com.interview.lld.scheduler.JobProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final JobRepository jobRepository;
    private final JobProcessor jobProcessor;
    @Scheduled(fixDelay = 60000)
    public void processLowPriorityJobs(){
        List<Job> jobs = jobRepository.getAllJobs();
        boolean isMediumJobsPending = jobs.stream()
                .anyMatch(j->
                        j.getJobPriority() == JobPriority.MEDIUM
                && (
                        j.getJobStatus() == JobStatus.RUNNING

                                || (
                                        j.getJobStatus() == JobStatus.PENDING
                        )
                                )
                );
        if(isMediumJobsPending) return;
        jobs.stream()
                .filter(j-> j.getJobPriority() ==JobPriority.LOW)
                .filter(j-> j.getJobStatus() == JobStatus.PENDING)
                .forEach( j->{
                    j.setJobStatus(JobStatus.RUNNING);
                    try {
                        jobProcessor.execute(j);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });


    };
}
