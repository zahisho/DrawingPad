package scribble.menu;

import javax.swing.JComboBox;
import scribble.frame.LayerList;
import scribble.frame.Scribble;

public class CurrentLayerComboBox extends JComboBox<String> {

  private final Scribble frame;

  public CurrentLayerComboBox(Scribble s) {
    frame = s;
    updateLayers();
  }

  public final void updateLayers() {
    LayerList layers = frame.getCanvas().getLayers();
    int nLayers = layers.size();

    removeAllItems();

    int indexCurLayer = -1;

    for (int i = 0; i < nLayers; i++) {
      if (layers.get(i).getShow()) {
        addItem("Layer " + i);
      }
      if (layers.get(i) == frame.getCanvas().getCurLayer()) {
        indexCurLayer = i;
      }
    }

    addItem("None");

    addItemListener((e) -> {
      frame.getCanvas().setCurLayer(getSelectedIndex());
    });

    if (indexCurLayer > -1) {
      setSelectedIndex(indexCurLayer);
    } else {
      setSelectedIndex(getItemCount() - 1);
    }
  }
}
