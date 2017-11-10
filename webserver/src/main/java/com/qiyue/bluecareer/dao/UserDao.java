package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.exception.HibernateException;
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

    private static final String EMAIL_STR = "email";
    private static final String ACCESS_KEY_STR = "accessKey";
    private static final String PASSWORD_STR = "password";

    public List<UserEntity> getUserList(){
        Session session = sessionFactory.openSession();
        Query<UserEntity> query = session.createQuery("from UserEntity ", UserEntity.class);
        List<UserEntity> userList =  query.list();
        session.close();
        return userList;
    }

    /**
     * 添加用户
     * @param user 带有必须信息的Entity
     * @return
     * @throws HibernateException  SQL 异常
     */
    public boolean addUser(UserEntity user) throws HibernateException {
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
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }

    /**
     * 验证登陆邮箱与密码
     * @param mail  邮箱
     * @param encryptPassword 加密后的密码
     * @return 返回accessKey
     */
    public String verifyPassword(String mail, String encryptPassword) {
        Session session = sessionFactory.openSession();
        Query<String> query = session.createQuery(
                "SELECT user.accessKey from UserEntity AS user where user.email = :email AND user.password = :password", String.class);
        query.setParameter(EMAIL_STR, mail);
        query.setParameter(PASSWORD_STR, encryptPassword);
        List<String> resList =  query.list();
        session.close();
        if (resList.isEmpty()) {
            return null;
        } else {
            return resList.get(0);
        }
    }

    /**
     * 获取用户头像图片地址
     * @param email 用户邮箱
     * @return
     */
    public String getUserImagePath(String email) {
        Session session = sessionFactory.openSession();
        Query<String> query = session.createQuery(
                "SELECT user.imagePath from UserEntity AS user where user.email = :email", String.class);
        query.setParameter(EMAIL_STR, email);
        List<String> resList =  query.list();
        session.close();
        if (resList.isEmpty()) {
            return null;
        }
        return resList.get(0);
    }

    /**
     * 验证用户Key是否正缺
     * @param email 用户邮箱
     * @param accessKey Key
     * @return 符合返回true
     */
    public boolean verifyAccessKey(String email, String accessKey) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.email = :email AND user.accessKey = :accessKey");
        query.setParameter(EMAIL_STR, email);
        query.setParameter(ACCESS_KEY_STR, accessKey);
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }

    /**
     * 更新用户的key
     * @param email  需要更新的用户
     * @param newAccessKey 新key
     * @return
     * @throws HibernateException
     */
    public boolean updateAccessKey(String email, String newAccessKey) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "UPDATE UserEntity SET accessKey = :accessKey WHERE email = :email");
            query.setParameter(ACCESS_KEY_STR, newAccessKey);
            query.setParameter(EMAIL_STR, email);
            query.executeUpdate();
            session.getTransaction().commit();
            logger.debug("update user key . " + newAccessKey);
            return true;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            session.getTransaction().rollback();
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }


    /**
     * 验证邮箱是否已经存在
     * @param email 邮箱信息
     * @return
     */
    public boolean haveEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.email = :email");
        query.setParameter(EMAIL_STR, email);
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }
}
