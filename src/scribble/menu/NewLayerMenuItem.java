package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import scribble.frame.Scribble;

public class NewLayerMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public NewLayerMenuItem(Scribble s) {
    super("New layer");
    frame = s;
    addAction();
  }

  private void addAction() {
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getCanvas().newLayer();
    frame.updateLayersMenu();
  }
}
