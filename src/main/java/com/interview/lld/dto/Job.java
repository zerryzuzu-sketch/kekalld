package com.interview.lld.dto;

import com.interview.lld.JobPriority;
import com.interview.lld.JobStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Job {
    private String id ;
    private String payload;
    private JobPriority jobPriority;
    private LocalDateTime jobScheduledTime ;
    private JobStatus jobStatus;
}
