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
    private String _currLevelName;
    private int _currLevelNum;
    private int _totalLevelNum;
    private String _occupationType;

    public MyOccupation(){}

    public MyOccupation(String _occupationNum, String _occupationName, String _currLevelName, int _currLevelNum, int _totalLevelNum, String _occupationType) {
        this._occupationNum = _occupationNum;
        this._occupationName = _occupationName;
        this._currLevelName = _currLevelName;
        this._currLevelNum = _currLevelNum;
        this._totalLevelNum = _totalLevelNum;
        this._occupationType = _occupationType;
    }

    @Override
    public String toString() {
        return "MyOccupation{" +
                "_occupationNum='" + _occupationNum + '\'' +
                ", _occupationName='" + _occupationName + '\'' +
                ", _currLevelName='" + _currLevelName + '\'' +
                ", _currLevelNum=" + _currLevelNum +
                ", _totalLevelNum=" + _totalLevelNum +
                ", _occupationType='" + _occupationType + '\'' +
                '}';
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

    public String get_currLevelName() {
        return _currLevelName;
    }

    public void set_currLevelName(String _currLevelName) {
        this._currLevelName = _currLevelName;
    }

    public int get_currLevelNum() {
        return _currLevelNum;
    }

    public void set_currLevelNum(int _currLevelNum) {
        this._currLevelNum = _currLevelNum;
    }

    public int get_totalLevelNum() {
        return _totalLevelNum;
    }

    public void set_totalLevelNum(int _totalLevelNum) {
        this._totalLevelNum = _totalLevelNum;
    }

    public String get_occupationType() {
        return _occupationType;
    }

    public void set_occupationType(String _occupationType) {
        this._occupationType = _occupationType;
    }
}