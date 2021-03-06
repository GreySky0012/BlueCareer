package com.example.mercer.bluecareer.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOCAL_USER".
*/
public class LocalUserDao extends AbstractDao<LocalUser, Long> {

    public static final String TABLENAME = "LOCAL_USER";

    /**
     * Properties of entity LocalUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Email = new Property(1, String.class, "email", false, "EMAIL");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
    };


    public LocalUserDao(DaoConfig config) {
        super(config);
    }
    
    public LocalUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOCAL_USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"EMAIL\" TEXT," + // 1: email
                "\"PASSWORD\" TEXT);"); // 2: password
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_LOCAL_USER_EMAIL ON LOCAL_USER" +
                " (\"EMAIL\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOCAL_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LocalUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(2, email);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LocalUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(2, email);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LocalUser readEntity(Cursor cursor, int offset) {
        LocalUser entity = new LocalUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // email
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // password
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LocalUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEmail(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LocalUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LocalUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
