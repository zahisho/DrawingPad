package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class DeleteSelectedMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public DeleteSelectedMenuItem(Scribble s) {
    super("Delete");
    frame = s;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().deleteSelectedShapes();
  }
}
