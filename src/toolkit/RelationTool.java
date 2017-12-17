package toolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import main.ScribbleCanvas;
import shape.Initializable;
import shape.LineShape;
import shape.RectangleShape;
import shape.Shape;
import shape.ShapeList;
import shape.TwoEndsShape;

/**
 *
 * @author M16U3L
 */
public class RelationTool extends Tool {

  private RectangleShape p1, p2;
  private RectangleShape nodoMover;
  private int iNodo;
  private int xStart;
  private int yStart;
  private final ScribbleCanvas canvas;

  public RelationTool(final ScribbleCanvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public final String getName() {
    return "Line";
  }

  // helper methods
  public static void drawLine(final Graphics g, final int x1,
    final int y1, final int x2, final int y2) {
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {
    ShapeList shapes = canvas.getShapes();
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape instanceof Initializable) {
          p1 = (RectangleShape) shape;
          if (p1.contents(e.getPoint())) {
            canvas.resetSelected();
            canvas.repaint();
            canvas.x = e.getX();
            xStart = canvas.x;
            canvas.y = e.getY();
            yStart = canvas.y;
            Graphics g = canvas.getGraphics();
            g.setXORMode(Color.darkGray);
            g.setColor(Color.lightGray);
          }
        }
        p1 = null;
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    ShapeList shapes = canvas.getShapes();
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape instanceof Initializable) {
          p2 = (RectangleShape) shape;
          if (p2.contents(e.getPoint())) {
            TwoEndsShape newShape = new LineShape(canvas.getCurColor());
            newShape.setColor(canvas.getCurColor());
            newShape.draw(canvas.getGraphics());
            newShape.setEnds(xStart, yStart, e.getX(), e.getY());
            canvas.addShape(newShape);
            canvas.repaint();
            break;
          }
        }
      }
    }
    p2 = null;
    p1 = null;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Graphics g = canvas.getGraphics();
    g.setXORMode(canvas.getBackground());
    g.setColor(canvas.getCurColor());
    drawLine(g, xStart, yStart, canvas.x, canvas.y);
    drawLine(g, xStart, yStart, e.getX(), e.getY());
    canvas.x = e.getX();
    canvas.y = e.getY();

  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }
}
