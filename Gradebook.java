/*
    *Benjamin Brown
    *Gradebook part 1
    */


import java.lang.System;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Gradebook {

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

                    try {
                        if (i==thisArray.length) {
                            throw new GradebookFullException("You cannot add a grade, the gradebook is full\n");
                        }


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
                            Quiz thisQuiz=new Quiz(grade,letter,name,date,numQuestions);
                            thisArray[i]=thisQuiz;
                            i++;
                            break;

                        //Discussion
                        case 2:

                            System.out.println("Enter the associated reading\n");
                            String reading=sc.nextLine();
                            Discussion thisDiscussion=new Discussion(grade, letter, name, date, reading);
                            thisArray[i]=thisDiscussion;
                            i++;
                            break;


                        //Program
                        case 3:

                            System.out.println("Enter the concept of the program\n");
                            String concept=sc.nextLine();
                            Program thisProgram=new Program(grade, letter, name, date, concept);
                            thisArray[i]=thisProgram;
                            i++;
                            break;

                        //if not in bounds
                        default:
                        System.out.println("Not a valid assignment type.\n");
                        break;
                    }
                }
                    catch (GradebookFullException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                //Remove grade
                case 2:

                    try { 
                        if (i==0) {
                            throw new GradebookEmptyException("The gradebook is empty.\n");
                        }

                    System.out.println("What is the name of the assignment you" +
                       " would like to remove?\n");
                    String nameCheck=sc.nextLine();

                    //Start loop to search for given name

                    int position=-1;
                    for (int j=0; j<thisArray.length; j++) {
                        if (thisArray[j].getName().equals(nameCheck)==true) {
                            position=j;
                            break;
                        }
                    }       

                    if (position==-1) {
                        throw new InvalidGradeException("Could not find the assignment you entered\n");
                    }

                    //Move all the other elements in array foreward 
                    for (int j=position; j<thisArray.length-1; j++) {
                        thisArray[j]=thisArray[j+1];
                    }
                    }
                    catch (GradebookEmptyException e) {
                        System.out.println(e);
                    }
                    catch (InvalidGradeException f) {
                        System.out.println(f);
                    }

                    break;

                //Print grades
                case 3:

                    for (int j=0; j<thisArray.length; j++) {
                        System.out.println("Assignment Name: " + thisArray[j].getName() +
                        "Grade: " + thisArray[j].getGrade() + "\n");
                    }
                    break;

                //Print Average
                case 4:

                    double sum=0;
                    for (int j=0; j<thisArray.length; j++) {
                        sum=sum+thisArray[j].getGrade();
                    }
                    double average=(double)(sum/thisArray.length);
                    System.out.println("Average: " + average + "\n");
                    break;

                //Print high/low
                case 5:

                    double high=0;
                    double low=0;

                    for (int j=0; j<thisArray.length; j++) {
                        if (thisArray[j].getGrade()<low) {
                            low=thisArray[j].getGrade();
                        }
                        if (thisArray[j].getGrade()>high) {
                            high=thisArray[j].getGrade();
                        }
                    }

                    System.out.println("Highest Score: " + high + "\n" +
                    "Lowest Score: " + low + "\n");
                    break;

                //Print quiz average
                case 6:

                    double quizSum=0;
                    int numQuizzes=0;
                    
                    for (int j=0; j<thisArray.length; j++) {
                        if (thisArray[j] instanceof Quiz) {
                            quizSum=quizSum + ((Quiz)thisArray[j]).getQuestions();
                            numQuizzes++;
                        }
                    }

                    double quizAverage=(double)(quizSum/numQuizzes);
                    System.out.println("Average number of quiz questions: " + 
                        quizAverage + "\n");

                    break;

                //print discussion readings
                case 7:

                    for (int j=0; j<thisArray.length; j++) {
                        if (thisArray[j] instanceof Discussion) {
                            System.out.println(((Discussion)thisArray[j]).getReading() + "\n");
                        }
                    }

                    break;
                    

                //Print program concepts
                case 8:

                    for (int j=0; j<thisArray.length; j++) {
                        if (thisArray[j] instanceof Program) {
                            System.out.println(((Program)thisArray[j]).getConcept() + "\n");
                        }
                    }

                    break;

                //quit
                case 9: 
                    break;

                default: 
                    System.out.println("Please enter a valid command\n");
                    break;
            }
        }
        sc.close();
    }

    
}
