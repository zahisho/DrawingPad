package toolkit;

import java.awt.Point;
import shape.UmlElement;
import shape.Shape;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;
import shape.ShapeList;
import umlObjects.Subject;
import umlObjects.UmlObject;

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
      if (actualShape.isSelected(e.getPoint())) {
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
          if ("A".equals(actualShape.getTypeClass())) {
            end = actualShape;
            shape.updateFigure(e.getPoint(), canvas.getGraphics());
            shape.draw(canvas.getGraphics());
            canvas.createShape(shape);
            init.setNameClass2(end.getNameClass());
            canvas.repaint();
            break;
          } else {
            JOptionPane.showMessageDialog(canvas,
              "La clase no puede tener ese tipo de relacion");
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
