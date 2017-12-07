package scribble.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class GroupShapesListener implements ActionListener {

  private final Scribble frame;

  public GroupShapesListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().groupShapes();
  }
}
