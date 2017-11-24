
package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class SaveFileListener implements ActionListener {
  
  private final Scribble SaveFileListener;

  public SaveFileListener(final Scribble SaveFileListener) {
    this.SaveFileListener = SaveFileListener;
  }

  public void actionPerformed(ActionEvent e) {
    SaveFileListener.saveFile();
  }
  
}
