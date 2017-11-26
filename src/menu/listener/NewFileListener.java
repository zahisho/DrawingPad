package menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class NewFileListener implements ActionListener {

  private final Scribble frame;

  public NewFileListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.newFile();
  }
}
