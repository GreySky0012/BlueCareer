package com.qiyue.bluecareer.model.view;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SalaryEntityPK implements Serializable {
    private String jobName;
    private String workPlace;

    @Column(name = "job_name", nullable = false, length = 64)
    @Id
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Column(name = "work_place", nullable = false, length = 64)
    @Id
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryEntityPK that = (SalaryEntityPK) o;

        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobName != null ? jobName.hashCode() : 0;
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        return result;
    }
}
