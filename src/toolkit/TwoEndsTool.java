package toolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import main.ScribbleCanvas;
import shape.Fillable;
import shape.LineShape;
import shape.OvalShape;
import shape.RectangleShape;
import shape.TwoEndsShape;

/**
 *
 * @author M16U3L
 */
public class TwoEndsTool implements Tool {

  public static final int LINE = 0;
  public static final int OVAL = 1;
  public static final int RECT = 2;

  private int shape = LINE;
  private int xStart;
  private int yStart;
  private final ScribbleCanvas canvas;
  private final String name;
  private static final int RGB = 255;

  public TwoEndsTool(final ScribbleCanvas canvas,
    final String name, final int shape) {
    this.canvas = canvas;
    this.name = name;
    this.shape = shape;
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final void startAction(final Point p) {
    canvas.resetSelected();
    canvas.repaint();
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
    }
  }

  @Override
  public final void continueAction(final Point p) {
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
          drawOval(g, xStart, yStart,
            canvas.x - xStart + 1, canvas.y - yStart + 1);
          drawOval(g, xStart, yStart, p.x - xStart + 1, p.y - yStart + 1);

          g.setXORMode(new Color(RGB, RGB, RGB, 0));
          g.setColor(canvas.getFillColor());
          fillOval(g, xStart, yStart,
            canvas.x - xStart + 1, canvas.y - yStart + 1);
          fillOval(g, xStart, yStart, p.x - xStart + 1, p.y - yStart + 1);
          break;
        case RECT:
          drawRect(g, xStart, yStart,
            canvas.x - xStart + 1, canvas.y - yStart + 1);
          drawRect(g, xStart, yStart, p.x - xStart + 1, p.y - yStart + 1);

          g.setXORMode(new Color(RGB, RGB, RGB, 0));
          g.setColor(canvas.getFillColor());
          fillRect(g, xStart, yStart,
            canvas.x - xStart + 1, canvas.y - yStart + 1);
          fillRect(g, xStart, yStart, p.x - xStart + 1, p.y - yStart + 1);
          break;
      }
      canvas.x = p.x;
      canvas.y = p.y;
    }
  }

  @Override
  public final void endAction(final Point p) {
    canvas.mouseButtonDown = false;
    TwoEndsShape newShape = null;
    switch (shape) {
      case LINE:
        newShape = new LineShape(canvas.getCurColor());
        break;
      case OVAL:
        newShape = new OvalShape(canvas.getCurColor(), canvas.getFillColor());
        break;
      case RECT:
        newShape = new RectangleShape(canvas.getCurColor(),
          canvas.getFillColor());
    }
    if (newShape != null) {
      newShape.setColor(canvas.getCurColor());
      if (newShape instanceof Fillable) {
        ((Fillable) newShape).fill(canvas.getFillColor());
      }
      newShape.draw(canvas.getGraphics());
      newShape.setEnds(xStart, yStart, p.x, p.y);
      canvas.addShape(newShape);
    }
    Graphics g = canvas.getGraphics();
    g.setPaintMode();
    canvas.repaint();
  }

  // helper methods
  public static void drawLine(final Graphics g, final int x1,
    final int y1, final int x2, final int y2) {
    g.drawLine(x1, y1, x2, y2);
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

  public static void drawOval(final Graphics g, int x,
    int y, int w, int h) {
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

  private void fillOval(final Graphics g, int x,
    int y, int w, int h) {
    if (w < 0) {
      x = x + w;
      w = -w;
    }
    if (h < 0) {
      y = y + h;
      h = -h;
    }
    g.fillOval(x, y, w, h);
  }

  private void fillRect(final Graphics g, int x,
    int y, int w, int h) {
    if (w < 0) {
      x = x + w;
      w = -w;
    }
    if (h < 0) {
      y = y + h;
      h = -h;
    }
    g.fillRect(x, y, w, h);
  }
}
