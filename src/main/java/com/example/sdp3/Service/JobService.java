package com.example.sdp3.Service;


import com.example.sdp3.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sdp3.Pojo.Jobs;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JobService{

    @Autowired
    JobRepository jobRepository;

    public void addJob(Jobs newJob){
        jobRepository.save(newJob);
    }

    public void deleteJobById(Long id){
        jobRepository.deleteById(id);
    }

    public List<Jobs> getAllJobs() {
        List<Jobs> job = jobRepository.findAll();
        if (job.size() != 0) {
            return job;
        }
        else{
            throw new IllegalStateException("Jobs Don't Exist");
        }
    }

        public Optional<Jobs> findJobById (Long id){
            Optional<Jobs> job = jobRepository.findById(id);
            if (job.isPresent()) {
                return job;
            } else {
                throw new IllegalStateException("Job Does not Exist");
            }
        }

    public List<Jobs> findJobByUserId (Long id){
        return jobRepository.findAllByUser_id(id).orElseThrow(() -> new IllegalStateException("No Items found."));

    }


    @Transactional
        public void updateJobs(Jobs updateJob){
            Optional<Jobs> job = jobRepository.findById(updateJob.getId());

            if(job.isPresent()){
                jobRepository.save(updateJob);
            }
            else{
                throw new IllegalStateException("Job not found");
            }

        }
    }