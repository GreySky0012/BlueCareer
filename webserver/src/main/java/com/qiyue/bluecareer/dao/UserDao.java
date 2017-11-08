package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.exception.BlueCareerException;
import com.qiyue.bluecareer.model.view.UserEntity;
import org.apache.log4j.Logger;
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
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(UserDao.class);

    public List<UserEntity> getUserList(){
        Session session = sessionFactory.openSession();
        Query<UserEntity> query = session.createQuery("from UserEntity ", UserEntity.class);
        List<UserEntity> userList =  query.list();
        session.close();
        return userList;
    }

    public boolean addUser(UserEntity user) throws BlueCareerException {
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
            throw new BlueCareerException(e);
        } finally {
            session.close();
        }
    }

    public boolean haveUserName(UserEntity userEntity) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.userName = :username");
        query.setParameter("username", userEntity.getUserName());
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }
}
