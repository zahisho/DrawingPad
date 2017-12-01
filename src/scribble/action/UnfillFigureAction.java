package scribble.action;

import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class UnfillFigureAction extends Action {

  private final Shape shape;

  public UnfillFigureAction(Shape s) {
    shape = s;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    shape.setFilled(true);
    return new FillFigureAction(shape);
  }
}
