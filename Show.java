//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane
 // Description:  show class creates the desgin and layout of the program
 //********************************************************************

//package project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
  * shows off the about dialogue boxes and tab to display errors with info on it
 */
public class Show
{

    /*
     * Displays error info 
     */
    public void emptyRosterErrorHandler()
    {
        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "Error");
        JPanel p = new JPanel();
        JLabel m = new JLabel("ERROR: Roster must be loaded first");

        p.add(m);
        d.add(p);

        d.setSize(300, 70);
        d.setVisible(true);
    }

    /*
     * Displays the roster with the added attendce information
     */
    public void displayAttendanceResult( LinkedHashMap<String, Integer> additionalUser, int UserAdded) 
    {
        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "Attendance Results");
        
        String loadtext = "Data loaded for " + UserAdded + " users in the roster.";
        
        String addText =  "<html>" + additionalUser.size()  + " additional attendee(s) was found:<br>";
        
        JPanel p = new JPanel();
        JLabel lM =
                new JLabel(loadtext);
        JLabel aM =
                new JLabel(addText);

        

        
        
        p.setLayout(new FlowLayout());
        p.add(lM);
        p.add(aM);

        String attM = "";

        if (!additionalUser.isEmpty()) 
        {
            for (Map.Entry<String, Integer> e : additionalUser.entrySet()) 
            {
                attM = "<html>" + e.getKey() + ", connected for " + e.getValue() + " minute(s)" + "<br></br>";
                JLabel addLabel = new JLabel(attM);
                p.add(addLabel);
            }
        }

        
        
        
        /*
         *  create and display with all its functioinalitys
         */
        d.add(new JScrollPane(p));
       
        
        
        d.setSize(600, 400);
        
        d.setVisible(true);
        
        Repository.extraStudents.clear();
        Repository.studentsAdded = 0;
    }

    /*
     * Displays  the about info with all the team members first and last name
     */
    public void displayTeamInfo() {
        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "About");
        JPanel p = new JPanel();
		
        
        String aboutInfo = "<html> <style>html {text-align: center} html {word-wrap: break-word} </style>" +
                "<h1>CSE360 <br></h1>"
                        + "<h2>Members:<br></h2>"
                        + "<h3>Ezedine Kargougou,Antonie Belhomme,Azaria Yemane,\n"
                        + "Kenneth Wang,</h3>"
                        + "</html>";
        
        JLabel tLabel = new JLabel(aboutInfo);

        p.add(tLabel);
        d.add(p);
        
        
        
        d.setSize(900, 250);
        d.setVisible(true);
    }
}

