package com.qiyue.bluecareer.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qiyue.bluecareer.model.CommonResponse;
import com.qiyue.bluecareer.model.view.ArticleEntity;
import com.qiyue.bluecareer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiyue on 2017/11/20
 */
@Controller
@ResponseBody
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<ArticleEntity>> getAllArticle() {
        return new CommonResponse<>(articleService.getAllArticle());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<ArticleEntity>> getArticleByJob(@RequestParam(value = "jobs", name = "jobs") String[] jobs) {
        List<ArticleEntity> result = new ArrayList<>();
        for (String job : jobs) {
            List<ArticleEntity> temp = articleService.getArticleByJob(job);
            result.addAll(temp);
        }
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/exclude", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<ArticleEntity>> getArticleExcludeJob(@RequestParam(value = "jobs", name = "jobs") String[] jobs) {
        return new CommonResponse<>(articleService.getArticleExcludeJobs(jobs));
    }
}
