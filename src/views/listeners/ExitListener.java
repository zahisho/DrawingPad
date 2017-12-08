package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.DrawingPadFrame;
import views.DrawingpadMenu;

/**
 *
 * @author M16U3L
 */
public class ExitListener implements ActionListener {

  private final DrawingPadFrame frame;
  private final DrawingpadMenu menu;

  public ExitListener(DrawingPadFrame frame, DrawingpadMenu menu) {
    this.frame = frame;
    this.menu = menu;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int result = JOptionPane.showConfirmDialog(null,
      "Do you want to exit Scribble Pad?",
      "Exit Scribble Pad?",
      JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      menu.saveFile();
      System.exit(0);
    } else {
      frame.setDefaultCloseOperation(JFrame.NORMAL);
    }
  }
}
