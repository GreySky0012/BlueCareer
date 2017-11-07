package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.model.view.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qiyue on 2017/11/7
 */
@Repository
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<UserEntity> getUserList(){
        Session session = sessionFactory.openSession();
        Query<UserEntity> query = session.createQuery("from UserEntity ", UserEntity.class);
        List<UserEntity> userList =  query.list();
        session.close();
        return userList;
    }
}
