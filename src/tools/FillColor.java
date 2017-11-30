package tools;

import java.awt.Point;
import model.Shape;
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
    if(shape != null){
      shape.setColor(canvas.getCurColor());
      shape.fillColor();
      canvas.repaint();
    }
  }
}
