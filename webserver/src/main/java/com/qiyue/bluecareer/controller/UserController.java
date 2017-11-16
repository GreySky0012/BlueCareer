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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Qiyue on 2017/11/7
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 调试接口  获取所有用户信息
     * @return 用户列表
     * @deprecated
     */
    @Deprecated
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<UserEntity>>  getUserList(){
        return new CommonResponse<>(userService.getUserList());
    }

    /**
     * 添加用户接口
     * @param userEntity 带有必要信息的Entity 用户名  邮箱 密码
     * @return CommonResponse data为null
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addUser(@RequestBody UserEntity userEntity) {
        if (userEntity.getUserName() == null
                || userEntity.getPassword() == null
                || userEntity.getEmail() == null) {
            return ErrorEnum.REQUEST_PARAMETER_ERROR.getResponse(userEntity.toString());
        }
        try {
            userService.addUser(userEntity);
            logger.debug("add user. " + userEntity.toString());
            return new CommonResponse();
        } catch (BlueCareerException e) {
            logger.error(e.getMessage());
            return ErrorEnum.SERVER_ERROR.getResponse(e.getMessage() + userEntity.getEmail());
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse modify(HttpServletRequest httpReq, @RequestBody UserEntity userEntity) {
        String idStr =  httpReq.getHeader("id");
        Integer id = Integer.valueOf(idStr);
        userEntity.setId(id);
        try {
            userService.modifyUser(userEntity);
            return new CommonResponse();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            return ErrorEnum.HIBERNATE_ERROR.getResponse(e.getMessage());
        }
    }

    /**
     * 查询邮箱是否存在接口
     * @param email 邮箱
     * @return CommonResponse data 字段为 结果  true 存在
     */
    @RequestMapping(value = "/email_exist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse verifyEmail(@RequestParam(value = "email") String email) {
        logger.debug("email verify. " + email);
        boolean res = userService.haveEmail(email);
        return new CommonResponse<>(res);
    }

    /**
     * 用户登陆
     * @param userEntity 带有用户邮箱和密码的Entity
     * @return CommonResponse data 字段为 accessKey
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse verifyLogin(@RequestBody UserEntity userEntity) {
        if (userEntity.getPassword() == null || userEntity.getEmail() == null) {
            return ErrorEnum.REQUEST_PARAMETER_ERROR.getResponse(userEntity.toString());
        }

        UserEntity user = userService.userLogin(userEntity.getEmail(), userEntity.getPassword());
        if (user != null) {
            logger.debug("user login. " + userEntity.toString());
            return new CommonResponse<>(user);
        } else {
            return ErrorEnum.LOGIN_ERROR.getResponse();
        }
    }

    /**
     * 获取邮箱对应用户头像图片地址
     * @param httpReq header 携带 id
     * @return CommonResponse data 字段为 头像图片地址
     */
    @RequestMapping(value = "/image_path", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getUserImagePath(HttpServletRequest httpReq) {
        String idStr =  httpReq.getHeader("id");
        Integer id = Integer.valueOf(idStr);
        String imagePath = userService.getUserImagePath(id);
        return new CommonResponse<>(imagePath);
    }

    @RequestMapping(value = "image_upload", method = RequestMethod.PUT, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse uploadUserImage() {
        return null;
    }
}
