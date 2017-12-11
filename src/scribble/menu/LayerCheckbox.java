package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import scribble.frame.Scribble;

public class LayerCheckbox extends JCheckBoxMenuItem implements ActionListener {

  private final int index;
  private final Scribble frame;

  public LayerCheckbox(Scribble s, int i, boolean selected) {
    super("Layer " + i);
    index = i;
    frame = s;
    init(selected);
  }

  private void init(boolean selected) {
    setSelected(selected);
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    if (isSelected()) {
      frame.getCanvas().showLayer(index);
    } else {
      frame.getCanvas().hideLayer(index);
    }
    frame.getCanvas().repaint();
  }
}
