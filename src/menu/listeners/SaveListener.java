package menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class SaveListener implements ActionListener {

  private final Scribble frame;

  public SaveListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.saveFile();
  }
}
