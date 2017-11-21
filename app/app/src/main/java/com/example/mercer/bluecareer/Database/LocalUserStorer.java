package com.example.mercer.bluecareer.Database;

import android.app.Activity;

import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.LocalUserDao;
import com.example.mercer.bluecareer.Activities.BActivity;
import com.example.mercer.bluecareer.Manager.DatabaseManager;
import com.example.mercer.bluecareer.Manager.SystemManager;

import java.util.List;

/**
 * Created by GreySky on 2017/11/21.
 */

public class LocalUserStorer {
    public static void Store(BActivity activity, String email, String password){
        LocalUserDao localUserDao = DatabaseManager.GetInstance(activity, "LocalUser").GetSession().getLocalUserDao();
        List<LocalUser> users = localUserDao.queryBuilder().list();
        if (users.isEmpty()){
            LocalUser localUser = new LocalUser();
            localUser.setEmail(email);
            localUser.setPassword(password);
            localUser.setId(0l);
            localUserDao.insert(localUser);
            return;
        }
        users.get(0).setPassword(password);
        users.get(0).setEmail(email);
        localUserDao.update(users.get(0));
    }

    public static LocalUser Get(BActivity activity){
        LocalUserDao localUserDao = DatabaseManager.GetInstance(activity, "LocalUser").GetSession().getLocalUserDao();
        List<LocalUser> users = localUserDao.queryBuilder().list();
        if (users.isEmpty()){
            return null;
        }
        return users.get(0);
    }
}
