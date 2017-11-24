
package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class SaveAsFileListener implements ActionListener {
  
  private final Scribble SaveAsFileListener;

public  SaveAsFileListener(final Scribble SaveAsFileListener) {
    this.SaveAsFileListener = SaveAsFileListener;
  }

  public void actionPerformed(ActionEvent e) {
    int retval = SaveAsFileListener.chooser.showDialog(null, "Save As");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = SaveAsFileListener.chooser.getSelectedFile();
      if (theFile != null) {
        if (!theFile.isDirectory()) {
          String filename = SaveAsFileListener.chooser.getSelectedFile().getAbsolutePath();
          SaveAsFileListener.saveFileAs(filename);
        }
      }
    }
  }
  
}
