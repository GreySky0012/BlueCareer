package com.example.mercer.bluecareer.DataStruct.JsonStruct;

/**
 * Created by GreySky on 2017/11/19.
 */

public class UserUpdateData {
    public String userName;
    public String realName;
    public String careerMessage;
    public String qq;
    public UserUpdateData(String un,String rn,String cm,String q){
        userName = un;
        realName = rn;
        careerMessage = cm;
        qq = q;
    }
}
