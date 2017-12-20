package toolkit;

import shape.UmlElement;
import shape.Shape;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class ClassAbstractTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;

  private Shape shape;

  public ClassAbstractTool(ScribbleCanvas canvas, UmlElement figure, String name) {
    this.canvas = canvas;
    this.figure = figure;
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    shape = new Shape(figure.getFigure());
    String res = JOptionPane.showInputDialog("Enter Name the Class Abstract");
    if (res != null) {
      canvas.createShape(shape);
      shape.setNameClass(res);
      shape.setTypeClass("A");
      shape.startFigure(e.getPoint(), canvas.getGraphics());
      canvas.repaint();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }

}
