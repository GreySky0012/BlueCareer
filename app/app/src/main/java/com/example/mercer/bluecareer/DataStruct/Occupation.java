package com.example.mercer.bluecareer.DataStruct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * author: Husen
 * date: 2017.11.20
 * description：
 */

public class Occupation implements Serializable{
    private String _jobName;
    private String _jobDuty;
    private String _jobRequire;
    private ArrayList<String> _workPlace;
    private ArrayList<Integer> _salary;

    public Occupation(){};

    public static Occupation getTestInstance(){
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList1.add("南京");
        arrayList2.add(10000);

        arrayList1.add("上海");
        arrayList2.add(12000);

        arrayList1.add("北京");
        arrayList2.add(13000);

        Occupation occupation = new Occupation(
                "Java工程师",
                "1、大专及以上学历学生（理工科背景，本科优先）；2、2016年或2017年毕业的应往届生；3、热爱计算机软件开发行业，善于学习和总结分析；4、有良好的工作态度和团队合作精神。",
                "岗位职责:1、协助团队进行Java的应用设计及开发规划；2、协助团队撰写设计开发及实现文档、流程；3、协助主管处理开发过程中技术问题；4、能够快速融入团队，积极、有效地开展工作。",
                arrayList1,
                arrayList2);

        return occupation;
    }

    public Occupation(String _jobName, String _jobDuty, String _jobRequire, ArrayList<String> _workPlace, ArrayList<Integer> _salary) {
        this._jobName = _jobName;
        this._jobDuty = _jobDuty;
        this._jobRequire = _jobRequire;
        this._workPlace = _workPlace;
        this._salary = _salary;
    }

    public String get_jobName() {
        return _jobName;
    }

    public void set_jobName(String _jobName) {
        this._jobName = _jobName;
    }

    public ArrayList<String> get_workPlace() {
        return _workPlace;
    }

    public void set_workPlace(ArrayList<String> _workPlace) {
        this._workPlace = _workPlace;
    }

    public ArrayList<Integer> get_salary() {
        return _salary;
    }

    public void set_salary(ArrayList<Integer> _salary) {
        this._salary = _salary;
    }

    public String get_jobDuty() {
        return _jobDuty;
    }

    public void set_jobDuty(String _jobDuty) {
        this._jobDuty = _jobDuty;
    }

    public String get_jobRequire() {
        return _jobRequire;
    }

    public void set_jobRequire(String _jobRequire) {
        this._jobRequire = _jobRequire;
    }
}
