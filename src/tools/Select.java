package tools;

import java.awt.Color;
import model.Stroke;
import model.TwoEndsShape;
import java.awt.Point;
import java.util.List;
import view.ScribbleCanvas;
import model.ShapeAbstract;
import model.ShapeList;


public class Select extends AbstractTool {

  private ShapeAbstract shape;

  public Select(ScribbleCanvas canvas, String name) {
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
      if(shape.getSelected()){
        shape.setSelected(false);
      }
      else{
        shape.setSelected(true); 
        ShapeList shapes = canvas.getShapes();
        shapes.remove(shape);
        shapes.add(shape);
      }
    }
    canvas.repaint();
  }

}
