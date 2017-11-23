
import java.awt.Point;
import scribble.AbstractTool;
import scribble.ScribbleCanvas;


public class Select extends AbstractTool{

  public Select(ScribbleCanvas canvas, String name){
    super(canvas, name);
  }
  
  @Override
  public void startShape(Point p) {
    if (canvas.belongShape(p)) {
      System.out.println("Existe Linea");
    } else {
      System.out.println("no Existe");
    }
  }

  @Override
  public void addPointToShape(Point p) {
    
  }

  @Override
  public void endShape(Point p) {
    
  }
  
}
