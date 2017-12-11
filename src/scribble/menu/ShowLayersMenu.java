package scribble.menu;

import javax.swing.JMenu;
import scribble.frame.Scribble;

public class ShowLayersMenu extends JMenu {

  private final Scribble frame;

  public ShowLayersMenu(Scribble s) {
    super("Show layers");
    frame = s;

    addItems();
  }

  public final void addItems() {
    int nLayers = frame.getCanvas().getLayers().size();

    removeAll();

    for (int i = 0; i < nLayers; i++) {
      add(new LayerCheckbox(frame, i));
    }
  }
}
