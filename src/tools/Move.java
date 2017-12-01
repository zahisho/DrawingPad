package tools;

import java.awt.Point;
import java.util.Iterator;
import model.Shape;
import model.ShapeList;
import view.ScribbleCanvas;

public class Move extends AbstractTool{
  
  private Point initPoint;
  private Point finalPoint;

  public Move(ScribbleCanvas canvas, String name) {
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
    Point movePoint = new Point(x, y);
    ShapeList shapes = canvas.getShapes();
    if(shapes != null){
      Iterator iter = shapes.iterator();
      while(iter.hasNext()){
        Shape shape = (Shape) iter.next();
        if(shape.getSelected()){
          shape.move(movePoint);          
        }
      }
    }
    canvas.repaint();
  }
  
}
