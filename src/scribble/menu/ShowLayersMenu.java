package scribble.menu;

import javax.swing.JMenu;
import scribble.frame.LayerList;
import scribble.frame.Scribble;

public class ShowLayersMenu extends JMenu {

  private final Scribble frame;

  public ShowLayersMenu(Scribble s) {
    super("Show layers");
    frame = s;

    updateLayers();
  }

  public final void updateLayers() {
    LayerList layers = frame.getCanvas().getLayers();
    int nLayers = layers.size();

    removeAll();

    for (int i = 0; i < nLayers; i++) {
      add(new LayerCheckbox(frame, i, layers.get(i).getShow()));
    }
  }
}
