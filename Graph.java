//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane 
 //********************************************************************

//package project;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.*;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Graph extends JFrame {

    /**
     * Constructor for scatter plot with a title input
     */
    public Graph(String title) {
        super(title);

        XYDataset data = createDataset(); //create a data set

        // Make a scatter plot with the library JFreeChart
        JFreeChart chartt =
                ChartFactory.createScatterPlot(
                        "Attendance", "Percent of Attendance", "Count", data);

        //Set a color for the plot
        XYPlot plotsca = (XYPlot) chartt.getPlot();
        plotsca.setBackgroundPaint(new Color(160, 228, 196));

        // Set the range of the x-axis
        NumberAxis xDom = (NumberAxis) plotsca.getDomainAxis();
        xDom.setRange(-5, 105);
        xDom.setTickUnit(new NumberTickUnit(10));
        xDom.setVerticalTickLabels(true);

        // Set the range of the y-axis
        NumberAxis yRan = (NumberAxis) plotsca.getRangeAxis();
        yRan.setRange(0, Repository.rost.size() + 0.5);
        yRan.setTickUnit(new NumberTickUnit(1));
        yRan.setVerticalTickLabels(true);

        // Make a panel to display our plot
        ChartPanel panels = new ChartPanel(chartt);
        setContentPane(panels);
    }

    /**
     *  Create a data set with the usage of attendance
     */
    private XYDataset createDataset() {
        // All the date in the roster
        List<LocalDate> datesList = new ArrayList<LocalDate>();

        // Read from the header for a date
        for (int i = 6; i < Repository.head.size(); i++) {
            datesList.add(LocalDate.parse(Repository.head.get(i)));
        }

        // Make a data set
        XYSeriesCollection dataset = new XYSeriesCollection();

        // For every day, find all the data points
        for (LocalDate date : datesList) {
            List<Double> xAxis = Project.all.getDataSet(date);
            XYSeries cData = new XYSeries(date.toString());

            for (int i = 0; i < xAxis.size(); i++) {
                int percen = xAxis.get(i).intValue();
                int counter = 1;

                // Keep a count of all the duplicates
                for (int j = i + 1; j < xAxis.size(); j++) {
                    if (percen == xAxis.get(j).intValue()) {
                        counter++;
                        xAxis.remove(j);
                        j--;
                    }
                }
                cData.add(percen, counter);
            }
            dataset.addSeries(cData);
        }

        return dataset;
    }

    /** 
     * Show the scatter plot with all the data 
     */
    public static void graphPlotGUI() {
        SwingUtilities.invokeLater(
                () -> {
                    Graph scatter = new Graph("Plot Data");
                    scatter.setSize(800, 400);
                    scatter.setLocationRelativeTo(null);
                    scatter.setVisible(true);
                });
    }
}