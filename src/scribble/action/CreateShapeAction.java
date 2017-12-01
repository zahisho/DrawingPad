package scribble.action;

import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class CreateShapeAction extends Action {

  private final Shape shape;

  public CreateShapeAction(Shape s) {
    shape = s;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    canvas.getShapes().remove(shape);
    return new RemoveShapeAction(shape);
  }
}
