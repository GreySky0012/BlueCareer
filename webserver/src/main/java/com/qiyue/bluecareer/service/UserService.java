package com.qiyue.bluecareer.service;

import com.qiyue.bluecareer.dao.UserDao;
import com.qiyue.bluecareer.model.view.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiyue on 2017/11/7
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<UserEntity> getUserList(){
        return userDao.getUserList();
    }
}
