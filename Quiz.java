/*
    *Benjamin Brown
    *Gradebook part 1
    */

import java.time.LocalDate;

public class Quiz implements AssignmentInterface {

    //decalare private variables
    
    private double grade;
    private char letter;
    private String name;
    private LocalDate dueDate;
    private int numQuestions;

    //create constructor

    public Quiz(double grade, char letter, String name, LocalDate dueDate, int numQuestions) {
        
        this.grade=grade;
        this.letter=letter;
        this.name=name;
        this.dueDate=dueDate;
        this.numQuestions=numQuestions;

    }

    public Quiz() {}



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

    public int getQuestions() {
        return numQuestions;
    }
    public void setQuestions(int numQuestions) {
        this.numQuestions=numQuestions;
    }

    //toString function for each object type
    public String toString() {
        String message= "Name: " + name + " Score: " + grade
        + " Letter: " + letter + " Due Date: " + dueDate + 
        " Number of Questions " + numQuestions + "\n";

        return message;
    }
    
}
