import java.awt.Point;
import java.util.List;
import scribble.AbstractTool;
import scribble.ScribbleCanvas;
import scribble.Shape;
import scribble.Stroke;


public class Select extends AbstractTool {

  private TwoEndsShape shapeTwoEnds;
  private Stroke shapeStroke;
  private Shape shape;
  private Point initPoint;
  private Point finalPoint;

  public Select(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  @Override
  public void startShape(Point p) {
    initPoint = p;
    shape =  canvas.belongShape(p);
    
    if(shape instanceof TwoEndsShape){
      shapeTwoEnds = (TwoEndsShape) shape;
    }
    if(shape instanceof Stroke){
      shapeStroke = (Stroke) shape;
      
    }
  }

  @Override
  public void addPointToShape(Point p) {
  }

  @Override
  public void endShape(Point p) {
    finalPoint = p;
    int x = initPoint.x - finalPoint.x;
    int y = initPoint.y - finalPoint.y;

    if (shapeStroke != null) {
      List<Point> points = shapeStroke.getPoints();
      for(Point point : points){
        point.x = point.x - x;
        point.y = point.y - y;
      }
      canvas.repaint();
      shapeStroke = null;
    }
    
    if (shapeTwoEnds != null){
      shapeTwoEnds.x1 = shapeTwoEnds.x1 - x;
      shapeTwoEnds.x2 = shapeTwoEnds.x2 - x;
      shapeTwoEnds.y1 = shapeTwoEnds.y1 - y;
      shapeTwoEnds.y2 = shapeTwoEnds.y2 - y;
      canvas.repaint();
      shapeTwoEnds = null;
    }
  }

}
