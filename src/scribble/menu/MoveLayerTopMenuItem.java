package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class MoveLayerTopMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public MoveLayerTopMenuItem(Scribble s) {
    super("Move layer top");
    frame = s;
    addAction();
  }

  private void addAction() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().moveLayerTop();
    frame.getCanvas().repaint();
  }
}
