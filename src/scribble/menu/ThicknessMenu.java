package scribble.menu;

import javax.swing.JMenu;
import scribble.frame.Scribble;
import scribble.drawing.Thickness;

public class ThicknessMenu extends JMenu {

  private final Scribble frame;

  public ThicknessMenu(Scribble f) {
    super("Thickness");
    frame = f;
    addItems();
  }

  private void addItems() {
    for (Thickness t : Thickness.values()) {
      add(new ThicknessMenuItem(frame, t));
    }
  }
}
