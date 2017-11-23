package menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import scribble.Scribble;

public class OpenFileListener implements ActionListener {

  private final Scribble frame;

  public OpenFileListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    JFileChooser chooser = frame.getChooser();
    int retval = chooser.showDialog(null, "Open");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (theFile.isFile()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          frame.openFile(filename);
        }
      }
    }
  }
}
