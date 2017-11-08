package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.model.view.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiyue on 2017/11/7
 */
@Repository
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;

    Logger logger = Logger.getLogger(this.getClass());

    public List<UserEntity> getUserList(){
        Session session = sessionFactory.openSession();
        Query<UserEntity> query = session.createQuery("from UserEntity ", UserEntity.class);
        List<UserEntity> userList =  query.list();
        session.close();
        return userList;
    }

    public boolean addUser(UserEntity user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            logger.debug("save user. " +  user.toString());
            return true;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
