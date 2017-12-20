package toolkit;

import shape.UmlElement;
import shape.Shape;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;
import shape.ShapeList;

public class ImplementsTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;
  private Shape init;
  private Shape end;
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
    ShapeList shapes = canvas.getShapes();
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape actualShape = (Shape) iter.next();
      if (actualShape.isSelected(e.getPoint())) {
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
      if (!init.isSelected(e.getPoint())) {
        String nameClasss = "";
        ShapeList shapes = canvas.getShapes();
        Iterator iter = shapes.iterator();
        while (iter.hasNext()) {
          Shape actualShape = (Shape) iter.next();
          if (actualShape.isSelected(e.getPoint())) {
            if ("I".equals(actualShape.getTypeClass())) {
              nameClasss = actualShape.getNameClass();
              init.addNameClasss(nameClasss);
              shape.updateFigure(e.getPoint(), canvas.getGraphics());
              shape.draw(canvas.getGraphics());
              canvas.createShape(shape);
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
    }
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
