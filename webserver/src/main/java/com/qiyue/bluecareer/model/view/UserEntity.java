package com.qiyue.bluecareer.model.view;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "bluecareer", catalog = "")
public class UserEntity {
    private int id;
    private String userName;
    private String realName;
    private String password;
    private String email;
    private String QQ;
    private String accessKey;
    private String imagePath;
    private String careerMessage;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "realname", nullable = true, length = 64)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 64, updatable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 64, updatable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 64)
    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    @Basic
    @Column(name = "access_key", nullable = true, length = 64, updatable = false)
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Basic
    @Column(name = "image_path", nullable = true, length = 64, updatable = false)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "career_message", nullable = true, length = 1024)
    public String getCareerMessage() {
        return careerMessage;
    }

    public void setCareerMessage(String careerMessage) {
        this.careerMessage = careerMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (realName != null ? !realName.equals(that.realName) : that.realName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (QQ != null ? !QQ.equals(that.QQ) : that.QQ != null) return false;
        if (accessKey != null ? !accessKey.equals(that.accessKey) : that.accessKey != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;
        if (careerMessage != null ? !careerMessage.equals(that.careerMessage) : that.careerMessage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (QQ != null ? QQ.hashCode() : 0);
        result = 31 * result + (accessKey != null ? accessKey.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (careerMessage != null ? careerMessage.hashCode() : 0);
        return result;
    }
}
