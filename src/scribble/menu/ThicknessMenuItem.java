package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;
import scribble.drawing.Thickness;

class ThicknessMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;
  private final Thickness thickness;

  public ThicknessMenuItem(Scribble f, Thickness t) {
    super(t.getLabel());
    frame = f;
    thickness = t;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().setCurThickness(thickness.getThickness());
    frame.getCanvas().repaint();
  }
}
