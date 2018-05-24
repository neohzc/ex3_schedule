package com.example.neoh.ex3_schedule;

public class Course {

    private String courseName;
    private String classRoom;
    private String teacher;
    private int id;


    Course(String courseName, String classRoom, String teacher, int id) {
        this.courseName = courseName;
        this.classRoom = classRoom;
        this.teacher = teacher;
        this.id = id;


    }

    Course(int id) {
        this.courseName = "";
        this.classRoom = "";
        this.teacher = "";
        this.id = id;


    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getId() {
        return id;
    }
}
