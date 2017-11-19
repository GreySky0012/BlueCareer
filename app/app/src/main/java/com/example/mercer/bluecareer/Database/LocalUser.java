package com.example.mercer.bluecareer.Database;

import android.graphics.Bitmap;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by GreySky on 2017/11/9.
 */
@Entity
public class LocalUser {
    @Id(autoincrement = true)
    private Long id;


    @Index(unique = true)
    private String email;

    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 2074837886)
    public LocalUser(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 173344742)
    public LocalUser() {
    }
}
