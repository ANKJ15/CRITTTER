package com.udacity.jdnd.course3.critter.Entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User{
    @Column
    @ElementCollection(targetClass= EmployeeSkill.class)
    private Set<EmployeeSkill> employeeSkills;
    @Column
    @ElementCollection(targetClass=DayOfWeek.class)
    private Set<DayOfWeek> workingDays;
    @ManyToMany(mappedBy = "employees")
    private List<Schedule> schedules;

    public Set<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    public Set<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Set<DayOfWeek> workingDays) {
        this.workingDays = workingDays;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
