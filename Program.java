import java.time.LocalDate;

public class Program implements AssignmentInterface{

    private double grade;
    private char letter;
    private String name;
    private LocalDate dueDate;
    private String concept;

    //create constructor

    public Program(double grade, char letter, String name, LocalDate dueDate, String concept) {
        
        this.grade=grade;
        this.letter=letter;
        this.name=name;
        this.dueDate=dueDate;
        this.concept=concept;

    }



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

    public String getConcept() {
        return concept;
    }
    public void setConcept(String concept) {
        this.concept=concept;
    }

    //toString function for each object type
    public String toString() {
        String message= "Name: " + name + " Score: " + grade
        + " Letter: " + letter + " Due Date: " + dueDate + 
        " Concept: " + concept + "\n";

        return message;
    }
    
}
