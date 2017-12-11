package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class GroupShapesMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public GroupShapesMenuItem(Scribble f) {
    super("Group");
    frame = f;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().groupShapes();
  }
}
