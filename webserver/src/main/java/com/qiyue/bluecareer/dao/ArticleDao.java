package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.model.view.ArticleEntity;
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
public class ArticleDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(ArticleDao.class);

    /**
     * 获取所有文章
     * @return
     */
    public List<ArticleEntity> getAllArticle() {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity", ArticleEntity.class);
        return query.list();
    }

    /**
     * 获取该职业相关的文章
     * @param jobName
     * @return
     */
    public List<ArticleEntity> getArticleByJob(String jobName) {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity WHERE jobName = :jobName", ArticleEntity.class);
        query.setParameter("jobName", jobName);
        return query.list();
    }

    /**
     * 获取除该职业外的所有相关文章
     * @param jobName
     * @return
     */
    public List<ArticleEntity> getArticleExcludeJob(String jobName) {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity WHERE jobName <> :jobName", ArticleEntity.class);
        query.setParameter("jobName", jobName);
        return query.list();
    }
}
