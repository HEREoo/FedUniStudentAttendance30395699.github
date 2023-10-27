package au.edu.federation.itech3107.fedunistudentattendance30395699;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Course extends LitePalSupport implements Serializable {
    private Integer id;
    private String title;
    private String teacher;
    private String date;


    public Course() {
    }

    public Course(String title, String teacher, String date) {
        this.title = title;
        this.teacher = teacher;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}


