package menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import scribble.Scribble;

public class ExitListener implements ActionListener {

  private final Scribble frame;

  public ExitListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    int result = JOptionPane.showConfirmDialog(null,
            "Do you want to exit Scribble Pad?",
            "Exit Scribble Pad?",
            JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      frame.saveFile();
      System.exit(0);
    } else {
      frame.setDefaultCloseOperation(JFrame.NORMAL);
    }
  }
}
