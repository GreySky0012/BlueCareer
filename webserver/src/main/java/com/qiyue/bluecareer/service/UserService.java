package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.exception.BlueCareerException;
import com.qiyue.bluecareer.dao.UserDao;
import com.qiyue.bluecareer.exception.HibernateException;
import com.qiyue.bluecareer.model.view.UserEntity;
import com.qiyue.bluecareer.utils.KeyUtil;
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

    public List<UserEntity> getUserList(){
        return userDao.getUserList();
    }

    public void addUser(UserEntity user) throws HibernateException, BlueCareerException {
        if (userDao.haveEmail(user)){
            logger.debug("email already registered " + user.getEmail());
            throw new BlueCareerException("email already registered ");
        }
        userDao.addUser(user);
    }

    public String userLogin(UserEntity user) throws HibernateException, BlueCareerException {
        if (!userDao.verifyPassword(user)){
            logger.debug("email or password error. " + user.getEmail());
            throw new BlueCareerException("email or password error. ");
        }
        String newKey = KeyUtil.getNewKey();
        logger.debug("create a new key. " + newKey);
        user.setAccessKey(newKey);
        logger.debug("update for user " + user.getEmail());
        userDao.updateAccessKey(user);
        return user.getAccessKey();
    }
}
