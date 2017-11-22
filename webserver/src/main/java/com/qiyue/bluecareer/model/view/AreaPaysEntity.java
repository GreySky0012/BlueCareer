package com.qiyue.bluecareer.model.view;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_area_pays", schema = "bluecareer", catalog = "")
public class AreaPaysEntity {
    private String workPlace;
    private Integer salary;

    public AreaPaysEntity(String workPlace, Integer salary) {
        this.workPlace = workPlace;
        this.salary = salary;
    }

    public AreaPaysEntity() {
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
    @Column(name = "salary", nullable = true)
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaPaysEntity that = (AreaPaysEntity) o;

        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workPlace != null ? workPlace.hashCode() : 0;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @javax.persistence.Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
