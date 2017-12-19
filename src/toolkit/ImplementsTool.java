package toolkit;

import shape.UmlElement;
import shape.Shape;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;

public class ImplementsTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;
  private Shape class1;

  private Shape shape;

  public ImplementsTool(ScribbleCanvas canvas, UmlElement figure, String name) {
    this.canvas = canvas;
    this.figure = figure;
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
  }

  @Override
  public final void mousePressed(MouseEvent e) {

  }

  @Override
  public final void mouseReleased(MouseEvent e) {

  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    if (class1 != null) {
      shape.updateFigure(e.getPoint(), canvas.getGraphics());
    }
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }

}
