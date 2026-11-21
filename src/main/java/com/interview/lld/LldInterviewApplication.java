package com.interview.lld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LldInterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(LldInterviewApplication.class, args);
    }
}



//Design and implement a small HTTP service to manage background jobs.
//
//The service should allow clients to submit jobs that need to be processed asynchronously. Jobs can have different priorities, and the system should decide how to handle them based on their priority.
//
//        HIGH: Process the job immediately using a mock worker.
//
//MEDIUM: Record the job for later processing.
//
//LOW: Record the job without triggering immediate processing.
//
//The system should maintain the state of submitted jobs so that their progress and outcomes can be tracked.


//invoke this probation job immediately



// in your request whatever job you are getting you need to execute it
// for high priority job as soon as you get it do a async work immediately
// medium priority jobs would have the particular time when it would have to run them -
// if all my medium priority jobs are done then we can think of processing the low priority jobs
// we can make a scheduler for low priority jobs that would be running like lets say after every 10 mins
// job should also have status