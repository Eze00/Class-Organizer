//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane
 //********************************************************************

//package project;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Student {

    // A map to hash the different date too
    private HashMap<LocalDate, Integer> attendance;

    // All the different string we will need for our tables
    private String ID;
    private String firstName;
    private String lastName;
    private String program;
    private String level;
    private String ASURITE;

    /*
     * The student constructor taking 6 different strings
     * to create a new student that we will hash in the table.
     */
    public Student(
            String ID,
            String firstName,
            String lastName,
            String program,
            String level,
            String ASURITE) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.level = level;
        this.ASURITE = ASURITE;
        this.attendance = new LinkedHashMap();
    }

    /*
     * Using a date and a time, we are able to add to the attendance by calling 
     * the function put, and if there is a duplicate it will just add to it.
     */
    public void addAttendance(LocalDate date, int timer) {
        attendance.put(date, attendance.getOrDefault(date, 0) + timer);
    }

    /*
     * Will return the attendance at a certain date
     */
    public int getDateAttendance(LocalDate date) {
        return attendance.get(date);
    }

    /*
     * Gets the attendance mapping for the particular student and return its
     */
    public HashMap<LocalDate, Integer> getAttendance() {
        return new LinkedHashMap(attendance);
    }

    /*
     *  Get the id of a student and return it	
     */
    public String getID() {
        return this.ID;
    }

    /*
     * Set a new ID to this specific student   	
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /*
     * Get the first name of the student and return it
     */
    public String getFirstName() {
        return this.firstName;
    }

    /*
     * Set this student name to a new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /*
     * Get the last name of this student and return it
     */
    public String getLastName() {
        return this.lastName;
    }

    /*
     * Change the last name of this student to the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
	 * Get the student program and return it 
     */
    public String getProgram() {
        return program;
    }

    /*
	 * Set the student program to the new program
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /*
     *	Get the student level and return it
     */
    public String getLevel() {
        return level;
    }

    /*
     * Set the student level to the new level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /*
     * Get the asurite and return it
     */
    public String getASURITE() {
        return this.ASURITE;
    }

    /*
     * Set the student asurite to the new asurite
     */
    public void setASURITE(String ASURITE) {
        this.ASURITE = ASURITE;
    }
}