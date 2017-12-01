package scribble.menu.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scribble.Scribble;

public class FillingColorListener implements ActionListener {

  private final Scribble frame;
  private final ColorDialog dialog;

  public FillingColorListener(Scribble f) {
    frame = f;
    dialog = new ColorDialog(frame,
            "Choose color", frame.getCanvas().getCurColor());
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    Color result = dialog.showDialog();
    if (result != null) {
      frame.getCanvas().changeFilling(result);
    }
  }
}
