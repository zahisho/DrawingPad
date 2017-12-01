package tools;

import java.awt.Point;
import model.Shape;
import model.ShapeList;
import view.ScribbleCanvas;

public class FillColor extends AbstractTool {
  
  private Shape shape;
  
  public FillColor(ScribbleCanvas canvas, String name) {
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
    ShapeList shapes = canvas.getShapes();
    if(shape != null){
      shape.setUndoState(shape.clon());
      
      shape.setColorFill(canvas.getCurColor());
      shape.fillColor();
      canvas.repaint();
      
      shape.getUndoState().setRedoState(shape);
      shapes.moveToTheLas(shape);
    }
  }
}
