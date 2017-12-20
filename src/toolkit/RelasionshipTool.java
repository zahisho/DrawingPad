package toolkit;

import shape.UmlElement;
import shape.Shape;
import shape.ShapeList;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import main.ScribbleCanvas;

public class RelasionshipTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;

  private Shape init;
  private Shape end;
  private Shape shape;

  public RelasionshipTool(ScribbleCanvas canvas, UmlElement figure,
    String name) {
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
  public final void mousePressed(final MouseEvent e) {
    ShapeList shapes = canvas.getShapes();
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape actualShape = (Shape) iter.next();
      if (actualShape != null) {
        if (actualShape.isSelected(e.getPoint())) {
          init = actualShape;
          shape = new Shape(figure.getFigure());
          shape.startFigure(e.getPoint(), canvas.getGraphics());
          break;
        }
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (init != null) {
      if (!init.isSelected(e.getPoint())) {
        ShapeList shapes = canvas.getShapes();
        Iterator iter = shapes.iterator();
        while (iter.hasNext()) {
          Shape actualShape = (Shape) iter.next();
          if (actualShape != null) {
            if (actualShape.isSelected(e.getPoint())) {
              if (actualShape.isSelected(e.getPoint())) {
                shape.updateFigure(e.getPoint(), canvas.getGraphics());
                shape.draw(canvas.getGraphics());
                canvas.createShape(shape);
                break;
              } else {
                canvas.repaint();
              }
            } else {
              canvas.repaint();
            }
          }
        }
      }
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (init != null) {
      shape.updateFigure(e.getPoint(), canvas.getGraphics());
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

}
