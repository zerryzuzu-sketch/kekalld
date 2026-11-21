package com.interview.lld;

import com.interview.lld.dto.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LowPriorityJobStrategy implements  IJobStrategies{

    @Override
    public void submit(Job job) {

    }
}
