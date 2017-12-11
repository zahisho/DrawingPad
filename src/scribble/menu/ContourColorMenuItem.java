package scribble.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class ContourColorMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;
  private final ColorDialog dialog;

  public ContourColorMenuItem(Scribble f) {
    super("Contour color");
    frame = f;
    dialog = new ColorDialog(frame,
            "Choose color", frame.getCanvas().getContourColor());
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    Color result = dialog.showDialog();
    frame.getCanvas().setContourColor(result);
  }
}
