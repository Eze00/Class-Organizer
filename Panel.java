//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane
 // Description:  panel class creates the some other layout of the program
 //********************************************************************


//package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.util.Arrays;


public class Panel extends JPanel implements Observer {
    
	protected JTable JT;

	/*
	 *  Panel class constructor 
	 */
    public Panel() 
    {
    	
    	setLayout(new BorderLayout());
    	
    	Dimension dim = new Dimension();
		dim.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.5, Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.5);
		
		setPreferredSize(dim);
		
		Dimension share = new Dimension();
		share.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    	
		setSize(share);
		
		
    	this.JT = new JTable();
    	JScrollPane sP = new JScrollPane(JT);
    	sP.setSize(share);
    	add(sP);
    	
    }
    
    
    /*
     * Sets the value of the drawable object and repaints the JPanel
     */
    @Override
    public void update(Observable o, Object arg) {
    	String[][] info = ((Repository)o).getTableData();
    	String[] h = ((Repository)o).gethead();
    	
    	for(String[] array : info) 
    	{
    		System.out.println(Arrays.toString(array));
    	}
    	
    	this.JT.setModel(new DefaultTableModel(info, h));
    	JT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
}
