package scribble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

public class SaveFileAsListener implements ActionListener {

  private final Scribble frame;

  public SaveFileAsListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    JFileChooser chooser = frame.getChooser();
    int retval = chooser.showDialog(null, "Save As");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (!theFile.isDirectory()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          frame.saveFileAs(filename);
        }
      }
    }
  }
}
