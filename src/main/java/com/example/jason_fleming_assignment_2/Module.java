package com.example.jason_fleming_assignment_2;


public class Module{
    private String name;
    private String code;
    private int semester;

    public Module(String name, String code, int semester){
        if (name.length() > 0)
            this.name = name;
        if (code.length() > 0)
            this.code = code;
        if (semester == 1 || semester == 2)
            this.semester = semester;
    }

    //Getters
    public String GetName(){return name;}
    public String GetCode(){return code;}
    public float GetSemester(){return semester;}


}
