package com.example.jason_fleming_assignment_2;

import javafx.collections.FXCollections;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseController {
    private Controller controller;
    private GUI gui;
    public DatabaseController(GUI gui, Controller controller){
        this.gui = gui;
        this.controller = controller;
        ConnectToDatabase();
    }
    public void ConnectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
    }


    public void AddStudent(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL, user, password);
            Statement statement = databaseConnection.createStatement();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");//this is used to format the date in the style yyyy/mm/dd for mySQL
            String DOB = gui.enterDP.getValue().format(formatter);
            String sqlInsert = "insert into students values(?,?,?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, gui.enterIDTF.getText());
            preparedStatement.setString(2, gui.enterNameTF.getText());
            preparedStatement.setString(3, DOB);
            preparedStatement.setString(4, gui.studentYearDD.getValue());
            preparedStatement.setInt(5, gui.studentSemesterDD.getValue());
            preparedStatement.execute();
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe);
        } catch (SQLException sqle) {
            controller.CreateErrorBox("Sorry that Student ID already exists");
            System.err.println(sqle);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        gui.studentIDGradeDropDown.setItems(FXCollections.observableArrayList(GetStudentNumbers()));
        gui.studentInfoStudentID.setItems(FXCollections.observableArrayList(GetStudentNumbers()));

    }
    public void RemoveStudent(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL, user, password);
            String sqlQuery = "DELETE FROM students Where StudentID = ?";
            PreparedStatement statement = databaseConnection.prepareStatement(sqlQuery);
            statement.setString(1, gui.enterIDTF.getText());
            statement.executeUpdate();
        }catch (ClassNotFoundException cnfe) {
        System.err.println(cnfe); }
        catch (SQLException sqle) {
        System.err.println(sqle);} catch (InstantiationException e) {
        throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
    }
        gui.studentIDGradeDropDown.setItems(FXCollections.observableArrayList(GetStudentNumbers()));
        gui.studentInfoStudentID.setItems(FXCollections.observableArrayList(GetStudentNumbers()));
    }

    //Tab2
    public void AddModule(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlInsert = "insert into modules values(?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1,gui.enterModuleCodeTF.getText());
            preparedStatement.setString(2,gui.enterModuleNameTF.getText());
            preparedStatement.setInt(3, gui.moduleSemesterDD.getValue());
            preparedStatement.execute();
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        gui.moduleCodeGradeDropDown.setItems(FXCollections.observableArrayList(GetModuleCodes()));
    }
    public void RemoveModule(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password);
            String sqlQuery = "DELETE FROM modules Where ModuleCode = ?";
            PreparedStatement statement = databaseConnection.prepareStatement(sqlQuery);
            statement.setString(1, gui.enterModuleCodeTF.getText());
            statement.executeUpdate();
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        gui.moduleCodeGradeDropDown.setItems(FXCollections.observableArrayList(GetModuleCodes()));
    }

    //Tab3
    public void AddGrade(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password);
            String sqlInsert = "insert into studentgrades values(?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1,gui.studentIDGradeDropDown.getValue());
            preparedStatement.setString(2,gui.moduleCodeGradeDropDown.getValue());
            preparedStatement.setString(3, gui.enterGradeTF.getText());
            preparedStatement.execute();
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            controller.CreateErrorBox("Sorry there is already a grade inputted for that student ID and Module Code");
            System.err.println(sqle);}
        controller.ShowResultType();
    }
    public void ModifyGrade(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlInsert = "UPDATE studentgrades SET Grade = ? Where StudentID = ? and ModuleCode = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1,gui.enterGradeTF.getText());
            preparedStatement.setString(2,gui.studentIDGradeDropDown.getValue());
            preparedStatement.setString(3, gui.moduleCodeGradeDropDown.getValue());
            preparedStatement.execute();
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        controller.ShowResultType();
    }
    public void DeleteGrade(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password);
            String sqlQuery = "DELETE FROM studentgrades Where StudentID = ? and ModuleCode = ?";
            PreparedStatement statement = databaseConnection.prepareStatement(sqlQuery);
            statement.setString(1, gui.studentIDGradeDropDown.getValue());
            statement.setString(2, gui.moduleCodeGradeDropDown.getValue());
            statement.executeUpdate();
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        controller.ShowResultType();
    }

    //Tab4
    public ArrayList<String> GetPassedGrades(){
        ArrayList<String> passedModules = new ArrayList<>();
        ArrayList<String> moduleCodes = new ArrayList<>();
        ArrayList<String> moduleNames = new ArrayList<>();
        ArrayList<String> grades = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlQuery = "SELECT * FROM studentgrades WHERE StudentID = ? and (Grade != 'NP' and Grade >= '40')";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, gui.studentInfoStudentID.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                moduleCodes.add(rs.getString("ModuleCode"));
                grades.add(rs.getString("Grade"));
            }
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        if (moduleCodes.size() > 0) {
            for (String modules : moduleCodes)
                moduleNames.add(GetModuleName(modules));
            for (int i = 0; i < moduleCodes.size(); i++) {
                passedModules.add("\nModule: " + moduleNames.get(i) + "\t\t" + "Grades: " + grades.get(i));
            }
        } else
            passedModules.add("No Grades given to this Student");
        return passedModules;
    }
    public ArrayList<String> GetFailedGrades(){
        ArrayList<String> failedModules = new ArrayList<>();
        ArrayList<String> moduleCodes = new ArrayList<>();
        ArrayList<String> moduleNames = new ArrayList<>();
        ArrayList<String> grades = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlQuery = "SELECT * FROM studentgrades WHERE StudentID = ? and (Grade < '40' or Grade = 'NP')";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, gui.studentInfoStudentID.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                moduleCodes.add(rs.getString("ModuleCode"));
                grades.add(rs.getString("Grade"));
            }
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        if (moduleCodes.size() > 0) {
            for (String modules : moduleCodes)
                moduleNames.add(GetModuleName(modules));
            for (int i = 0; i < moduleCodes.size(); i++) {
                failedModules.add("\nModule: " + moduleNames.get(i) + "\t\t" + "Grades: " + grades.get(i));
            }
        } else
            failedModules.add("No Grades given to this Student");
        return failedModules;
    }
    public ArrayList<String> GetAllGrades(){
        ArrayList<String> allModules = new ArrayList<>();
        ArrayList<String> moduleCodes = new ArrayList<>();
        ArrayList<String> moduleNames = new ArrayList<>();
        ArrayList<String> grades = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlQuery = "SELECT * FROM studentgrades WHERE StudentID = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, gui.studentInfoStudentID.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                moduleCodes.add(rs.getString("ModuleCode"));
                grades.add(rs.getString("Grade"));
            }
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        if (moduleCodes.size() > 0) {
            for (String modules : moduleCodes)
                moduleNames.add(GetModuleName(modules));
            for (int i = 0; i < moduleCodes.size(); i++) {
                allModules.add("\nModule: " + moduleNames.get(i) + "\t\t" + "Grades: " + grades.get(i));
            }
        }
        else
            allModules.add("No Grades given to this Student");
        return allModules;
    }
    public String GetStudentInfo(){
        String studentInfo = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlQuery = "SELECT * FROM students WHERE StudentID = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, gui.studentInfoStudentID.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                studentInfo += "Name: " + rs.getString("Name") + "\nStudentID: " + rs.getString("StudentID") + "\nDate of Birth: " + rs.getDate("DateOfBirth") + "\nYear: " + rs.getString("CurrentYear") + "\nSemester: " + rs.getInt("CurrentSemester") + "\n";
            }
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        return studentInfo;
    }

    //Mutual Functions
    public ArrayList<String> GetStudentNumbers() {
        ArrayList<String> studentNumbers = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL, user, password);
            String sqlQuery = "Select studentID from students";
            try (Statement stmt = databaseConnection.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    studentNumbers.add(rs.getString("studentID"));
                }
            } catch (SQLException sqle) {
                System.err.println(sqle);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return studentNumbers;
    }
    public ArrayList<String> GetModuleCodes() {
        ArrayList<String> moduleCodes = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL, user, password);
            String sqlQuery = "Select ModuleCode from Modules";
            try (Statement stmt = databaseConnection.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    moduleCodes.add(rs.getString("ModuleCode"));
                }
            } catch (SQLException sqle) {
                System.err.println(sqle);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return moduleCodes;
    }
    public String GetStudentName(){
        String studentName = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlInsert = "SELECT Name From students where StudentID = ?";
            //statement.execute(sqlInsert);
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1,gui.studentIDGradeDropDown.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                studentName = rs.getString("name");
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        return studentName;
    }
    public String GetModuleName(){
        String moduleName = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlInsert = "SELECT ModuleName From Modules where ModuleCode = ?";
            //statement.execute(sqlInsert);
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1,gui.moduleCodeGradeDropDown.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                moduleName = rs.getString("ModuleName");
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        return moduleName;
    }
    public String GetModuleName(String moduleCode){
        String moduleName = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlInsert = "SELECT ModuleName From Modules where ModuleCode = ?";
            //statement.execute(sqlInsert);
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1,moduleCode);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                moduleName = rs.getString("ModuleName");
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
        return moduleName;
    }
    //Memory Leak functions
    public void DropTable(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
                String user = "root";
                String password = "root";
                Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
                Statement statement = databaseConnection.createStatement();
                String sqlQuery = "Drop Table students";
                statement.execute(sqlQuery);
            }catch (ClassNotFoundException cnfe) {
                System.err.println(cnfe); }
            catch (SQLException sqle) {
                System.err.println(sqle);}
            RecreateTable();
        }
    public void RecreateTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sourceURL = "jdbc:mysql://localhost:3306/jason_fleming_oop_assignment2";
            String user = "root";
            String password = "root";
            Connection databaseConnection = DriverManager.getConnection(sourceURL,user, password );
            String sqlQuery = "Create Table students (StudentID varchar(255) NOT NULL, Name varchar(255) Not NUll, DateOfBirth Date NOT NULL, CurrentYear varchar(255) Not Null, CurrentSemester int Not Null, Primary Key(StudentID))";
            Statement statement = databaseConnection.createStatement();
            statement.execute(sqlQuery);
        }catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe); }
        catch (SQLException sqle) {
            System.err.println(sqle);}
    }
}
