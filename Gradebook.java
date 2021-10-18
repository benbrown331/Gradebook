import java.lang.System;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Gradebook  {

    public static void main(String args[]) {

        Scanner sc= new Scanner(System.in);

        System.out.println("Welcome to your gradebook!\n Enter the size of your gradebook (up to 20 grades)\n");
        int size=sc.nextInt();

        AssignmentInterface[] thisArray;
        thisArray=new AssignmentInterface[size];

        //While loop for program to run until user exit
        int i=0; //index gradebook
        int userInput=0;
        int assignmentType=0;
        while (userInput!=9) { 

            //Prompt user with menu
            System.out.println("Please enter the number associated with the" + 
                " function you would like to execute\n");
            System.out.println("1) Add Grade - Add a grade to gradebook\n" + 
                "2) Remove Grade - Remove a grade from the gradebook\n" + 
                "3) Print Grades - Print all the grades in the gradebook\n" +
                "4) Print Average - Print the average of all the grades in the gradebook\n" + 
                "5) Print High/Low Scores - Print the higest and lowest score in gradebook\n" +
                "6) Print Quiz Question Average - Print average number of quiz questions\n" +
                "7) Print Discussion Readings - Prints the reading associated with each discussion\n" +
                "8) Print Program Concepts - Print the concept associated with each program\n" +
                "9) Quit - Exit program\n");

            userInput=sc.nextInt();

            //use switch statement to handle user input
            switch (userInput) {

                //Add grade
                case 1:

                    //Ask user what kind of assignment this is
                    System.out.println("What kind of assignment is this?\n" +
                        "enter the number for the corresponding catagory\n" +
                        "1) Quiz\n" + 
                        "2) Discussion\n" +
                        "3) Program\n");
                    assignmentType=sc.nextInt();

                    //Ask user for pertinent info
                    System.out.println("Enter the assignment grade\n");
                    double grade=sc.nextDouble();

                    System.out.println("Enter the letter grade for the assignment\n");
                    char letter=sc.next().charAt(0);

                    System.out.println("Enter the name for the assignment\n");
                    String name=sc.nextLine();

                    System.out.println("Enter the due date for the assignment\n");
                    //take date as a string
                    String myDate=sc.nextLine();
                    //convert string to LocalDate
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                    LocalDate date = LocalDate.parse(myDate, dateFormat);

                    switch (assignmentType) {

                        //Quiz
                        case 1:

                            System.out.println("Enter the number of questions\n");
                            int numQuestions=sc.nextInt();
                            Quiz myQuiz=myQuiz(grade,letter,name,date,numQuestions);


                        //Discussion
                        case 2:

                        //Program
                        case 3:

                        //if not in bounds
                        default:
                        System.out.println("Not a valid assignment type.\n");
                        break;
                    }

                //Remove grade
                case 2:

                //Print grades
                case 3:

                //Print Average
                case 4:

                //Print high/low
                case 5:

                //Print quiz average
                case 6:

                //print discussion readings
                case 7:

                //Print program concepts
                case 8:

                //quit
                case 9: 
                    break;

                default: 
                    System.out.println("Please enter a valud command\n");
                    break;
            }

        


    //array of assignment interface
    //ask user for gradebook size
    // initialize gradebook
    //prompt user with menu
        //add grades
            //ask for type (quiz, discussion, ect)
            //ask for rest of data grade, name ect
        //remove grades
            //remove first instance of a grade
            //if grade does not exist, throw InvalidGradeException
            //if empty, throw GBookEmptyException
        //print grades
            //print all grades
            //GBookEmptyException
        //Print average
        //print highest/lowest
            //if only 1 grade, it is high and low
            //GBookEmptyException
        //Quiz question average
        //discussion topics print
        //print program concepts

        //put all in a loop until user enters 'q'
        }

    
}
