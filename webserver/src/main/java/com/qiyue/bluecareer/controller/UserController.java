package com.qiyue.bluecareer.controller;

import com.qiyue.bluecareer.exception.BlueCareerException;
import com.qiyue.bluecareer.exception.HibernateException;
import com.qiyue.bluecareer.model.CommonResponse;
import com.qiyue.bluecareer.model.enums.ErrorEnum;
import com.qiyue.bluecareer.model.view.UserEntity;
import com.qiyue.bluecareer.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by qiyue on 2017/11/7
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<UserEntity>>  getUserList(){
        return new CommonResponse<>(userService.getUserList());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addUser(@RequestBody UserEntity userEntity) {
        if (userEntity.getUserName() == null
                || userEntity.getPassword() == null
                || userEntity.getEmail() == null) {
            return ErrorEnum.REQUEST_PARAMETER_ERROR.getResponse(userEntity.toString());
        }

        try {
            logger.debug("add user. " + userEntity.toString());
            userService.addUser(userEntity);
            return new CommonResponse();
        } catch (BlueCareerException e) {
            logger.error(e.getMessage());
            return ErrorEnum.SERVER_ERROR.getResponse(e.getMessage() + userEntity.getEmail());
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/email_exit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse verifyEmail(@RequestParam(value = "email") String email) {
        logger.debug("email verify. " + email);
        boolean res = userService.haveEmail(email);
        return new CommonResponse<>(res);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse verifyLogin(@RequestBody UserEntity userEntity) {
        if (userEntity.getPassword() == null || userEntity.getEmail() == null) {
            return ErrorEnum.REQUEST_PARAMETER_ERROR.getResponse(userEntity.toString());
        }

        String accessKey = userService.userLogin(userEntity.getEmail(), userEntity.getPassword());
        if (accessKey != null) {
            logger.debug("user login. " + userEntity.toString());
            return new CommonResponse<>(accessKey);
        } else {
            return ErrorEnum.LOGIN_ERROR.getResponse();
        }
    }

    @RequestMapping(value = "/image_path", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getUserImagePath(@RequestParam(value = "email") String email) {
        try {
            String imagePath = userService.getUserImagePath(email);
            return new CommonResponse<>(imagePath);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "image_upload", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse uploadUserImage() {
        return null;
    }
}
