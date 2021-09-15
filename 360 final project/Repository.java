//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane
 //********************************************************************

//package project;

import java.io.*;
import java.time.LocalDate; // import the LocalDate class
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;


@SuppressWarnings("deprecation")
public class Repository extends Observable {

    public static List<Student> rost;
    public static List<String> head;
    public static int studentsAdded = 0;
    public static LinkedHashMap<String, Integer> extraStudents;
    public static List<LocalDate> dates;

    public static final String delimiter = ",";
    public static final int basehead = 6;

    public Repository() {
        head = new ArrayList();
        head.add("ID");
        head.add("First Name");
        head.add("Last Name");
        head.add("Program");
        head.add("Level");
        head.add("ASURITE");
        extraStudents = new LinkedHashMap();
        dates = new ArrayList<LocalDate>();
    }

    /*
     * Reads from the csvFile path passed as a parameter line by line adding each student to the
     * rost.
     *
     * @param csvFile path to rost csv file
     */
    public void read(String csvFile) {
        List<Student> studentList = new ArrayList();

        try {

            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String[] studentAttributes;

            line = br.readLine();
            studentAttributes = line.split(delimiter);

            if (studentAttributes[0].equals("ID")) {
                for (int i = head.size(); i < studentAttributes.length; i++) {
                    head.add(studentAttributes[i]);
                }
            } else {
                studentList.add(createStudent(studentAttributes));
            }

            while ((line = br.readLine()) != null) {
                studentAttributes = line.split(delimiter);
                studentList.add(createStudent(studentAttributes));
            }

            br.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        rost = studentList;
    }

    /**
     * Calls the read file function and notifies observers as per the observable-observer pattern
     *
     * @param csvInputFile path to rost csv file
     */
    public void load(String csvInputFile) {

        head = head.subList(0, basehead); // reset to default head

        this.read(csvInputFile);
        setChanged();
        notifyObservers();
    }

    /**
     * createStudent takes in an array of strings,
     * each entry corresponding to an attribute of
     * the student read in a line of the input csv.
     * The method will use these attributes to
     * construct a Student object and return it as stu.
     *
     * @param attributes String[]
     * @return Student
     *
     */
    public Student createStudent(String[] attributes) {
        String ID = attributes[0];
        String firstName = attributes[1];
        String lastName = attributes[2];
        String program = attributes[3];
        String level = attributes[4];
        String ASURITE = attributes[5];

        Student stu = new Student(ID, firstName, lastName, program, level, ASURITE);
        for (int i = basehead; i < attributes.length; i++) {
            stu.addAttendance(LocalDate.parse(head.get(i)), Integer.parseInt(attributes[i]));
        }

        return stu;
    }

    /**
     * saves the rost to a file
     *
     * @param saveFilePath (String) path where file is to be saved
     * @return boolean status
     */
    public boolean save(String saveFilePath) {

        try {
            FileWriter csvWriter = new FileWriter(saveFilePath);

            if (!head.isEmpty()) csvWriter.append(String.join(",", head));

            List<List<String>> tableData = new ArrayList();

            String[][] arrTableData = getTableData();

            for (int i = 0; i < arrTableData.length; i++) {
                List<String> tableRow = Arrays.asList(arrTableData[i]);
                tableData.add(tableRow);
            }

            for (List<String> studentInfo : tableData) {
                csvWriter.append("\n");
                csvWriter.append(String.join(",", studentInfo));
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Converts the head array list to an array of strings Needed because JTable only accepts an
     * array of strings for head
     *
     * @return String[] of the rost head
     */
    public String[] gethead() {

        String[] headArr = new String[head.size()];
        int i = 0;
        for (String s : head) {
            headArr[i] = s;
            i++;
        }
        return headArr;
    }

    /**
     * Converts the table data to a double array of strings Needed because JTable only accepts a
     * double array of strings for the data
     *
     * @return String[][] of the rost data
     */
    public String[][] getTableData() {

        String[][] tableData = new String[rost.size()][];

        for (int i = 0; i < rost.size(); i++) {
            String[] stuAttributes = new String[head.size()];
            stuAttributes[0] = rost.get(i).getID();
            stuAttributes[1] = rost.get(i).getFirstName();
            stuAttributes[2] = rost.get(i).getLastName();
            stuAttributes[3] = rost.get(i).getProgram();
            stuAttributes[4] = rost.get(i).getLevel();
            stuAttributes[5] = rost.get(i).getASURITE();

            int studentIndex = basehead;
            for (Map.Entry<LocalDate, Integer> e : rost.get(i).getAttendance().entrySet()) {
                stuAttributes[studentIndex] = Integer.toString(e.getValue());
                studentIndex++;
            }

            tableData[i] = stuAttributes;
        }

        return tableData;
    }

    /**
     * Adds the attendance data for the students by reading from the attendance filepath
     *
     * @param date date that the attendance data is for
     * @param filepath path to the csv file with attendance data
     */
    public void addStudentAttendance(LocalDate date, String filepath) {

        try {
            File file = new File(filepath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String ASURITE = "";
            int time = 0;

            if (!head.contains(date.toString())) {
                head.add(date.toString());
            }

            while ((line = br.readLine()) != null) { // Read all lines of csv file
                ASURITE = line.split(delimiter)[0];

                if (line.split(delimiter)[1].equals("")) {
                    time = 0;
                } else {
                    time = Integer.parseInt(line.split(delimiter)[1]);
                }

                extraStudents.put(ASURITE, time);

                for (Student student : rost) { // Find student by ASURITE
                    student.addAttendance(date, 0);

                    if (student.getASURITE().equals(ASURITE)) {
                        student.addAttendance(date, time);
                        extraStudents.remove(ASURITE);
                        studentsAdded++;

                        if (!this.hasDate(date)) {
                            dates.add(date);
                        }
                    }
                }
            }

            br.close();
            setChanged();
            notifyObservers();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    /**
     * Checks if the the dateToCheck is in the dates in the rost
     *
     * @param dateToCheck
     * @return boolean True if the date is in the rost, false otherwise
     */
    public boolean hasDate(LocalDate dateToCheck) {
        for (LocalDate date : dates) {
            if ((date).equals(dateToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the attendance data in percentage of time attended for the scatter plot
     *
     * @param date to collect the attendance data for
     * @return an array list of attendance data percentages
     */
    public List<Double> getDataSet(LocalDate date) {
        List<Double> xAxis = new ArrayList();

        for (Student student : rost) {
            if (student.getDateAttendance(date) >= 75) {
                xAxis.add(100.0);
            } else {
                double percentage = student.getDateAttendance(date) / 75.0 * 100;
                xAxis.add(percentage);
            }
        }
        return xAxis;
    }
}