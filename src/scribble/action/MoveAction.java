package scribble.action;

import java.awt.Point;
import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class MoveAction extends Action {

  private final Shape shape;
  private final Point movement;

  public MoveAction(Shape s, Point p) {
    shape = s;
    movement = p;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    Point counter = new Point(-movement.x, -movement.y);
    shape.move(counter);
    return new MoveAction(shape, counter);
  }
}
