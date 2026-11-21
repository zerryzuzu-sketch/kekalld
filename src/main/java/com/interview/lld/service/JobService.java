package com.interview.lld.service;

import com.interview.lld.IJobStrategies;
import com.interview.lld.JobPriority;
import com.interview.lld.dto.Job;
import com.interview.lld.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final List<IJobStrategies> strategies;

    public Job submit(Job request){
        Job job = Job.builder()
                .id(UUID.randomUUID().toString())
                .payload(request.getPayload())
                .jobPriority(request.getJobPriority())
                .jobScheduledTime(request.getJobScheduledTime()).build();
        jobRepository.save(job);
        switch (job.getJobPriority()){
            case
        }
    }


}
