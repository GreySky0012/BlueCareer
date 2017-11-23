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
public class AreaPaysEntity {
    private String workPlace;
    private Double minAveSalary;
    private Double maxAveSalary;

    public AreaPaysEntity() {

    }

    public AreaPaysEntity(String workPlace, double minAveSalary, double maxAveSalary) {
        this.workPlace = workPlace;
        this.minAveSalary = minAveSalary;
        this.maxAveSalary = maxAveSalary;
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

        AreaPaysEntity that = (AreaPaysEntity) o;

        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;
        if (minAveSalary != null ? !minAveSalary.equals(that.minAveSalary) : that.minAveSalary != null) return false;
        if (maxAveSalary != null ? !maxAveSalary.equals(that.maxAveSalary) : that.maxAveSalary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workPlace != null ? workPlace.hashCode() : 0;
        result = 31 * result + (minAveSalary != null ? minAveSalary.hashCode() : 0);
        result = 31 * result + (maxAveSalary != null ? maxAveSalary.hashCode() : 0);
        return result;
    }

    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
