package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.exception.BlueCareerException;
import com.qiyue.bluecareer.dao.UserDao;
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

    public List<UserEntity> getUserList(){
        return userDao.getUserList();
    }

    public boolean addUser(UserEntity user) throws BlueCareerException {
        if (userDao.haveUserName(user)){
            throw  new BlueCareerException("already have user " + user.getUserName());
        }
        return userDao.addUser(user);
    }
}
