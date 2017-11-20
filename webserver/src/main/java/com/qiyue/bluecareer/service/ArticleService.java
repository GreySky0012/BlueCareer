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

    public List<ArticleEntity> getAllArticle(){
        return articleDao.getAllArticle();
    }

    public List<ArticleEntity> getArticleByJob(String jobName) {
        return articleDao.getArticleByJob(jobName);
    }

    public List<ArticleEntity> getArticleExcludeJob(String jobName) {
        return articleDao.getArticleExcludeJob(jobName);
    }
}
