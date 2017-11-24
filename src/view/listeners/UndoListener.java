
package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class UndoListener implements ActionListener{
  
  private final Scribble UNDOLISTENER;

  /**
   *
   * @param UNDOLISTENER
   */
  public UndoListener(final Scribble UNDOLISTENER) {
    this.UNDOLISTENER = UNDOLISTENER;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    UNDOLISTENER.makeUndo();
   
  }
  
}
