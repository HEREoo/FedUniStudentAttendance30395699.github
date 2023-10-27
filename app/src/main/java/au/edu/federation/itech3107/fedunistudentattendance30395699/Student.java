package au.edu.federation.itech3107.fedunistudentattendance30395699;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {
    private Integer id;
    private String name;
    private String course;
    private String stuId;
    @Column(ignore = true)
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Student(String stuId, String name, String course) {

        this.course = course;
        this.name = name;
        this.stuId = stuId;
    }

    public Student() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
