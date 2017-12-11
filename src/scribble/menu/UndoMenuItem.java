package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class UndoMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public UndoMenuItem(Scribble s) {
    super("Undo");
    frame = s;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    frame.getCanvas().undo();
  }
}
