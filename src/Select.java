
import java.awt.Point;
import scribble.AbstractTool;
import scribble.ScribbleCanvas;


public class Select extends AbstractTool{
  
  private TwoEndsShape shape;
  private Point pointInit;
  private Point pointfinal;

  public Select(ScribbleCanvas canvas, String name){
    super(canvas, name);
  }
  
  @Override
  public void startShape(Point p) {
    shape = (TwoEndsShape) canvas.belongShape(p);
    pointInit = p;
  }
  
  @Override
  public void addPointToShape(Point p) {
    
  }
  
  @Override
  public void endShape(Point p) {
    if(shape !=null){
      pointfinal = p;
      int x = pointInit.x - pointfinal.x;
      int y = pointInit.y - pointfinal.y;
      shape.x1 = shape.x1 - x;
      shape.x2 = shape.x2 - x;
      shape.y1 = shape.y1 - y;
      shape.y2 = shape.y2 - y;
      canvas.repaint();
    }
  }
  
}
