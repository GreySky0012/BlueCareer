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
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        boolean res = userService.haveEmail(userEntity);
        return new CommonResponse<>(res);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse verifyLogin(@RequestBody UserEntity userEntity) {
        if (userEntity.getPassword() == null || userEntity.getEmail() == null) {
            return ErrorEnum.REQUEST_PARAMETER_ERROR.getResponse(userEntity.toString());
        }

        try {
            logger.debug("user login. " + userEntity.toString());
            String accessKey = userService.userLogin(userEntity);
            return new CommonResponse<>(accessKey);
        } catch (BlueCareerException e) {
            logger.error(e.getMessage());
            return ErrorEnum.SERVER_ERROR.getResponse(e.getMessage() + userEntity.getEmail());
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/image_path", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getUserImagePath(@RequestParam(value = "email") String email,
                                           @RequestParam(value = "accessKey") String accessKey) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setAccessKey(accessKey);
            UserEntity response = userService.getUserImagePath(userEntity);
            return new CommonResponse<>(response);
        } catch (BlueCareerException e) {
            logger.error(e.getMessage());
            return ErrorEnum.KEY_ERROR.getResponse(e.getMessage() + email);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }
}
