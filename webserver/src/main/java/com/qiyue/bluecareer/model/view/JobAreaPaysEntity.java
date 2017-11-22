package com.qiyue.bluecareer.model.view;
import javax.persistence.*;

@Entity
@Table(name = "job_area_pays", schema = "bluecareer", catalog = "")
public class JobAreaPaysEntity {
    private int id;
    private String jobName;
    private String workPlace;
    private Integer salary;

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

        JobAreaPaysEntity that = (JobAreaPaysEntity) o;

        if (id != that.id) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }
}
