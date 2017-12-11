package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class MoveLayerFrontMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public MoveLayerFrontMenuItem(Scribble s) {
    super("Move layer front");
    frame = s;
    addAction();
  }

  private void addAction() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().moveLayerFront();
    frame.getCanvas().repaint();
  }
}
