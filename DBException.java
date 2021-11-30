/*
 * Benjamin Brown
 * DBException.java
 * Project part 2
 * 11/29/2021
 * 
 * A lot of this file is heavily based off of the canvas DBUtil file
 * */

@SuppressWarnings("serial")
public class DBException extends Exception{
    DBException() {}
    
    DBException(Exception e) {
        super(e);
    }
}
