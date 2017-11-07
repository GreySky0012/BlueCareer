package com.qiyue.bluecareer.controller;

import com.qiyue.bluecareer.model.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qiyue on 2017/11/7
 */
@Controller
@ResponseBody
@RequestMapping(value = "/debug")
public class DebugController {

    /**
     * 测试接口是否可达
     * @return CommonResponse 标准返回
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<String> getDebugResponse() {
        return new CommonResponse<>("Default Debug API: Hello from BlueCareer");
    }
}
