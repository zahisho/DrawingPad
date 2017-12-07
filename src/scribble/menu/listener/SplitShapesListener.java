package scribble.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class SplitShapesListener implements ActionListener {

  private final Scribble frame;

  public SplitShapesListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().splitShapes();
  }
}
