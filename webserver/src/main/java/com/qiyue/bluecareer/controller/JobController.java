package com.qiyue.bluecareer.controller;

import com.qiyue.bluecareer.model.CommonResponse;
import com.qiyue.bluecareer.model.view.JobEntity;
import com.qiyue.bluecareer.model.view.UserEntity;
import com.qiyue.bluecareer.service.JobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Qiyue on 2017/11/20
 */
@Controller
@ResponseBody
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private JobService jobService;

    private static Logger logger = Logger.getLogger(JobController.class);

    @RequestMapping(value = "/{jobName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getJobInfo(@PathVariable(value = "jobName") String jobName) {

        return new CommonResponse<>(jobService.getJobInfo(jobName));
    }
}
