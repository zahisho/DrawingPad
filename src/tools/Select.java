package tools;

import java.awt.Color;
import model.Stroke;
import model.TwoEndsShape;
import java.awt.Point;
import java.util.List;
import view.ScribbleCanvas;
import model.Shape;


public class Select extends AbstractTool {

  private Shape shape;

  public Select(ScribbleCanvas canvas, String name) {
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
      if(shape.getSelected()){
        shape.setSelected(false);
        shape.setColor(Color.black);
      }
      else{
        shape.setSelected(true);  
        shape.setColor(Color.yellow);
      }
    }
    canvas.repaint();
  }

}
