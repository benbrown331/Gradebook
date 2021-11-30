/*
    *Benjamin Brown
    *Gradebook part 2
    11/29/2021
    */

import java.util.Comparator;

public class Sorting {
    
    public static Comparator<AssignmentInterface> scoreSort = new Comparator<AssignmentInterface>() {

        public int compare(AssignmentInterface grade1, AssignmentInterface grade2) {

                //ascending (0->100)
  
                  if (grade1.getGrade()==grade2.getGrade()) {
                      return 0;
                  }
                  else if (grade1.getGrade()>grade2.getGrade()) {
                      return 1;
                  }
                  else {
                      return -1;
                  }
                  }
    };

    public static Comparator<AssignmentInterface> letterSort = new Comparator<AssignmentInterface>() {
        //A->Z
        public int compare(AssignmentInterface grade1, AssignmentInterface grade2) {

            char letter1=grade1.getLetter();
            char letter2=grade2.getLetter();
            Character.toUpperCase(letter1);
            Character.toUpperCase(letter2);
  
                  if (letter1==letter2) {
                      return 0;
                  }
                  else if (letter1>letter2) {
                      return 1;
                  }
                  else {
                      return -1;
                  }
                  }
    };

    public static Comparator<AssignmentInterface> nameSort = new Comparator<AssignmentInterface>() {
        //A->Z
        public int compare(AssignmentInterface grade1, AssignmentInterface grade2) {

            String name1=grade1.getName().toUpperCase();
            String name2=grade2.getName().toUpperCase();
  
                  if (name1==name2) {
                      return 0;
                  }
                  else if (name1.compareToIgnoreCase(name2)>0) {
                      return 1;
                  }
                  else {
                      return -1;
                  }
                  }
    };

    public static Comparator<AssignmentInterface> dateSort = new Comparator<AssignmentInterface>() {
        //present->past
        public int compare(AssignmentInterface grade1, AssignmentInterface grade2) {

            return grade2.getDueDate().compareTo(grade1.getDueDate());
                  }
    };


}
