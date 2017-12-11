package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class MoveLayerBottomMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public MoveLayerBottomMenuItem(Scribble s) {
    super("Move layer bottom");
    frame = s;
    addAction();
  }

  private void addAction() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().moveLayerBottom();
    frame.getCanvas().repaint();
  }
}
