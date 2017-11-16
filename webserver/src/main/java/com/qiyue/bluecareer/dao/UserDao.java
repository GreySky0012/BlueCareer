package com.qiyue.bluecareer.dao;

import com.qiyue.bluecareer.exception.HibernateException;
import com.qiyue.bluecareer.model.view.UserEntity;
import com.qiyue.bluecareer.utils.KeyUtil;
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

    private static final String ID_STR = "id";
    private static final String EMAIL_STR = "email";
    private static final String ACCESS_KEY_STR = "accessKey";
    private static final String PASSWORD_STR = "password";
    private static final String IMAGE_PATH_STR = "imagePath";

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
     * 修改用户数据  目前仅可修改  用户名 真实姓名 qq  职业
     * @param userEntity  用户新数据  不包括邮箱 密码  id  accesskey  image_path
     * @throws HibernateException
     */
    public void modifyUserInfo(UserEntity userEntity) throws HibernateException {

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(userEntity);
            session.getTransaction().commit();
            logger.debug("update user info . " + userEntity.toString());
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
     * @return UserEntity 用户信息
     */
    public UserEntity verifyPassword(String mail, String encryptPassword) {
        Session session = sessionFactory.openSession();
        Query<UserEntity> query = session.createQuery(
                "from UserEntity AS user where user.email = :email AND user.password = :password", UserEntity.class);
        query.setParameter(EMAIL_STR, mail);
        query.setParameter(PASSWORD_STR, encryptPassword);
        List<UserEntity> resList =  query.list();
        session.close();
        if (resList.isEmpty()) {
            return null;
        } else {
            return resList.get(0);
        }
    }

    /**
     * 获取用户头像图片地址
     * @param id 用户id
     * @return
     */
    public String getUserImagePath(Integer id) {
        Session session = sessionFactory.openSession();
        Query<String> query = session.createQuery(
                "SELECT user.imagePath from UserEntity AS user where user.id = :id", String.class);
        query.setParameter(ID_STR, id);
        List<String> resList =  query.list();
        session.close();
        if (resList.isEmpty()) {
            return null;
        }
        return resList.get(0);
    }

    /**
     * 获取用户职业信息
     * @param id 用户id
     * @return
     */
    public String getUserCareerMessage(Integer id) {
        Session session = sessionFactory.openSession();
        Query<String> query = session.createQuery(
                "SELECT user.careerMessage from UserEntity AS user where user.id = :id", String.class);
        query.setParameter(ID_STR, id);
        List<String> resList =  query.list();
        session.close();
        if (resList.isEmpty()) {
            return null;
        }
        return resList.get(0);
    }

    /**
     * 验证用户Key是否正缺
     * @param id 用户id
     * @param accessKey Key
     * @return 符合返回true
     */
    public boolean verifyAccessKey(Integer id, String accessKey) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from UserEntity AS user where user.id = :id AND user.accessKey = :accessKey");
        query.setParameter(ID_STR, id);
        query.setParameter(ACCESS_KEY_STR, accessKey);
        List resList =  query.list();
        session.close();
        return !resList.isEmpty();
    }

    /**
     * 更新用户的key
     * @param id  需要更新的用户id
     * @return 更新后的key
     * @throws HibernateException
     */
    public String updateAccessKey(Integer id) throws HibernateException {
        String newAccessKey = KeyUtil.getNewKey();
        newAccessKey = "abcdefg";
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "UPDATE UserEntity SET accessKey = :accessKey WHERE id = :id");
            query.setParameter(ACCESS_KEY_STR, newAccessKey);
            query.setParameter(ID_STR, id);
            query.executeUpdate();
            session.getTransaction().commit();
            logger.debug("update user key . " + newAccessKey);
            return newAccessKey;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            session.getTransaction().rollback();
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }

    /**
     * 更新用户 头像地址
     * @param id id
     * @param imagePath 地址
     * @throws HibernateException
     */
    public void updateImagePath(Integer id, String imagePath) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "UPDATE UserEntity SET imagePath = :imagePath WHERE id = :id");
            query.setParameter(IMAGE_PATH_STR, imagePath);
            query.setParameter(ID_STR, id);
            query.executeUpdate();
            session.getTransaction().commit();
            logger.debug("update user imagePath . " + imagePath);
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
