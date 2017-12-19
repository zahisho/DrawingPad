package toolkit;

import shape.UmlElement;
import shape.Shape;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;

public class TextTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;

  private Shape shape;

  public TextTool(ScribbleCanvas canvas, UmlElement figure, String name) {
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
    shape = new Shape(figure.getFigure());
    String res = JOptionPane.showInputDialog("Enter Text");
    if (res != null) {
      canvas.createShape(shape);
      shape.setText(res);
      shape.startFigure(e.getPoint(), canvas.getGraphics());
    }
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    shape.updateFigure(e.getPoint(), canvas.getGraphics());
    shape.draw(canvas.getGraphics());
  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    shape.updateFigure(e.getPoint(), canvas.getGraphics());
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }
}
