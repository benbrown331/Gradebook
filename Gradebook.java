/*
    *Benjamin Brown
    *Gradebook part 2
    11/29/2021
    */


import java.io.*;
import java.lang.System;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Gradebook {

    public static void main(String args[]) {

        Scanner sc= new Scanner(System.in);

        ArrayList<AssignmentInterface> gradebook = new ArrayList<>();

        //While loop for program to run until user exit
        int userInput=0;
        int assignmentType=0;
        //Variable to ensure unqique file names
        int numFiles=1;
        while (userInput!=9) { 

            //Prompt user with menu
            System.out.println("\nPlease enter the number associated with the" + 
                " function you would like to execute\n");
            System.out.println("1) Add Grade - Add a grade to gradebook\n" + 
                "2) Remove Grade - Remove a grade from the gradebook\n" + 
                "3) Print Grades - Print all the grades in the gradebook\n" +
                "4) Print to File - Print gradebook contents to external text file\n" + 
                "5) Read From File - Read data from an external text file into program\n" +
                "6) To MySQL - Adds all current grades to AWS database\n" +
                "7) From MySQL - Import any unadded grades to gradebook from AWS\n" +
                "8) MySQL Search - Search for specific grades within AWS database\n" +
                "9) Quit - Exit program\n");

            userInput=sc.nextInt();

            //use switch statement to handle user input
            switch (userInput) {

                //Add grade
                case 1:

                    //Ask user what kind of assignment this is
                    System.out.println("\nWhat kind of assignment is this?\n" +
                        "Enter the number for the corresponding catagory:\n" +
                        "1) Quiz\n" + 
                        "2) Discussion\n" +
                        "3) Program\n");
                    assignmentType=sc.nextInt();

                    //check for valid assignment type jacva

                    //Ask user for pertinent info
                    System.out.println("\nEnter the assignment grade:\n");
                    double grade=sc.nextDouble();

                    System.out.println("\nEnter the letter grade for the assignment:\n");
                    char letter=sc.next().charAt(0);
                    //char letterGrade=letter.charAt(0);

                    System.out.println("\nEnter the name for the assignment:\n");
                    String name=sc.next();

                    System.out.println("\nEnter the due date for the assignment (M/dd/yyyy):\n");
                    //take date as a string
                    String myDate=sc.next();
                    //convert string to LocalDate
                    LocalDate date=null;
                    try {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                    date = LocalDate.parse(myDate, dateFormat);
                    } catch (DateTimeParseException d) {
                        System.out.println(d);
                    }

                    switch (assignmentType) {

                        //Quiz
                        case 1:

                            System.out.println("\nEnter the number of questions:\n");
                            int numQuestions=sc.nextInt();
                            Quiz thisQuiz=new Quiz(grade,letter,name,date,numQuestions);
                            gradebook.add(thisQuiz);
                            break;

                        //Discussion
                        case 2:

                            System.out.println("\nEnter the associated reading:\n");
                            String reading=sc.next();
                            Discussion thisDiscussion=new Discussion(grade, letter, name, date, reading);
                            gradebook.add(thisDiscussion);
                            break;


                        //Program
                        case 3:

                            System.out.println("\nEnter the concept of the program:\n");
                            String concept=sc.next();
                            Program thisProgram=new Program(grade, letter, name, date, concept);
                            gradebook.add(thisProgram);
                            break;

                        //if not in bounds
                        default:
                        System.out.println("Not a valid assignment type.\n");
                        break;
                    }

                    break;

                //Remove grade
                case 2:

                    try { 
                        if (gradebook.isEmpty()) {
                            throw new GradebookEmptyException("The gradebook is empty.\n");
                        }

                    System.out.println("\nWhat is the name of the assignment you" +
                       " would like to remove?\n");
                    String nameCheck=sc.next();

                    //Start loop to search for given name
                    //flag to see if grade gets removed in loop
                    Boolean removed=false;
                    for (int j=0; j<gradebook.size(); j++) {
                        if (gradebook.get(j).getName().equalsIgnoreCase(nameCheck)==true) {
                            System.out.println("\n" + gradebook.get(j).getName() + " has been removed!");
                            //remove given grade
                            gradebook.remove(j);
                            removed=true;
                            break;
                        }
                    }       

                    if (!removed) {
                        throw new InvalidGradeException("Could not find the assignment you entered\n");
                    }

                    //Move all the other elements in array foreward 
                    //for (int j=position; j<i; j++) {
                     //   gradebook.get(j)=gradebook.get(j+1);
                    //}
                    }
                    catch (GradebookEmptyException e) {
                        System.out.println(e);
                        break;
                    }
                    catch (InvalidGradeException f) {
                        System.out.println(f);
                        break;
                    }

                    break;

                //Print grades
                case 3:

                try {
                    if (gradebook.isEmpty()) {
                        throw new GradebookEmptyException();
                    }

                    System.out.println("\nHow would you like to sort the list?\n" + 
                        "1) Score (numeric)\n" + 
                        "2) Letter Grade\n" + 
                        "3) Name (alphabetical)\n" + 
                        "4) Due Date\n");
                    int sortChoice=sc.nextInt();

                    switch (sortChoice) {

                        //sort by number grade
                        case 1:

                            Collections.sort(gradebook,Sorting.scoreSort);
                            break;

                        case 2: 

                            Collections.sort(gradebook,Sorting.letterSort);
                            break;

                        case 3: 

                            Collections.sort(gradebook,Sorting.nameSort);
                            break;

                        case 4:

                            Collections.sort(gradebook,Sorting.dateSort);
                            break;

                        default: 

                            System.out.println("\nNo valid sorting option entered.");
                            break;
                    }

                    
                    //to output date format correctly
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                    for (int j=0; j<gradebook.size(); j++) {
                        System.out.println("Assignment Name: " + gradebook.get(j).getName() +
                        " Grade: " + gradebook.get(j).getGrade() + " Letter Grade: " + 
                        gradebook.get(j).getLetter() + " Due Date: " +
                        gradebook.get(j).getDueDate().format(dateFormat)+ "\n");
                    }
                }
                catch (GradebookEmptyException e) {
                    System.out.println(e);
                    break;
                }
                    break;

                //Print to output file
                case 4:


                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");

                    //write data to file
                    try {
                        String dirString =  "C:/Users/Benjamin/Documents/Computer Science/CPSC 2810/Gradebook/GradeTextFiles";
                        Path dirPath = Paths.get(dirString);
                        if (Files.notExists(dirPath)) {
                            Files.createDirectories(dirPath);
                        }
                        String fileString="gradebook_" + numFiles + ".txt";
                        Path filePath = Paths.get(dirString,fileString);
                        File outFile=filePath.toFile();
                        if (Files.notExists(filePath)) {
                        Files.createFile(filePath);
                        }
                        try (PrintWriter out = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter(outFile)))) {

                            // write data for each gradebook member to file
                            for (int i=0; i<gradebook.size();i++) {

                            //create string of data that does not change based on assignment
                                String mainOutput=gradebook.get(i).getName()+"\t" + gradebook.get(i).getGrade() 
                                 + "\t" + gradebook.get(i).getLetter() + "\t" + 
                                gradebook.get(i).getDueDate().format(dateFormat) + "\t";

                                //print data to file based on assignment
                                if (gradebook.get(i) instanceof Quiz) {
                                    out.print("Quiz\t");
                                    out.print(mainOutput);
                                    out.print(((Quiz) gradebook.get(i)).getQuestions() + "\n");
                                }
                                else if (gradebook.get(i) instanceof Discussion) {
                                    out.print("Discussion\t");
                                    out.print(mainOutput);
                                    out.print(((Discussion) gradebook.get(i)).getReading()+ "\n");
                                }
                                else {
                                    out.print("Program\t");
                                    out.print(mainOutput);
                                    out.print(((Program) gradebook.get(i)).getConcept()+ "\n");
                                }

                     }
                   } catch (IOException e) {
                       System.out.println(e);
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                numFiles++;

                 break;


                //Read in from input file
                case 5:

                    System.out.println("Enter the name of the file to read into the gradebook: ");
                    String userFile=sc.next();

                    String dirString =  "C:/Users/Benjamin/Documents/Computer Science/CPSC 2810/Gradebook/GradeTextFiles";
                    Path dirPath = Paths.get(dirString,userFile);
                    File inFile = dirPath.toFile();

                    //begin reading data
                    if (Files.exists(dirPath)) { 
                        try (BufferedReader in = new BufferedReader(
                                                 new FileReader(inFile))) {
                            
                            String line;
                            while ((line = in.readLine()) != null) {
                                String[] columns=line.split("\t");
                                String newAssignment=columns[0];
                                String newName=columns[1];
                                String newGrade=columns[2];
                                String newLetter=columns[3];
                                String newDate=columns[4];
                                //convert string to LocalDate
                                date=null;
                                try {
                                    dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                                    date = LocalDate.parse(newDate, dateFormat);
                                } catch (DateTimeParseException d) {
                                    System.out.println(d);
                                }
                                
                                if (newAssignment.equalsIgnoreCase("Quiz")) {
                                    String newNumQuestions=columns[5];
                                    Quiz thisQuiz=new Quiz(Double.parseDouble(newGrade),newLetter.charAt(0),newName,date,Integer.parseInt(newNumQuestions));
                                    gradebook.add(thisQuiz);
                                }
                                else if (newAssignment.equalsIgnoreCase("Discussion")) {
                                    String newReading=columns[5];
                                    Discussion thisDiscussion=new Discussion(Double.parseDouble(newGrade),newLetter.charAt(0),newName,date,newReading);
                                    gradebook.add(thisDiscussion);
                                }
                                else {
                                    String newConcept=columns[5];
                                    Program thisProgram=new Program(Double.parseDouble(newGrade),newLetter.charAt(0),newName,date,newConcept);
                                    gradebook.add(thisProgram);
                                }
                                

                            }
                        } catch (IOException e) { // catch IOException
                                System.out.println(e);
                                }
                            }
                            break;

                    

                //Write to MySQL
                case 6:

                    //add all current grades to table called gradebook
                    //ask user for username and password
                    //find correct endpoint
                    //if table does not already exist, create one
                    //Ask user for username and password
                    System.out.println("Enter your username for the database:");
                    String username=sc.next();
                    System.out.println("Enter your password for the database:");
                    String password=sc.next();

                    //after gradebook is created, insert all of the gradebook data
                    GradebookDB.createGradebook(username, password);
                    for (int i=0; i<gradebook.size(); i++) {
                        GradebookDB.insertGrade(gradebook.get(i));
                    }
                    System.out.println("Added to MySQL");

                    break;

                //Read from MySQL
                case 7:

                    System.out.println("Enter your username for the database:");
                    username=sc.next();
                    System.out.println("Enter your password for the database:");
                    password=sc.next();

                    //after gradebook is created, insert all of the gradebook data
                    //return all grades to an array list
                    ArrayList<AssignmentInterface> fromSQL=GradebookDB.getGrades(username,password);

                    //sort both arrays based on name
                    Collections.sort(fromSQL,Sorting.nameSort);
                    Collections.sort(gradebook,Sorting.nameSort);
                    //if gradebook is empty, simply set them equal to each other.
                    if (gradebook.isEmpty()) {
                        gradebook=fromSQL;
                    }
                    else {

                    //add the remaining SQL elements into gradebook
                    for (int i=0; i<fromSQL.size(); i++) {
                        if (fromSQL.get(i).getName().equalsIgnoreCase(gradebook.get(i).getName())) {
                            if (fromSQL.get(i).getGrade()==gradebook.get(i).getGrade()) {
                                if (fromSQL.get(i).getDueDate().isEqual(gradebook.get(i).getDueDate())) {
                                    //if all these conditions are true, entry is identical
                                    //I skipped letter because it would be redundant
                                    break;
                                }
                            }
                        }
                        else {
                        gradebook.add(fromSQL.get(i));
                        }

                    }
                }

                    break;
                    

                //MySQL Search
                case 8:

                System.out.println("What would you like to search for in the database?" + 
                    "Enter the corresponding letter");
                
                //Create menu
                System.out.println("\nMenu: \n");
                System.out.println("a. All quizzes");
                System.out.println("b. All programs");
                System.out.println("c. All discussions");
                System.out.println("d. All grades within a certain score range");
                System.out.println("e. All grades within a certain due date range");
                System.out.println("f. All grades with an even score");

                // get input from user                
                String menuChoice = sc.next();    
                System.out.println();
                menuChoice.toLowerCase();

                System.out.println("Enter your username for the database:");
                username=sc.next();
                System.out.println("Enter your password for the database:");
                password=sc.next();

                //connect tp database
                Connection connection = DBUtil.getConnection(username, password);
                String sql="empty";
                //check for valid entry to run Query
                int valid=0;

                switch (menuChoice.charAt(0)) {

                    case 'a':

        
                    sql = "SELECT * FROM Gradebook WHERE type=\"Quiz\"";
                    valid=1;
                    

                    break;

                    case 'b':

                        sql = "SELECT * FROM Gradebook WHERE type=\"Program\"";
                        valid=1;
        

                    break;

                    case 'c':

                        sql = "SELECT * FROM Gradebook WHERE type=\"Discussion\"";
        
                        valid=1;

                    break;

                    case 'd': 

                        System.out.println("Enter the lower grade boundry:");
                        int grade1= sc.nextInt();
                        System.out.println("Enter the upper grade boundry:");
                        int grade2= sc.nextInt();

                        sql = "SELECT * FROM Gradebook WHERE score between '" +grade1 + "and '" + grade2;
                        valid=1;

                        break;

                        


                    case 'e': 

                        System.out.println("Enter the due date you want results after (yyyy-M-d):");
                        String date1Str= sc.next();
                        System.out.println("Enter the due date you want results before (yyyy-M-d):");
                        String date2Str= sc.next();

                        sql = "SELECT * FROM Gradebook WHERE dueDate between '" +date1Str + "and '" + date2Str;
                        valid=1;
        

                    break;

                        case 'f': 

                        sql = "SELECT * FROM Gradebook WHERE score % 2 = 0";
                        valid=1;

                        break;

                    }
                    if (valid==1) {
                    try (PreparedStatement ps = connection.prepareStatement(sql);
                           ResultSet rs = ps.executeQuery()) {
                                while (rs.next()) {
                                    System.out.println("Name: " + rs.getString("name")
                            + " Score: " + rs.getString("score") + " Letter: " + rs.getString("letter") 
                            + " Due Date: " + rs.getString("dueDate") + " Unique variable: " +rs.getString("special"));
                        }
                        } catch (SQLException e)  {
                        e.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Invalid input");
                    }

                break;
                

                //quit
                case 9: 

                    System.out.println("Thank you for using this gradebook program."
                    + " Goodbye!");
                    break;

                default: 
                    System.out.println("Please enter a valid command\n");
                    break;
            }
        }
        sc.close();
    }

    
}
