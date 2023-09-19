package com.example.jason_fleming_assignment_2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class GUI  extends Application {

    private ArrayList<String> collegeYears = new ArrayList<>();
    private ArrayList<Integer> collegeSemester = new ArrayList<>();
    protected TextField enterNameTF = new TextField();
    protected TextField enterIDTF = new TextField();
    protected DatePicker enterDP = new DatePicker();//default date in style yyyy/mm/dd
    protected ComboBox<String> studentYearDD = new ComboBox<>();
    protected ComboBox<Integer> studentSemesterDD = new ComboBox<>();
    protected TextField enterModuleNameTF = new TextField();
    protected TextField enterModuleCodeTF = new TextField();
    protected ComboBox<Integer> moduleSemesterDD = new ComboBox<>();
    protected ComboBox<String> studentIDGradeDropDown;
    protected ComboBox<String> moduleCodeGradeDropDown;
    protected TextField enterGradeTF = new TextField();
    protected Label gradeStudentName = new Label("No student Selected");
    protected Label gradeModuleName = new Label("No Module Selected");
    protected ComboBox<String> studentInfoStudentID = new ComboBox<>();
    protected TextArea studentInfoTA = new TextArea();
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        collegeYears.add("1st");
        collegeYears.add("2nd");
        collegeYears.add("3rd");
        collegeYears.add("4th");
        collegeSemester.add(1);
        collegeSemester.add(2);
        primaryStage.setTitle("MTU Student Record System");
        Controller controller = new Controller(this);
        controller.SetResultsType(1);
        //Tab1
        Label enterName = new Label("Enter Name");
        HBox t1hb1 = new HBox(enterName, enterNameTF);
        t1hb1.setPadding(new Insets(20,0,0,10));
        t1hb1.setSpacing(10);
        Label enterID = new Label("Enter Student ID");
        HBox t1hb2 = new HBox(enterID,enterIDTF);
        t1hb2.setPadding(new Insets(20,0,0,10));
        t1hb2.setSpacing(10);
        Label enterDOB = new Label("Enter Date of Birth");
        enterDP.setValue(LocalDate.of(2005,1,1));
        HBox t1hb3 = new HBox(enterDOB,enterDP);
        t1hb3.setPadding(new Insets(20,0,0,10));
        t1hb3.setSpacing(10);
        Label enterCYear = new Label("Enter students current Year");
        studentYearDD.setItems(FXCollections.observableArrayList(collegeYears));
        HBox t1hb4 = new HBox(enterCYear, studentYearDD);
        t1hb4.setPadding(new Insets(20,0,0,10));
        t1hb4.setSpacing(10);
        Label enterCSemester = new Label("Enter students current semester");
        studentSemesterDD.setItems(FXCollections.observableArrayList(collegeSemester));
        HBox t1hb5 = new HBox(enterCSemester, studentSemesterDD);
        t1hb5.setPadding(new Insets(20,0,0,10));
        t1hb5.setSpacing(10);
        Button addStudentB = new Button("Add");
        Button removeStudentB = new Button("Remove");
        HBox t1hb6 = new HBox(addStudentB,removeStudentB);
        t1hb6.setPadding(new Insets(20,0,0,10));
        t1hb6.setSpacing(10);
        Button memoryLeak = new Button("Memory Leak");
        Button resetStudentTable = new Button("Reset Student Table");
        HBox t1hb7 = new HBox(memoryLeak, resetStudentTable);
        t1hb7.setPadding(new Insets(20,0,0,10));
        t1hb7.setSpacing(10);
        VBox vb1 = new VBox(t1hb1,t1hb2,t1hb3,t1hb4,t1hb5,t1hb6, t1hb7);
        vb1.setMaxWidth(400);
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Students");
        tab1.setContent(vb1);
        tab1.setClosable(false);

        //Tab2
        Label enterModuleName = new Label("Enter Module Name");
        HBox t2hb1 = new HBox(enterModuleName, enterModuleNameTF);
        t2hb1.setPadding(new Insets(20,0,0,10));
        t2hb1.setSpacing(10);
        Label enterModuleCode = new Label("Enter Module Code");
        HBox t2hb2 = new HBox(enterModuleCode, enterModuleCodeTF);
        t2hb2.setPadding(new Insets(20,0,0,10));
        t2hb2.setSpacing(10);
        Label enterModuleSemester = new Label("Enter Module Semester");
        moduleSemesterDD.setItems(FXCollections.observableArrayList(collegeSemester));
        HBox t2hb3 = new HBox(enterModuleSemester, moduleSemesterDD);
        t2hb3.setPadding(new Insets(20,0,0,10));
        t2hb3.setSpacing(10);
        Button addModuleB = new Button("Add");
        Button removeModuleB = new Button("Remove");
        HBox t2hb4 = new HBox(addModuleB, removeModuleB);
        t2hb4.setPadding(new Insets(20,0,0,10));
        t2hb4.setSpacing(10);
        VBox vb2 = new VBox(t2hb1, t2hb2,t2hb3, t2hb4);
        Tab tab2 = new Tab("Modules");
        tab2.setContent(vb2);
        tab2.setClosable(false);

        //Tab3
        Label gradeStudentNumber = new Label("Select Student Number");
        studentIDGradeDropDown = new ComboBox<>(FXCollections.observableArrayList(controller.GetStudentNumbers()));
        HBox t3hb1 = new HBox(gradeStudentNumber, studentIDGradeDropDown);
        t3hb1.setPadding(new Insets(20,0,0,10));
        t3hb1.setSpacing(10);
        HBox t3hb2 = new HBox(gradeStudentName);
        t3hb2.setPadding(new Insets(20,0,0,10));
        t3hb2.setSpacing(10);
        Label gradeModuleCode = new Label("Select Module Code");
        moduleCodeGradeDropDown = new ComboBox<>(FXCollections.observableArrayList(controller.GetModuleCodes()));
        HBox t3hb3 = new HBox(gradeModuleCode, moduleCodeGradeDropDown);
        t3hb3.setPadding(new Insets(20,0,0,10));
        t3hb3.setSpacing(10);
        HBox t3hb4 = new HBox(gradeModuleName);
        t3hb4.setPadding(new Insets(20,0,0,10));
        t3hb4.setSpacing(10);
        Label gradeSemester = new Label("Enter Grade");
        HBox t3hb5 = new HBox(gradeSemester, enterGradeTF);
        t3hb5.setPadding(new Insets(20,0,0,10));
        t3hb5.setSpacing(10);
        Button gradeEnterB = new Button("Enter");
        Button gradeModifyB = new Button("Modify");
        Button gradeRemoveB = new Button("Remove");
        HBox t3hb6 = new HBox(gradeEnterB, gradeModifyB, gradeRemoveB);
        t3hb6.setPadding(new Insets(20,0,0,10));
        t3hb6.setSpacing(10);
        VBox vb3 = new VBox(t3hb1,t3hb2, t3hb3, t3hb4, t3hb5, t3hb6);
        Tab tab3 = new Tab("Grades");
        tab3.setContent(vb3);
        tab3.setClosable(false);

        //Tab4
        Label studentInfoSelectStudent = new Label("Select Student: ");
        studentInfoStudentID.setItems(FXCollections.observableArrayList(controller.GetStudentNumbers()));
        HBox t4hb1 = new HBox(studentInfoSelectStudent, studentInfoStudentID);
        t4hb1.setPadding(new Insets(20,0,0,10));
        t4hb1.setSpacing(10);
        ToggleGroup choiceGroup = new ToggleGroup();
        RadioButton passedModules = new RadioButton("Passed Module");
        RadioButton failedModules = new RadioButton("Failed Modules");
        RadioButton allModules = new RadioButton("All Modules");
        passedModules.setToggleGroup(choiceGroup);
        failedModules.setToggleGroup(choiceGroup);
        allModules.setToggleGroup(choiceGroup);
        passedModules.setSelected(true);
        HBox t4hb2 = new HBox(passedModules, failedModules, allModules);
        t4hb2.setPadding(new Insets(20,0,0,10));
        t4hb2.setSpacing(10);
        studentInfoTA.setEditable(false);
        studentInfoTA.setPrefWidth(380);
        studentInfoTA.setPrefHeight(250);
        HBox t4hb3 = new HBox(studentInfoTA);
        t4hb3.setPadding(new Insets(20,0,0,10));
        t4hb3.setSpacing(10);
        Button showResults = new Button("Show Results");
        HBox t4hb4 = new HBox(showResults);
        t4hb4.setPadding(new Insets(20,0,0,10));
        t4hb4.setSpacing(10);
        VBox vb4 = new VBox(t4hb1, t4hb2, t4hb3, t4hb4);
        Tab tab4 = new Tab("Student Info");
        tab4.setContent(vb4);
        tab4.setClosable(false);
        //
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        BorderPane root = new BorderPane();
        root.setCenter(tabPane);
        root.getChildren().add(vb1);
        Scene scene = new Scene(root, 400, 550);
        primaryStage.setScene(scene);
        primaryStage.show();


        //Actions
        //Tab1
        addStudentB.setOnAction(e -> controller.AddStudent());
        removeStudentB.setOnAction(e -> controller.RemoveStudent());
        memoryLeak.setOnAction(e -> controller.MemoryLeak());
        resetStudentTable.setOnAction(e -> controller.ResetTable());

        //Tab2
        addModuleB.setOnAction(e -> controller.AddModule());
        removeModuleB.setOnAction(e -> controller.RemoveModule());

        //Tab3
        studentIDGradeDropDown.setOnAction(e -> controller.GetStudentName());
        moduleCodeGradeDropDown.setOnAction(e -> controller.GetModuleName());
        gradeEnterB.setOnAction(e -> controller.AddGrade());
        gradeModifyB.setOnAction(e -> controller.ModifyGrade());
        gradeRemoveB.setOnAction(e -> controller.DeleteGrade());

        //Tab4
        passedModules.setOnAction(e -> controller.SetResultsType(1));
        failedModules.setOnAction(e -> controller.SetResultsType(2));
        allModules.setOnAction(e -> controller.SetResultsType(3));
        showResults.setOnAction(e -> controller.ShowResultType());
    }
}
