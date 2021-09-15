//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane  
 //********************************************************************

//package project;

import java.time.LocalDate;

public class Edit {
	
	/*
	 * This method will call the getOpenFile in CSV file chooser then
	 * return the string to the csv file the user chose to open
	 */
	public static String getOpenPath() {
		try {
			return new CSVFile().getOpenFile().getAbsolutePath().toString();
		} catch(java.lang.NullPointerException e) {
			return null;
		}
	}
	
	/*
	 * This method will call the getSaveFile in CSV file choser
	 * and will return the string path where the user wants to save the file
	 */
	public static String getSavePath() {
		try {
			return new CSVFile().getSaveFile().getAbsolutePath().toString();
		} catch(java.lang.NullPointerException e) {
			return null;
		}
	}
	
	/*
	 * This method will call the load function in Repository
	 */
	public static void loadTable(String filepath) {
        Project.all.load(filepath);
	}
	
	/*
	 * saves the table
	 */
    public static void saveTable(String filepath) {
        Project.all.save(filepath);
    }
	
    /*
     * this method will call the datePickerGUI from DatePicker class
     */
	public static void displayDatePicker(String filepath) {
        TimeSetter date = new TimeSetter();
        date.datePickerGUI(filepath);
    }
    
	/*
	 * this method: call the addStudentAttendance function 
	 * 				call and displays the output of the added attendance
	 */
    public static void saveDate(int month, int day, int year, String filepath) {

    	LocalDate time = LocalDate.of(year,  month, day);
    	Project.all.addStudentAttendance(time, filepath);

    
    	Show show = new Show();
    	show.displayAttendanceResult(Repository.extraStudents, Repository.studentsAdded);
    }
    
    /*
     * this will call the Display then displpays error if the roster hdoesn't loaded before 
     * the other functions get called
     */
    public static void showEmptyRosterError() {
    	// Display error for when the roster has not been loaded
    	Show dis = new Show();
    	dis.emptyRosterErrorHandler();
    }
    
    /*
     * shows the the scatter plot GUI
     */
    public static void showScatterPlot() {
    	Graph.graphPlotGUI();
    } 
    
    /**
     * this will display dialogue box about team info
     */
    public static void showAboutTab() {
        Show show = new Show();
        show.displayTeamInfo();
    }
}