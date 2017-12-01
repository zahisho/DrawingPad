
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import scribble.ScribbleCanvas;
import scribble.Shape;
import scribble.Tool;

/**
 *
 * @author Isaac
 */
public class SelectTool implements Tool {

  private final ScribbleCanvas canvas;
  private final String name;
  private static final int HIT_BOX_SIZE = 4;
  private Point startPoint;
  private Point curPoint;
  private boolean catchAShape;
  private int xStart;
  private int yStart;
  private RectangleShape selectRect;
  private final float[] DASHED_STROKE = new float[]{5, 2};

  public SelectTool(final ScribbleCanvas canvas,
          final String name) {
    this.name = name;
    this.canvas = canvas;
    catchAShape = false;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void startAction(Point p) {
    canvas.resetSelected();
    catchAShape = false;
    List<Shape> shapes = canvas.getShapes();
    for (Shape selected : shapes) {
      if (selected.isSelected(p)) {
        canvas.addSelectedShape(selected);
        curPoint = p;
        catchAShape = true;
      }
      canvas.repaint();
    }
    if (!catchAShape) {
      canvas.mouseButtonDown = true;
      canvas.x = p.x;
      xStart = canvas.x;
      canvas.y = p.y;
      yStart = canvas.y;
      Graphics g = canvas.getGraphics();
      TwoEndsTool.drawRect(g, xStart, yStart, 1, 1);
    }
  }

  @Override
  public void continueAction(Point p) {
    if (catchAShape) {
      List<Shape> selectedShapes = canvas.getSelectedShapes();
      if (!selectedShapes.isEmpty()) {
        Point auxiliarPoint = new Point(p.x, p.y);
        p.x -= curPoint.x;
        p.y -= curPoint.y;
        for (Shape s : selectedShapes) {
          s.move(p);
          canvas.repaint();
          curPoint = auxiliarPoint;
        }
      }
    } else {
      if (canvas.mouseButtonDown) {
        Graphics g = canvas.getGraphics();
        g.setXORMode(Color.darkGray);
        g.setColor(Color.lightGray);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, DASHED_STROKE, 0));
        TwoEndsTool.drawRect(g, xStart, yStart,
                canvas.x - xStart + 1, canvas.y - yStart + 1);
        TwoEndsTool.drawRect(g, xStart, yStart,
                p.x - xStart + 1, p.y - yStart + 1);
        canvas.x = p.x;
        canvas.y = p.y;
      }
    }
  }

  @Override
  public void endAction(Point p) {
    if (!catchAShape) {
      canvas.mouseButtonDown = false;
      selectRect = new RectangleShape(Color.black);
      selectRect.setColor(canvas.getCurColor());
      selectRect.setEnds(xStart, yStart, p.x, p.y);
      Graphics g = canvas.getGraphics();
      g.setPaintMode();
      canvas.repaint();
      List<Shape> shapes = canvas.getShapes();
      shapes.stream().filter((s) ->
              (s.intersects(xStart, yStart,
                      p.x - xStart, p.y - yStart))).map((s) -> {
        s.setSelected(g);
        return s;
      }).map((s) -> {
        canvas.addSelectedShape(s);
        return s;
      }).forEachOrdered((_item) -> {
        canvas.repaint();
      });
      canvas.repaint();
    }
  }

}
