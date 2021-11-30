/*
    *Benjamin Brown
    *Gradebook part 2
    11/29/2021
    */

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradebookDB {

    public static String user;
    public static String pass;

    public static void createGradebook(String username, String password) {

        user=username;
        pass=password;


        Connection connection=DBUtil.getConnection(username,password);

        StringBuilder sql=new StringBuilder("CREATE TABLE IF NOT EXISTS Gradebook");
        sql.append(" name VARCHAR(100), ");
        sql.append(" letter VARCHAR(1), ");
        sql.append(" dueDate VARCHAR(100), ");
        sql.append(" score DOUBLE(64), ");
        sql.append(" type VARCHAR(10), ");
        sql.append(" special VARCHAR(100), ");

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
    }

    public static void insertGrade(AssignmentInterface grade) {

        Connection connection=DBUtil.getConnection(user,pass);

        if (grade instanceof Quiz) {
            try {
            String sql= "INSERT INTO Gradebook " + 
            "(name,letter,dueDate,score,type,special) " + 
            "VALUES " + 
                "(?<?<?<?<?<?)";
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
            ps.setString(5, "Discussion");
            ps.setString(6, ((Discussion) grade).getReading());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }
    
}
