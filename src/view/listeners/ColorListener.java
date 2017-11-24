
package view.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pad.ColorDialog;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class ColorListener implements ActionListener {
  
  private final Scribble ColorListener;
  private ColorDialog dialog;

  public ColorListener(final Scribble ColorListener) {
    this.dialog = new ColorDialog(ColorListener, "Choose color", ColorListener.canvas.getCurColor());
    this.ColorListener = ColorListener;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Color result = dialog.showDialog();
    if (result != null) {
      ColorListener.canvas.setCurColor(result);
      
     
    }
  }
  
  
}
