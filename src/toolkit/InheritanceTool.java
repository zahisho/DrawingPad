package toolkit;

import shape.UmlElement;
import shape.Shape;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;
import shape.ShapeList;

public class InheritanceTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;
  private Shape init;
  private Shape end;

  private Shape shape;

  public InheritanceTool(ScribbleCanvas canvas, UmlElement figure, String name) {
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
    ShapeList shapes = canvas.getShapes();
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape actualShape = (Shape) iter.next();
      if (actualShape.isSelected(e.getPoint())
        && !"I".equals(actualShape.getTypeClass())) {
        actualShape.setNameTypeClass("abstract");
        init = actualShape;
        shape = new Shape(figure.getFigure());
        shape.startFigure(e.getPoint(), canvas.getGraphics());
        break;
      }
    }
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    if (init != null) {
      ShapeList shapes = canvas.getShapes();
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape actualShape = (Shape) iter.next();
        if (actualShape.isSelected(e.getPoint())) {
          if (!"I".equals(actualShape.getTypeClass())) {
            end = actualShape;
            shape.updateFigure(e.getPoint(), canvas.getGraphics());
            shape.draw(canvas.getGraphics());
            canvas.createShape(shape);
            init.setNameExtend(end.getNameClass());
            canvas.repaint();
            break;
          } else {
            JOptionPane.showMessageDialog(canvas,
              "Wrong relationship");
            canvas.repaint();
          }
        } else {
          canvas.repaint();
        }
      }
    }
    canvas.repaint();
  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    if (init != null) {
      shape.updateFigure(e.getPoint(), canvas.getGraphics());
    }
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }

}
