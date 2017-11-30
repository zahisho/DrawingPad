package tools;

import java.awt.Point;
import view.ScribbleCanvas;
import model.Shape;
import model.ShapeList;

public class ClearShape extends AbstractTool{

  private Shape shape;
  
  public ClearShape(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }
  
  @Override
  public void startShape(Point p) {
  }

  @Override
  public void addPointToShape(Point p) {
  }

  @Override
  public void endShape(Point p) {
    shape =  canvas.belongShape(p);
    if(shape != null){
      ShapeList shapes = canvas.getShapes();
      shapes.remove(shape);
      canvas.repaint();
    }
  }
}
