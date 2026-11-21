package com.interview.lld.dto;

import com.interview.lld.JobPriority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequest {
    private String payload;
    private JobPriority priority;
    private LocalDateTime scheduledTime;
}
