package tools;


import model.Stroke;
import model.TwoEndsShape;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import view.ScribbleCanvas;
import model.Shape;
import model.ShapeList;

public class SelectAll extends AbstractTool{
  private Point initPoint;
  private Point finalPoint;

  public SelectAll(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }
  
  @Override
  public void startShape(Point p) {
    initPoint = p;
  }

  @Override
  public void addPointToShape(Point p) {
  }

  @Override
  public void endShape(Point p) {
    finalPoint = p;
    int x = initPoint.x - finalPoint.x;
    int y = initPoint.y - finalPoint.y;
    ShapeList shapes = canvas.getShapes();
    if(shapes != null){
      Iterator iter = shapes.iterator();
      while (iter.hasNext()){
        Shape shape = (Shape) iter.next();
        if (shape instanceof TwoEndsShape){
          modifyPositionTwoEnd((TwoEndsShape)shape, x, y);
        }
        if (shape instanceof Stroke){
          modifyPositionStroke((Stroke)shape, x, y);
        }
      }
    }
    canvas.repaint();
  }

  private void modifyPositionTwoEnd(TwoEndsShape shape, int x, int y) {
    shape.x1 = shape.x1 - x;
    shape.x2 = shape.x2 - x;
    shape.y1 = shape.y1 - y;
    shape.y2 = shape.y2 - y;
  }

  private void modifyPositionStroke(Stroke shape, int x, int y){
    List<Point> points =  shape.getPoints();
    for (Point point : points) {
      point.x = point.x - x;
      point.y = point.y - y;
    }
  }
}
