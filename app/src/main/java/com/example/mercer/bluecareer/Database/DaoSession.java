package com.example.mercer.bluecareer.Database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.mercer.bluecareer.Database.LocalUser;

import com.example.mercer.bluecareer.Database.LocalUserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig localUserDaoConfig;

    private final LocalUserDao localUserDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        localUserDaoConfig = daoConfigMap.get(LocalUserDao.class).clone();
        localUserDaoConfig.initIdentityScope(type);

        localUserDao = new LocalUserDao(localUserDaoConfig, this);

        registerDao(LocalUser.class, localUserDao);
    }
    
    public void clear() {
        localUserDaoConfig.getIdentityScope().clear();
    }

    public LocalUserDao getLocalUserDao() {
        return localUserDao;
    }

}
