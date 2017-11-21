package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.model.view.JobEntity;
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
public class JobDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(JobDao.class);

    public JobEntity getJobInfo(String jobName) {
        Session session = sessionFactory.openSession();
        Query<JobEntity> query = session.createQuery("FROM JobEntity WHERE jobName = :jobName", JobEntity.class);
        query.setParameter("jobName", jobName);
        List<JobEntity> result = query.list();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
