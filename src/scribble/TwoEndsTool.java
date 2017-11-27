package scribble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TwoEndsTool extends AbstractTool {

  public static final int LINE = 0;
  public static final int OVAL = 1;
  public static final int RECT = 2;

  private int shape = LINE;
  private int xStart, yStart;

  public TwoEndsTool(Canvas canvas, String name, int shape) {
    super(canvas, name);
    this.shape = shape;
  }

  @Override
  public void startAction(Point p) {
    canvas.mouseButtonDown = true;
    canvas.x = p.x;
    xStart = canvas.x;
    canvas.y = p.y;
    yStart = canvas.y;
    Graphics g = canvas.getGraphics();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    switch (shape) {
      case LINE:
        drawLine(g, xStart, yStart, xStart, yStart);
        break;
      case OVAL:
        drawOval(g, xStart, yStart, 1, 1);
        break;
      case RECT:
        drawRect(g, xStart, yStart, 1, 1);
        break;
      default:
        break;
    }
  }

  @Override
  public void continueAction(Point p) {
    if (canvas.mouseButtonDown) {
      Graphics g = canvas.getGraphics();
      g.setXORMode(canvas.getBackground());
      g.setColor(canvas.getCurColor());
      switch (shape) {
        case LINE:
          drawLine(g, xStart, yStart, canvas.x, canvas.y);
          drawLine(g, xStart, yStart, p.x, p.y);
          break;
        case OVAL:
          drawOval(g, xStart, yStart, canvas.x - xStart + 1,
            canvas.y - yStart + 1);
          drawOval(g, xStart, yStart, p.x - xStart + 1, p.y - yStart + 1);
          break;
        case RECT:
          drawRect(g, xStart, yStart, canvas.x - xStart + 1,
            canvas.y - yStart + 1);
          drawRect(g, xStart, yStart, p.x - xStart + 1, p.y - yStart + 1);
          break;
        default:
          break;
      }
      canvas.x = p.x;
      canvas.y = p.y;
    }
  }

  @Override
  public void endAction(Point p) {
    canvas.mouseButtonDown = false;
    Shape newShape = null;
    switch (shape) {
      case LINE:
        newShape = new LineShape(canvas.getCurColor());
        break;
      case OVAL:
        newShape = new OvalShape(canvas.getCurColor());
        break;
      case RECT:
        newShape = new RectangleShape(canvas.getCurColor());
        break;
      default:
        break;
    }
    if (newShape != null) {
      newShape.setColor(canvas.getCurColor());
      newShape.setEnds(xStart, yStart, p.x, p.y);
      canvas.addShape(newShape);
    }
    Graphics g = canvas.getGraphics();
    g.setPaintMode();
    canvas.repaint();
  }

  // helper methods 
  public static void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
    g.drawLine(x1, y1, x2, y2);
  }

  public static void drawRect(Graphics g, int x, int y, int w, int h) {
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

  public static void drawOval(Graphics g, int x, int y, int w, int h) {
    if (w < 0) {
      x = x + w;
      w = -w;
    }
    if (h < 0) {
      y = y + h;
      h = -h;
    }
    g.drawOval(x, y, w, h);
  }
}
