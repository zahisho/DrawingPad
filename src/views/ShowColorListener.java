package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author M16U3L
 */
public class ShowColorListener implements ActionListener {

  private final DrawingpadMenu menu;

  public ShowColorListener(DrawingpadMenu menu) {
    this.menu = menu;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    menu.showColorPanel();
  }
}
