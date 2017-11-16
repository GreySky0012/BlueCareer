package com.example.mercer.bluecareer.DataStruct;

import java.io.Serializable;

/**
 * author: Husen
 * date: 2017.11.16
 * description：我的职业Bean
 */

public class MyOccupation implements Serializable{
    private String _occupationNum;
    private String _occupationName;
    private String _occupationLevel;
    private int _occupation1;
    private int _occupation2;
    private String _occupationType;

    @Override
    public String toString() {
        return "MyOccupation{" +
                "_occupationNum='" + _occupationNum + '\'' +
                ", _occupationName='" + _occupationName + '\'' +
                ", _occupationLevel='" + _occupationLevel + '\'' +
                ", _occupation1=" + _occupation1 +
                ", _occupation2=" + _occupation2 +
                ", _occupationType='" + _occupationType + '\'' +
                '}';
    }

    public MyOccupation(){}

    public MyOccupation(String occupationNum, String occupationName, String occupationLevel, int occupation1, int occupation2, String occupationType){
        _occupationNum = occupationNum;
        _occupationName = occupationName;
        _occupationLevel = occupationLevel;
        _occupation1 = occupation1;
        _occupation2 = occupation2;
        _occupationType = occupationType;
    }

    public String get_occupationNum() {
        return _occupationNum;
    }

    public void set_occupationNum(String _occupationNum) {
        this._occupationNum = _occupationNum;
    }

    public String get_occupationName() {
        return _occupationName;
    }

    public void set_occupationName(String _occupationName) {
        this._occupationName = _occupationName;
    }

    public String get_occupationLevel() {
        return _occupationLevel;
    }

    public void set_occupationLevel(String _occupationLevel) {
        this._occupationLevel = _occupationLevel;
    }

    public int get_occupation1() {
        return _occupation1;
    }

    public void set_occupation1(int _occupation1) {
        this._occupation1 = _occupation1;
    }

    public int get_occupation2() {
        return _occupation2;
    }

    public void set_occupation2(int _occupation2) {
        this._occupation2 = _occupation2;
    }

    public String get_occupationType() {
        return _occupationType;
    }

    public void set_occupationType(String _occupationType) {
        this._occupationType = _occupationType;
    }
}