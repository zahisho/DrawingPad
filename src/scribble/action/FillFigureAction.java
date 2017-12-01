package scribble.action;

import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class FillFigureAction extends Action {

  private final Shape shape;

  public FillFigureAction(Shape s) {
    shape = s;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    shape.setFilled(false);
    return new UnfillFigureAction(shape);
  }
}
