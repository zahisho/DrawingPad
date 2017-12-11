package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;
import scribble.drawing.LineStyle;

class LineStyleMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;
  private final LineStyle lineStyle;

  public LineStyleMenuItem(Scribble f, LineStyle d) {
    super(d.getLabel());
    frame = f;
    lineStyle = d;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().setCurLineStyle(lineStyle.getStyle());
    frame.getCanvas().repaint();
  }
}
