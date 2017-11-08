package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.exception.HibernateException;
import com.qiyue.bluecareer.model.view.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
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
     * @param userEntity  带有邮箱和密码信息的Entity
     * @return 正确返回true
     */
    public boolean verifyPassword(UserEntity userEntity) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.email = :email AND user.password = :password");
        query.setParameter("email", userEntity.getEmail());
        query.setParameter("password", userEntity.getPassword());
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }

    /**
     * 验证请求key是否正确
     * @param userEntity  带有邮箱和key信息的Entity
     * @return
     */
    public boolean verifyAccesssKey(UserEntity userEntity) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.id = : AND user.accessKey = :accessKey");
        query.setParameter("email", userEntity.getEmail());
        query.setParameter("accessKey", userEntity.getAccessKey());
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }

    /**
     * 更新用户的key
     * @param userEntity 带有邮箱和新key信息的Entity
     * @return
     * @throws HibernateException
     */
    public boolean updateAccessKey(UserEntity userEntity) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "UPDATE UserEntity SET accessKey = :accessKey WHERE email = :email");
            query.setParameter("accessKey", userEntity.getAccessKey());
            query.setParameter("email", userEntity.getEmail());
            query.executeUpdate();
            session.getTransaction().commit();
            logger.debug("update user key . " + userEntity.getAccessKey());
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
     * @param userEntity 带有邮箱信息的Entity
     * @return
     */
    public boolean haveEmail(UserEntity userEntity) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.email = :email");
        query.setParameter("email", userEntity.getEmail());
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }
}
