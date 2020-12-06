package com.dwinuray.app_assignment.models;
/*
 *  Dev - By Dwi Nur Cahyo
 *  Github : github.com/dwinuray
 * */
public class Assignments {

    private String id ,type, name, description, date, status;


    public Assignments(String id, String type, String name, String description, String date, String status) {
        this.id   = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

/*
 *  Dev - By Dwi Nur Cahyo
 *  Github : github.com/dwinuray
 * */
