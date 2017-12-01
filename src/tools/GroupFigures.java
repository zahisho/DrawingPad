package tools;

import java.awt.Point;
import java.util.Iterator;
import model.Shape;
import model.ShapeList;
import view.ScribbleCanvas;

public class GroupFigures extends AbstractTool {

  public GroupFigures(ScribbleCanvas canvas, String name) {
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
    ShapeList shapes = canvas.getShapes();
    if(shapes != null){
      Iterator iter = shapes.iterator();
      while(iter.hasNext()){
        Shape shape = (Shape) iter.next();
        if(shape.getSelected()){
          shape.setUndoState(shape.clon());
          shape.groupFigure(p);
          shape.getUndoState().setRedoState(shape); 
        }
      }
    }
    canvas.repaint();
  }
  
}
