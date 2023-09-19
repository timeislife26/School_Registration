package com.example.jason_fleming_assignment_2;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {
    private final GUI gui;
    private DatabaseController dbController;
    private int resultType;

    public Controller(GUI gui){
        this.gui = gui;
        dbController = new DatabaseController(gui, this);
    }
    public void AddStudent() {
        Student student;
        try {
            student = new Student(gui.enterNameTF.getText(), gui.enterIDTF.getText(), gui.enterDP, gui.studentYearDD.getValue(), gui.studentSemesterDD.getValue(), this);
            dbController.AddStudent();
        } catch (Exception e){
            CreateErrorBox("All areas need to be filled out correctly");
        }
    }
    public void RemoveStudent(){
        dbController.RemoveStudent();
    }

    //Tab2
    public void AddModule(){
        try{
            Module module = new Module(gui.enterModuleNameTF.getText(), gui.enterModuleCodeTF.getText(), gui.moduleSemesterDD.getValue());
            dbController.AddModule();
        } catch (Exception e){
            CreateErrorBox("All areas need to be filled out correctly");
        }
    }
    public void RemoveModule(){
        dbController.RemoveModule();
    }
    //Tab3
    public boolean CheckGrade(){
        if (gui.enterGradeTF.getText().toLowerCase() != "np") {
            if (gui.enterGradeTF.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.NONE, "The grade field is empty do you want enter 'NP' for Not Completed?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                Optional<ButtonType> result = a.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == ButtonType.YES) {
                        //Yes button is pressed
                        gui.enterGradeTF.setText("NP");
                        return true;
                    } else if (result.get() == ButtonType.NO) {
                        // No button is pressed
                        return false;
                    }
                }
            } else if (Integer.parseInt(gui.enterGradeTF.getText()) >= 0 && Integer.parseInt(gui.enterGradeTF.getText()) <= 100 ) {
                return true;
            }
            else CreateErrorBox("You must enter a number between 0 and 100 or NP for not completed");
        }
        else {
            gui.enterGradeTF.setText("NP");
            return true;
        }
        return false;
    }
    public void AddGrade(){
        if (CheckGrade()){
            dbController.AddGrade();
        }
    }
    public void ModifyGrade(){
        if (CheckGrade()){
            dbController.ModifyGrade();
        }
    }
    public void DeleteGrade(){
        dbController.DeleteGrade();
    }
    //Tab4
    public boolean isStudentInfoDDSelected(){
        /*if (gui.studentInfoStudentID.getSelectionModel().getSelectedItem().toString() == null){
            return false;
        }*/
        return true;
    }
    public void SetResultsType(int resultType){
        this.resultType = resultType;
    }
    public void ShowResultType(){
        switch (resultType){
            case 1:
                GetPassedGrades();
                break;
            case 2:
                GetFailedModules();
                break;
            case 3:
                GetAllModules();
                break;
        }
    }
    private void GetPassedGrades(){
        if (isStudentInfoDDSelected()) {
            StringBuilder studentInfo = new StringBuilder(dbController.GetStudentInfo());
            ArrayList<String> passedModules = dbController.GetPassedGrades();
            for (String pModules : passedModules) {
                studentInfo.append(pModules);
            }
            gui.studentInfoTA.setText(studentInfo.toString());
        }
    }
    private void GetFailedModules(){
        if (isStudentInfoDDSelected()){
            StringBuilder studentInfo = new StringBuilder(dbController.GetStudentInfo());
            ArrayList<String> failedModules = dbController.GetFailedGrades();
            for (String fModules : failedModules) {
                studentInfo.append(fModules);
            }
            gui.studentInfoTA.setText(studentInfo.toString());
        }
    }
    private void GetAllModules(){
        if (isStudentInfoDDSelected()){
            StringBuilder studentInfo = new StringBuilder(dbController.GetStudentInfo());
            ArrayList<String> allModules = dbController.GetAllGrades();
            for (String aModules : allModules) {
                studentInfo.append(aModules);
            }
            gui.studentInfoTA.setText(studentInfo.toString());
        }
    }

    //Mutual Functions
    public ArrayList<String> GetStudentNumbers(){
        return dbController.GetStudentNumbers();
    }
    public ArrayList<String> GetModuleCodes(){
        return dbController.GetModuleCodes();
    }
    public void GetStudentName(){
        gui.gradeStudentName.setText(dbController.GetStudentName());
    }
    public void GetModuleName(){
        gui.gradeModuleName.setText(dbController.GetModuleName());
    }
    //creates an error box with a message
    public void CreateErrorBox(String message){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(message);
        a.show();
    }
    //Memory Leak
    public void MemoryLeak(){
        int i = 0;
        while (true){
            gui.enterNameTF.setText("a");
            gui.enterIDTF.setText("R" + i);
            gui.enterDP.setValue(LocalDate.of(2005,1,1));
            gui.studentYearDD.setValue("1st");
            gui.studentSemesterDD.setValue(1);
            dbController.AddStudent();
            i++;
        }
    }
    public void ResetTable(){
        dbController.DropTable();
    }
}
