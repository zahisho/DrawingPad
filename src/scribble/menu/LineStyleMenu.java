package scribble.menu;

import javax.swing.JMenu;
import scribble.frame.Scribble;
import scribble.drawing.LineStyle;

public class LineStyleMenu extends JMenu {

  private final Scribble frame;

  public LineStyleMenu(Scribble f) {
    super("Line style");
    frame = f;
    addItems();
  }

  private void addItems() {
    for (LineStyle s : LineStyle.values()) {
      add(new LineStyleMenuItem(frame, s));
    }
  }
}
