package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import scribble.frame.Scribble;

public class ShowToolbarCheckbox extends JCheckBoxMenuItem implements ActionListener {

  private final Scribble frame;

  public ShowToolbarCheckbox(Scribble s) {
    super("Show toolbar");
    frame = s;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    if (isSelected()) {
      frame.showToolbar();
    } else {
      frame.hideToolbar();
    }
  }
}
