package tools;

import java.awt.Point;
import view.ScribbleCanvas;
import model.ShapeAbstract;
import model.ShapeList;

public class ClearShape extends AbstractTool{

  private ShapeAbstract shape;
  
  public ClearShape(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }
  
  @Override
  public void startAction(Point p) {
  }

  @Override
  public void continueAction(Point p) {
  }

  @Override
  public void endAction(Point p) {
    shape =  canvas.belongShape(p);
    if(shape != null){
      ShapeList shapes = canvas.getShapes();
      shapes.remove(shape);
      canvas.repaint();
    }
  }
}
