package com.example.mercer.bluecareer.Manager;

import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.example.mercer.bluecareer.Activities.BActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by GreySky on 2017/11/9.
 */
public class DatabaseManager {
    private DaoMaster.DevOpenHelper _helper;
    private SQLiteDatabase _db;
    private DaoMaster _master;
    private DaoSession _session;

    private static HashMap<String,DatabaseManager> _instance;

    private DatabaseManager(BActivity activity, String dbname){
        _helper = new DaoMaster.DevOpenHelper(activity,dbname,null);
        _db = _helper.getWritableDatabase();
        _master = new DaoMaster(_db);
        _session = _master.newSession();
    }

    public DaoSession GetSession(){return _session;}

    public static DatabaseManager GetInstance(BActivity activity,String dbname){
        if (_instance == null)
            _instance = new HashMap<String,DatabaseManager>();
        if (!_instance.containsKey(dbname))
            _instance.put(dbname,new DatabaseManager(activity,dbname));
        return _instance.get(dbname);
    }
}