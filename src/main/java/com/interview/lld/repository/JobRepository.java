package com.interview.lld.repository;

import com.interview.lld.dto.Job;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JobRepository {
    private final Map<String, Job> jobs = new ConcurrentHashMap<> ();
    public  void save(Job job){
        jobs.put(job.getId(), job);
    }

    public Job findById (String id){
        return jobs.get(id);
    }

    public List<Job> getAllJobs(){
        return new ArrayList<>(jobs.values());
    }
}
