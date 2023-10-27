package au.edu.federation.itech3107.fedunistudentattendance30395699;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class CheckTable extends LitePalSupport {
    private int id;
    private String title;
    private String name;
//    @Column(ignore = true)
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CheckTable() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public CheckTable(String title, String name, String date) {
        this.title = title;
        this.name = name;
        this.date = date;
    }
}
