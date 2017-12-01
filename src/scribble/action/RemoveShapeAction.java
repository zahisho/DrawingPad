package scribble.action;

import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class RemoveShapeAction extends Action {

  private final Shape shape;

  public RemoveShapeAction(Shape s) {
    shape = s;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    canvas.addShape(shape);
    return new CreateShapeAction(shape);
  }
}
