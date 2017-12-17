package toolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import main.ScribbleCanvas;
import shape.RectangleShape;
import shape.Relationable;
import shape.Shape;
import shape.ShapeList;
import shape.TwoEndsShape;

/**
 *
 * @author M16U3L
 */
public class ClassTool extends Tool {

  private int xStart;
  private int yStart;
  private final ScribbleCanvas canvas;

  public ClassTool(final ScribbleCanvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public String getName() {
    return "Class";
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
    canvas.resetSelected();
    canvas.repaint();
    canvas.x = e.getX();
    xStart = canvas.x;
    canvas.y = e.getY();
    yStart = canvas.y;
    Graphics g = canvas.getGraphics();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);

    drawRect(g, xStart, yStart, 1, 1);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    String text = JOptionPane.showInputDialog("Class Name");
    TwoEndsShape newShape;
    if (text != null) {
      newShape = new RectangleShape(canvas.getCurColor(),
        canvas.getFillColor(), text);
      newShape.setColor(canvas.getCurColor());
      newShape.draw(canvas.getGraphics());
      newShape.setEnds(xStart, yStart, e.getX(), e.getY());
      canvas.addShape(newShape);
    }
    canvas.repaint();
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
    drawRect(g, xStart, yStart,
      canvas.x - xStart + 1, canvas.y - yStart + 1);
    drawRect(g, xStart, yStart, e.getX() - xStart + 1, e.getY() - yStart + 1);
    canvas.x = e.getX();
    canvas.y = e.getY();

    //repaitn relations
    ShapeList shapes = canvas.getShapes();
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape instanceof Relationable) {
          shape.setEnds(e.getX(), e.getY(), e.getX(), e.getY());
          shape.draw(g);
        }
      }
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  public static void drawRect(final Graphics g, int x,
    int y, int w, int h) {
    if (w < 0) {
      x = x + w;
      w = -w;
    }
    if (h < 0) {
      y = y + h;
      h = -h;
    }
    g.drawRect(x, y, w, h);
  }

}
