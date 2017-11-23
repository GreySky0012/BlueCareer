package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.model.view.AreaPaysEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Qiyue on 2017/11/7
 */
@Repository
public class SalaryDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(SalaryDao.class);

    public List<AreaPaysEntity> getAreaSalaryByJob(String jobName) {
        Session session = sessionFactory.openSession();
        Query<AreaPaysEntity> query = session.createQuery("SELECT new AreaPaysEntity(workPlace, minAveSalary, maxAveSalary) FROM JobAreaPaysEntity WHERE jobName = :jobName", AreaPaysEntity.class);
        query.setParameter("jobName", jobName);
        return query.list();
    }
}
