package com.qiyue.bluecareer.controller;

import com.qiyue.bluecareer.model.CommonResponse;
import com.qiyue.bluecareer.model.view.AreaPaysEntity;
import com.qiyue.bluecareer.model.view.JobEntity;
import com.qiyue.bluecareer.service.JobService;
import com.qiyue.bluecareer.service.SalaryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Qiyue on 2017/11/20
 */
@Controller
@ResponseBody
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private SalaryService salaryService;

    private static Logger logger = Logger.getLogger(JobController.class);

    @RequestMapping(value = "/{jobName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getJobInfo(@PathVariable(value = "jobName") String jobName) {
        JobEntity jobInfo = jobService.getJobInfo(jobName);
        List<AreaPaysEntity> jobPays =  salaryService.getAreaPaysByJob(jobName);
        Map<String, Object> respData = new HashMap<>(2);
        respData.put("jobInfo", jobInfo);
        respData.put("jobPays", jobPays);
        return new CommonResponse<>(respData);
    }
}
