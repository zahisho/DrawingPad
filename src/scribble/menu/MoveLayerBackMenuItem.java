package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class MoveLayerBackMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public MoveLayerBackMenuItem(Scribble s) {
    super("Move layer back");
    frame = s;
    addAction();
  }

  private void addAction() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().moveLayerBack();
    frame.getCanvas().repaint();
  }
}
