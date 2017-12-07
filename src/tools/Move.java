package tools;

import java.awt.Point;
import java.util.Iterator;
import model.ShapeAbstract;
import model.ShapeList;
import view.ScribbleCanvas;

public class Move extends AbstractTool{
  
  private Point curPoint;

  public Move(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  @Override
  public void startAction(Point p) {
    canvas.mouseButtonDown = true;
    curPoint = p;
  }

  @Override
  public void continueAction(Point p) {
    Point actualPoint = new Point(p.x, p.y);
    ShapeList shapes = canvas.getShapes();
    if(shapes != null){
      Iterator iter = shapes.iterator();
      p.x -= curPoint.x;
      p.y -= curPoint.y;
      while(iter.hasNext()){
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape.getSelected()){
          shape.move(p);
          canvas.repaint();
          curPoint = actualPoint;
        }
      }
    }
  }

  @Override
  public void endAction(Point p) {
    canvas.repaint();
  }

}
