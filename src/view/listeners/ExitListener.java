
package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class ExitListener implements ActionListener {
  
  private final Scribble ExitListener;

  public ExitListener(final Scribble ExitListener) {
    this.ExitListener = ExitListener;
  }

  public void actionPerformed(ActionEvent e) {
    int result = JOptionPane.showConfirmDialog(null, "Do you want to exit Scribble Pad?", "Exit Scribble Pad?", JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      ExitListener.saveFile();
      System.exit(0);
    }
  }
  
}
