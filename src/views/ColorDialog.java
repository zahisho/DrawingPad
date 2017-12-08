package views;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import main.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class ColorDialog extends JPanel {

  private FillColorPanel fillColorPanel;
  private StrokeColorPanel drawColorPanel;
  private ScribbleCanvas panelCanvas;
  private boolean isVisible;

  public ColorDialog(ScribbleCanvas panelCanvas) {
    isVisible = true;
    setVisible(true);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    drawColorPanel = new StrokeColorPanel(panelCanvas);
    add(drawColorPanel, BorderLayout.CENTER);
    fillColorPanel = new FillColorPanel(panelCanvas);
    add(fillColorPanel, BorderLayout.CENTER);
  }

  public boolean getVisiblePanel() {
    return isVisible;
  }

  public void setVisiblePanel(boolean b) {
    this.setVisible(b);
    isVisible = b;
  }
}
