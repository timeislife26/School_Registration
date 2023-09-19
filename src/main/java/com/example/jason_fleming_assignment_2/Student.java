package com.example.jason_fleming_assignment_2;

import javafx.scene.control.DatePicker;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Student {
    protected String name;
    private String studentID;
    private DatePicker DOB;
    private String currentYear;
    protected int currentSemester;
    protected ArrayList<Module> moduleList = new ArrayList<>();
    private Controller controller;

    public Student(String name, String studentID, DatePicker DOB, String currentYear, int currentSemester, Controller controller){
        if (name.length() > 0)
            this.name = name;
        else
            controller.CreateErrorBox("A name has to be entered");
        if (CheckStudentID(studentID))
            this.studentID = studentID;
        if (CheckDOB(DOB))
            this.DOB = DOB;
        if (currentYear.length() > 0)
            this.currentYear = currentYear;
        if (currentSemester > 0)
            this.currentSemester = currentSemester;
        this.controller = controller;
    }

    //Getters
    public String GetName(){return name;}
    public String GetStudentID(){return studentID;}
    public DatePicker GetDOB(){return DOB;}
    public float GetCurrentSemester(){return currentSemester;}


    //Extra functions
    private boolean CheckStudentID(String ID){
        if (ID.length() > 0){
            if (ID.charAt(0) == 'R')
                return true;
            else {
                controller.CreateErrorBox("Student ID needs to start with R");
            }
        }
        else controller.CreateErrorBox("All Fields need to be entered");
        return false;
    }
    private boolean CheckDOB(DatePicker DOB){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String str = DOB.getValue().format(formatter);
        if (str.length() > 0 && DOB.getValue().getYear() > 1900)
            return true;
        return false;
    }
}
