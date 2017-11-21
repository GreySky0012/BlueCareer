package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.dao.ArticleDao;
import com.qiyue.bluecareer.model.view.ArticleEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qiyue on 2017/11/20
 */
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    private static Logger logger = Logger.getLogger(ArticleService.class);

    public List<ArticleEntity> getAllArticle(Integer start){
        return articleDao.getAllArticle(start);
    }

    public List<ArticleEntity> getArticleByJob(String jobName, Integer start) {
        return articleDao.getArticleByJob(jobName, start);
    }

    public List<ArticleEntity> getArticleExcludeJob(String jobName, Integer start) {
        return articleDao.getArticleExcludeJob(jobName, start);
    }

    public List<ArticleEntity> getArticleExcludeJobs(String[] jobNames, Integer start) {
        return articleDao.getArticleExcludeJobs(jobNames, start);
    }
}
