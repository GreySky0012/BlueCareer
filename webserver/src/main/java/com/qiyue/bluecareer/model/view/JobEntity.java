package com.qiyue.bluecareer.model.view;

import javax.persistence.*;

@Entity
@Table(name = "job", schema = "bluecareer", catalog = "")
public class JobEntity {
    private String jobName;
    private String duty;
    private String requirements;

    @Id
    @Column(name = "job_name", nullable = false, length = 64)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "duty", nullable = false, length = -1)
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Basic
    @Column(name = "requirements", nullable = false, length = -1)
    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobEntity jobEntity = (JobEntity) o;

        if (jobName != null ? !jobName.equals(jobEntity.jobName) : jobEntity.jobName != null) return false;
        if (duty != null ? !duty.equals(jobEntity.duty) : jobEntity.duty != null) return false;
        if (requirements != null ? !requirements.equals(jobEntity.requirements) : jobEntity.requirements != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobName != null ? jobName.hashCode() : 0;
        result = 31 * result + (duty != null ? duty.hashCode() : 0);
        result = 31 * result + (requirements != null ? requirements.hashCode() : 0);
        return result;
    }
}
