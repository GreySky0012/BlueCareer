package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.dao.JobDao;
import com.qiyue.bluecareer.model.view.JobEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Qiyue on 2017/11/20
 */
@Service
public class JobService {
    @Autowired
    private JobDao jobDao;

    private static Logger logger = Logger.getLogger(JobService.class);

    public JobEntity getJobInfo(String jobName) {
        return jobDao.getJobInfo(jobName);
    }
}
