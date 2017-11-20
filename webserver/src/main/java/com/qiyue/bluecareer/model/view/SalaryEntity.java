package com.qiyue.bluecareer.model.view;

import javax.persistence.*;

@Entity
@Table(name = "salary", schema = "bluecareer", catalog = "")
@IdClass(SalaryEntityPK.class)
public class SalaryEntity {
    private String jobName;
    private String workPlace;
    private double salary;

    @Id
    @Column(name = "job_name", nullable = false, length = 64)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Id
    @Column(name = "work_place", nullable = false, length = 64)
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Basic
    @Column(name = "salary", nullable = false, precision = 0)
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryEntity that = (SalaryEntity) o;

        if (Double.compare(that.salary, salary) != 0) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = jobName != null ? jobName.hashCode() : 0;
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
