/*
    *Benjamin Brown
    *Gradebook part 2
    11/29/2021
    */

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class GradebookDB {

    public static String user;
    public static String pass;

    public static void createGradebook(String username, String password) {

        user=username;
        pass=password;


        Connection connection=DBUtil.getConnection(username,password);

        String sql= "CREATE TABLE IF NOT EXISTS Gradebook (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(100) NOT NULL," +
            "letter VARCHAR(1) NOT NULL," +
            "dueDate VARCHAR(100) NOT NULL," +
            "score DECIMAL NOT NULL," +
            "type VARCHAR(10) NOT NULL," +
            "special VARCHAR(100) NOT NULL" +
        ");";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
    }

    public static void insertGrade(AssignmentInterface grade) {

        Connection connection=DBUtil.getConnection(user,pass);

        if (grade instanceof Quiz) {
            try {
            String sql= "INSERT INTO Gradebook" + 
            "(name,letter,dueDate,score,type,special) " + 
            "VALUES " + 
                "(?,?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, grade.getName());
            ps.setString(2, grade.getLetter()+ " ");
            ps.setString(3,grade.getDueDate().toString());
            ps.setDouble(4, grade.getGrade());
            ps.setString(5, "Quiz");
            ps.setInt(6, ((Quiz) grade).getQuestions());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        
        else if (grade instanceof Discussion) {
            try {
            String sql= "INSERT INTO Gradebook " + 
            "(name,letter,dueDate,score,type,special) " + 
            "VALUES " + 
                "(?<?<?<?<?<?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, grade.getName());
            ps.setString(2,grade.getLetter() + " ");
            ps.setString(3,grade.getDueDate().toString());
            ps.setDouble(4, grade.getGrade());
            ps.setString(5, "Discussion");
            ps.setString(6, ((Discussion) grade).getReading());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        
        else {
            try {
            String sql= "INSERT INTO Gradebook " + 
            "(name,letter,dueDate,score,type,special) " + 
            "VALUES " + 
                "(?<?<?<?<?<?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, grade.getName());
            ps.setString(2,grade.getLetter()+ " ");
            ps.setString(3,grade.getDueDate().toString());
            ps.setDouble(4, grade.getGrade());
            ps.setString(5, "Program");
            ps.setString(6, ((Program) grade).getConcept());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }


    public static ArrayList<AssignmentInterface> getGrades(String username,String password) {

        Connection connection=DBUtil.getConnection(username,password);
        String sql = "SELECT * FROM Gradebook ";

        ArrayList<AssignmentInterface> grades= new ArrayList<>();
        
        try (PreparedStatement ps=connection.prepareStatement(sql); 
            ResultSet rs= ps.executeQuery()) {
                //go through each result
                while (rs.next()) {
                    String name=rs.getString(2);
                    String letter=rs.getString(3);
                    String myDate=rs.getString(4);
                    //convert string to LocalDate
                    LocalDate date=null;
                    try {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
                    date = LocalDate.parse(myDate, dateFormat);
                    } catch (DateTimeParseException d) {
                        System.out.println(d);
                    }
                    double grade=rs.getDouble(5);
                    String type =rs.getString(6);
                    String special = rs.getString(7);
                    if (type.equalsIgnoreCase("Quiz")) {
                        int numQuestions=Integer.parseInt(special);
                        Quiz thisQuiz = new Quiz(grade,letter.charAt(0),name,date,numQuestions);
                        grades.add(thisQuiz);
                    }
                    else if (type.equalsIgnoreCase("Discussion")) {
                        Discussion thisDiscussion = new Discussion(grade,letter.charAt(0),name,date,special);
                        grades.add(thisDiscussion);
                    }
                    else {
                        Program thisProgram = new Program(grade,letter.charAt(0),name,date,special);
                        grades.add(thisProgram);
                    }

                }
                return grades;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

    }
    
}
