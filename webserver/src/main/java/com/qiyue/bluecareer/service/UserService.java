package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.dao.UserDao;
import com.qiyue.bluecareer.exception.BlueCareerException;
import com.qiyue.bluecareer.exception.HibernateException;
import com.qiyue.bluecareer.model.view.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiyue on 2017/11/7
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    private static Logger logger = Logger.getLogger(UserService.class);

    /**
     * @deprecated 仅供调试使用
     * @return 用户列表
     */
    @Deprecated
    public List<UserEntity> getUserList(){
        return userDao.getUserList();
    }

    /**
     * 添加用户
     * @param user 带有用户必要信息的Entity
     * @throws HibernateException  SQL异常
     * @throws BlueCareerException  邮箱已存在异常
     */
    public void addUser(UserEntity user) throws HibernateException, BlueCareerException {
        if (userDao.haveEmail(user.getEmail())){
            logger.debug("email already registered " + user.getEmail());
            throw new BlueCareerException("email already registered ");
        }
        userDao.addUser(user);
    }

    /**
     * 更新用户信息
     * @param userEntity 带有用户信息的entity  id为必须
     * @throws HibernateException
     */
    public void modifyUser(UserEntity userEntity) throws HibernateException {
//        userEntity.setPassword(null);
//        userEntity.setAccessKey(null);
//        userEntity.setImagePath(null);
        userDao.modifyUserInfo(userEntity);
    }

    /**
     * 查询邮箱是否存在
     * @param email 邮箱
     * @return true 存在
     */
    public boolean haveEmail(String email) {
        return userDao.haveEmail(email);
    }

    /**
     * 用户登陆
     * @param mail 用户邮箱
     * @param password 用户密码
     * @return access_key
     */
    public UserEntity userLogin(String mail, String password) {
        //todo 用户密码加密
        UserEntity userEntity = userDao.verifyPassword(mail, password);
        userEntity.setEmail(null);
        userEntity.setPassword(null);
        return userEntity;
    }

    /**
     * 返回对应用户的头像图片地址
     * @param id 用户id
     * @return 地址
     */
    public String getUserImagePath(Integer id) {
        return userDao.getUserImagePath(id);
    }

    public void updateUserImagePath(Integer id, String imagePath) throws HibernateException {
        userDao.updateImagePath(id, imagePath);
    }

    /**
     * 返回对应用户的职业信息
     * @param id 用户id
     * @return
     */
    public String getUserCareerMessage(Integer id) {
        return userDao.getUserCareerMessage(id);
    }
}
