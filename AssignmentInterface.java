/*
    *Benjamin Brown
    *Gradebook part 2
    11/29/2021
    */

import java.time.LocalDate;

public interface AssignmentInterface {

    //get and set for number grade
    double getGrade();
    void setGrade(double grade);

    //get and set for letter grade
    char getLetter();
    void setLetter(char letter);

    //get and set for name
    String getName();
    void setName(String name);

    //get/set for due date

    LocalDate getDueDate();
    void setDueDate(LocalDate dueDate);

    //toString function for each object type
    String toString();

    
}
