package com.qiyue.bluecareer.model.view;
/*
* ------------------------------------------------------------------
* Copyright © 2017 Hangzhou DtDream Technology Co.,Lt d. All rights reserved.
* ------------------------------------------------------------------
*       Product: net
*   Module Name: GateWay
*  Date Created: 2017/11/22
*   Description:
* ------------------------------------------------------------------
* Modification History
* DATE            Name           Description
* ------------------------------------------------------------------
* 2017/11/22     
* ------------------------------------------------------------------
*/

import javax.persistence.*;

@Entity
@Table(name = "job_area_pays", schema = "bluecareer", catalog = "")
public class JobAreaPaysEntity {
    private int id;
    private String jobName;
    private String workPlace;
    private Double minAveSalary;
    private Double maxAveSalary;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_name", nullable = false, length = 50)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "work_place", nullable = false, length = 50)
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Basic
    @Column(name = "min_ave_salary", nullable = true, precision = 0)
    public Double getMinAveSalary() {
        return minAveSalary;
    }

    public void setMinAveSalary(Double minAveSalary) {
        this.minAveSalary = minAveSalary;
    }

    @Basic
    @Column(name = "max_ave_salary", nullable = true, precision = 0)
    public Double getMaxAveSalary() {
        return maxAveSalary;
    }

    public void setMaxAveSalary(Double maxAveSalary) {
        this.maxAveSalary = maxAveSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobAreaPaysEntity that = (JobAreaPaysEntity) o;

        if (id != that.id) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;
        if (minAveSalary != null ? !minAveSalary.equals(that.minAveSalary) : that.minAveSalary != null) return false;
        if (maxAveSalary != null ? !maxAveSalary.equals(that.maxAveSalary) : that.maxAveSalary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        result = 31 * result + (minAveSalary != null ? minAveSalary.hashCode() : 0);
        result = 31 * result + (maxAveSalary != null ? maxAveSalary.hashCode() : 0);
        return result;
    }
}
