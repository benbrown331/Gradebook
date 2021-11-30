/*
    *Benjamin Brown
    *Gradebook part 2
    11/29/2021
    */

import java.time.LocalDate;

public class Discussion implements AssignmentInterface {

    private double grade;
    private char letter;
    private String name;
    private LocalDate dueDate;
    private String reading;

    //create constructor

    public Discussion(double grade, char letter, String name, LocalDate dueDate, String reading) {
        
        this.grade=grade;
        this.letter=letter;
        this.name=name;
        this.dueDate=dueDate;
        this.reading=reading;

    }

    public Discussion() {}



    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade=grade;
    }

    //get and set for letter grade
    public char getLetter() {
        return letter;
    }
    public void setLetter(char letter) {
        this.letter=letter;
    }

    //get and set for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }

    //get/set for due date

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate=dueDate;
    }

    public String getReading() {
        return reading;
    }
    public void setReading(String reading) {
        this.reading=reading;
    }

    //toString function for each object type
    public String toString() {
        String message= "Name: " + name + " Score: " + grade
        + " Letter: " + letter + " Due Date: " + dueDate + 
        " Reading: " + reading + "\n";

        return message;
    }
    
}
