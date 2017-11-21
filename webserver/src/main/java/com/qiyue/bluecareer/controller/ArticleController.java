package com.qiyue.bluecareer.controller;

import com.qiyue.bluecareer.model.CommonResponse;
import com.qiyue.bluecareer.model.view.ArticleEntity;
import com.qiyue.bluecareer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public CommonResponse<List<ArticleEntity>> getAllArticle(@RequestParam(value = "start", name = "start") Integer start) {
        return new CommonResponse<>(articleService.getAllArticle(start));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<ArticleEntity>> getArticleByJob(@RequestParam(value = "jobs", name = "jobs") String[] jobs,
                                                               @RequestParam(value = "start", name = "start") Integer start) {
        List<ArticleEntity> result = new ArrayList<>();
        for (String job : jobs) {
            List<ArticleEntity> temp = articleService.getArticleByJob(job, start);
            result.addAll(temp);
        }
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/exclude", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<ArticleEntity>> getArticleExcludeJob(@RequestParam(value = "jobs", name = "jobs") String[] jobs,
                                                                    @RequestParam(value = "start", name = "start") Integer start) {
        return new CommonResponse<>(articleService.getArticleExcludeJobs(jobs, start));
    }
}
