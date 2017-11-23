package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.dao.SalaryDao;
import com.qiyue.bluecareer.model.view.AreaPaysEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qiyue on 2017/11/20
 */
@Service
public class SalaryService {
    @Autowired
    private SalaryDao salaryDao;

    private static Logger logger = Logger.getLogger(SalaryService.class);


    public List<AreaPaysEntity> getAreaPaysByJob(String jobName) {
        return salaryDao.getAreaSalaryByJob(jobName);
    }
}
