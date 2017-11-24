
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
public class OpenFileListener implements ActionListener {
  
  private final Scribble OpenFileListener;

  public OpenFileListener(final Scribble OpenFileListener) {
    this.OpenFileListener = OpenFileListener;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int retval = OpenFileListener.chooser.showDialog(null, "Open");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = OpenFileListener.chooser.getSelectedFile();
      if (theFile != null) {
        if (theFile.isFile()) {
          String filename = OpenFileListener.chooser.getSelectedFile().getAbsolutePath();
          OpenFileListener.openFile(filename);
        }
      }
    }
  }
  
}
