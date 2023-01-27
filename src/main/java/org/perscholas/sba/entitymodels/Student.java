package org.perscholas.sba.entitymodels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Student")
public class Student implements Serializable {
    @Id
    private String sEmail;
    private String sName;
    private String sPassword;
    @OneToMany(targetEntity = Course.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> sCourses;

    public Student() {
    }

    public Student(String sEmail, String sName, String sPassword, List<Course> sCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPassword = sPassword;
        this.sCourses = sCourses;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sEmail, student.sEmail) && Objects.equals(sName, student.sName) && Objects.equals(sPassword, student.sPassword) && Objects.equals(sCourses, student.sCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sEmail, sName, sPassword, sCourses);
    }
}