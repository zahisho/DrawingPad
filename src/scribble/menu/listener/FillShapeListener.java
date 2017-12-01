package scribble.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class FillShapeListener implements ActionListener {

  private final Scribble frame;

  public FillShapeListener(Scribble s) {
    frame = s;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().fillSelectedShapes();
  }
}
