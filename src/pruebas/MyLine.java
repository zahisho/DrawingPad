package pruebas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import scribble.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class MyLine extends MyShape {

  public MyLine(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  public MyLine(Color color) {
    super(color);
  }

  @Override
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public void startShape(Point p) {
    canvas.mouseButtonDown = true;
    canvas.x = p.x;
    xStart = canvas.x;
    canvas.y = p.y;
    yStart = canvas.y;
    Graphics g = canvas.getGraphics();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    drawLine(g, xStart, yStart, xStart, yStart);
  }

  @Override
  public void addPointToShape(Point p) {
    if (canvas.mouseButtonDown) {
      Graphics g = canvas.getGraphics();
      g.setXORMode(Color.darkGray);
      g.setColor(Color.lightGray);
      drawLine(g, xStart, yStart, canvas.x, canvas.y);
      drawLine(g, xStart, yStart, p.x, p.y);

      canvas.x = p.x;
      canvas.y = p.y;
    }
  }

  @Override
  public void endShape(Point p) {
    canvas.mouseButtonDown = false;
    MyShape newShape = null;
    newShape = new MyLine(canvas.getCurColor());

    if (newShape != null) {
      newShape.setColor(canvas.getCurColor());
      newShape.setEnds(xStart, yStart, p.x, p.y);
      //canvas.addShape(newShape);
    }
    Graphics g = canvas.getGraphics();
    g.setPaintMode();
    canvas.repaint();
  }
}
