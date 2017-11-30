package tools;

import model.Stroke;
import model.TwoEndsShape;
import java.awt.Point;
import java.util.List;
import view.ScribbleCanvas;
import model.Shape;


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
    Point movePoint = new Point(x, y);

    if (shapeStroke != null) {
      shapeStroke.move(movePoint);
      canvas.repaint();
      shapeStroke = null;
    }
    
    if (shapeTwoEnds != null){
      shapeTwoEnds.move(movePoint);
      canvas.repaint();
      shapeTwoEnds = null;
    }
  }

}
