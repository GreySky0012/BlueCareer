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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            userService.addUser(userEntity);
            return new CommonResponse();
        } catch (BlueCareerException e) {
            logger.debug(e.getMessage());
            return ErrorEnum.SERVER_ERROR.getResponse(userEntity.getUserName());
        } catch (HibernateException e) {
            logger.debug(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }
}
