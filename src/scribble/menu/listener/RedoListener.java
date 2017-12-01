package scribble.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class RedoListener implements ActionListener {

  private final Scribble frame;

  public RedoListener(Scribble s) {
    frame = s;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().redo();
  }
}
