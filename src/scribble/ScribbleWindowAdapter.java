package scribble;

import menu.listeners.ExitListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScribbleWindowAdapter extends WindowAdapter {

  private final Scribble frame;

  public ScribbleWindowAdapter(Scribble f) {
    frame = f;
  }

  @Override
  public final void windowClosing(final WindowEvent e) {
    ExitListener exitListener = frame.getExitListener();
    if (exitListener != null) {
      exitListener.actionPerformed(new ActionEvent(frame, 0, null));
    }
  }
}
