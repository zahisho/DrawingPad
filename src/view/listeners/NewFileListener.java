
package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class NewFileListener implements ActionListener {
  
  private final Scribble NewFileListener;

  public NewFileListener(final Scribble NewFileListener) {
    this.NewFileListener = NewFileListener;
  }

  public void actionPerformed(ActionEvent e) {
    NewFileListener.newFile();
  }
  
}
