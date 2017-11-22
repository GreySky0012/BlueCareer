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

    private static int GET_ARTICLE_ONE_TIME = 10;
    /**
     * 获取所有文章
     * @return
     */
    public List<ArticleEntity> getAllArticle(Integer start) {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity ORDER BY id DESC", ArticleEntity.class);
        query.setFirstResult(start);
        query.setMaxResults(GET_ARTICLE_ONE_TIME);
        return query.list();
    }

    /**
     * 获取该职业相关的文章
     * @param jobName
     * @return
     */
    public List<ArticleEntity> getArticleByJob(String jobName, Integer start) {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity WHERE jobName = :jobName ORDER BY id DESC", ArticleEntity.class);
        query.setParameter("jobName", jobName);
        query.setFirstResult(start);
        query.setMaxResults(GET_ARTICLE_ONE_TIME + start);
        return query.list();
    }

    /**
     * 获取除该职业外的所有相关文章
     * @param jobName
     * @return
     */
    public List<ArticleEntity> getArticleExcludeJob(String jobName, Integer start) {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity WHERE jobName <> :jobName ORDER BY id DESC", ArticleEntity.class);
        query.setParameter("jobName", jobName);
        query.setFirstResult(start);
        query.setMaxResults(GET_ARTICLE_ONE_TIME);
        return query.list();
    }

    /**
     * 获取除列表中职业外的所有相关文章
     * @param jobNames
     * @return
     */
    public List<ArticleEntity> getArticleExcludeJobs(String[] jobNames, Integer start) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM ArticleEntity WHERE jobName <> :jobName0 ");
        for (int i = 1 ; i < jobNames.length ; i++) {
            sb.append(String.format("AND jobName <> :jobName%s ", i));
        }
        sb.append("ORDER BY id DESC ");
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery(sb.toString(), ArticleEntity.class);
        for (int i = 0 ; i < jobNames.length ; i++) {
            query.setParameter(String.format("jobName%s", i), jobNames[i]);
        }
        query.setFirstResult(start);
        query.setMaxResults(GET_ARTICLE_ONE_TIME);
        return query.list();
    }

    public List<ArticleEntity> getArticleOrderByViewCount(Integer start) {
        Session session = sessionFactory.openSession();
        Query<ArticleEntity> query = session.createQuery("FROM ArticleEntity ORDER BY viewCount", ArticleEntity.class);
        query.setFirstResult(start);
        query.setMaxResults(GET_ARTICLE_ONE_TIME);
        return query.list();
    }
}
