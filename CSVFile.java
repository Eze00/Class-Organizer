//********************************************************************
 // ASU CSE360         Project
 // Names:     Ezedine Kargougou,Antonie Belhomme, Kenneth Wang, Azaria Yemane
 // Description:  
 //********************************************************************

package project;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class CSVFile extends JFileChooser {

    private JFileChooser chooser = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
    final Dimension prefered = new Dimension();

    /* Constructor*/
    public CSVFile() {
        chooser.setFileFilter(filter);
        chooser.setBackground(Color.WHITE);
        chooser.setForeground(Color.white);
        prefered.setSize(
                Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.25,
                Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.25);
        chooser.setPreferredSize(prefered);
    }

    /**
     * lets users choose what file to open by showing them the
     *  file dialogue
     */
    public File getOpenFile() {
        File file;
        int returnedValue = chooser.showOpenDialog(getParent());
        file = chooser.getSelectedFile();

        if (returnedValue == JFileChooser.APPROVE_OPTION) {
            String nameOfFile = file.getName();
            if (!nameOfFile.substring(nameOfFile.lastIndexOf('.')).equals(".csv")) {
                file = null;
            }
        } else {
            file = null;
        }

        return file;
    }

    /**
     * let's the user choose where to save their files
     */
    public File getSaveFile() {
        File file;
        int returnedValue = chooser.showSaveDialog(getParent());
        file = chooser.getSelectedFile();

        if (returnedValue != JFileChooser.APPROVE_OPTION) {
            file = null;
        }
        if (file.getName().indexOf('.') == -1) {
            file = new File(file.toString() + ".csv");
        } else if (!file.getName()
                .substring(file.getName().indexOf('.'))
                .equalsIgnoreCase(".csv")) {
            file = null;
        } else {
        }

        return file;
    }
}