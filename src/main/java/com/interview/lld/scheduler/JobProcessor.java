package com.interview.lld.scheduler;


import com.interview.lld.dto.Job;
import com.interview.lld.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobProcessor {
    private final JobRepository jobRepository;
    public void execute(Job job) throws InterruptedException {
        System.out.println("Executing this job" + job);
        // whatever execution goes here we can have different jobs lets say
        Thread.sleep(200);
    }
}
