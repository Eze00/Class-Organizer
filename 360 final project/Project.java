//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane
 // Description:  main class is the driver class of the whole program
 //********************************************************************

//package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



/*
 * this is the main driver class of the Gui project
 * button that are being pressed it is going to the actionlistner.
 */
public class Project extends JFrame 
{
		
	protected static Repository all;
	
	/*
	 * Constructor for Main class. Sets up the layout and adds JMenuBar and JPanel to it
	 */
	public Project(){   
        
        setLayout(new BorderLayout()); // set this to whatever works best
        
		Dimension prefrence = new Dimension();
		prefrence.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2, Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
		setPreferredSize(prefrence);
		
		//write the names for the drop down menu selections
		
        JMenuItem lR = new JMenuItem("Load a Roster");
        JMenuItem aA = new JMenuItem("Add Attendance");
        JMenuItem sR = new JMenuItem("Save");
        JMenuItem pD = new JMenuItem("Plot Data");
        
        /*
         * inserts menuItems to file
         */
        JMenu f = new JMenu("File");
        f.add(lR);
        f.add(aA);
        f.add(sR);
        f.add(pD);
        
        /*
         *  create the about info
         */
        JMenuItem a = new JMenuItem("About");
        JMenuBar mB = new JMenuBar();
        mB.add(f);
        mB.add(a);
        
        add(mB);
        setJMenuBar(mB);
        
        /*
         *  create the title of the program
         */
        setTitle("CSE360 Final Project");
        
        /*
         *  create panel with rosters
         */
        Panel p = new Panel();
        add(p, BorderLayout.CENTER);
        
        /*
         *  create repository object
         */
        all = new Repository();
        all.addObserver(p);
        
        
        
       
        /*
         *  gives the buttons a action to do
         */
       
        lR.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	String inputFilepath = Edit.getOpenPath();
            	if(inputFilepath != null) 
            	{		// Check if file could be found
            		Edit.loadTable(inputFilepath);
	        	}
            }
        });
        
        aA.addActionListener(new ActionListener() 
        {
        	@Override
			public void actionPerformed(ActionEvent e) 
        	{
        		if(Repository.rost != null) 
        		{		// Check if roster has been loaded
		        	
        			String p = Edit.getOpenPath();
		        	
        			if(p != null)
		        	{		// Check if file could be found
		        		Edit.displayDatePicker(p);
		        	}
        		}
        		
        		else 
        		{
        			Edit.showEmptyRosterError();		// not been loaded
        		}
        	}
        });
        
        sR.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	// check if there is something in the load
            	if(Repository.rost != null)
            	{		
        			String inputFilepath = Edit.getSavePath();
        			if(inputFilepath != null) 
        			{	
        				// Check if file is indeed found
        				Edit.saveTable(inputFilepath);
        			}
        		}
        		
            	else 
        		{
        			Edit.showEmptyRosterError();
        		}
            }
        });
        
        /*
         * gives buttons a action if gets pressed on
         */
        pD.addActionListener(new ActionListener() 
        {
        	@Override
            public void actionPerformed(ActionEvent e)
            {
        		if(Repository.rost != null) 
        		{		
        			Edit.showScatterPlot();
        		}
        		else 
        		{
        			Edit.showEmptyRosterError();
        		}
            }
        });
        
        a.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Edit.showAboutTab();
            }
        });

	}

	/*
	 * this code starts up the whole program, so it can run
	 */
	public static void main(String[] args) 
	{
        Project m = new Project();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(m.getPreferredSize());
        m.setVisible(true);

	}

}
