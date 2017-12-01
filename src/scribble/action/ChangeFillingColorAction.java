package scribble.action;

import java.awt.Color;
import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class ChangeFillingColorAction extends Action {

  private final Color previousColor;
  private final Color actualColor;
  private final Shape shape;

  public ChangeFillingColorAction(Shape s, Color pre, Color act) {
    shape = s;
    previousColor = pre;
    actualColor = act;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    shape.setFillingColor(previousColor);
    return new ChangeFillingColorAction(shape, actualColor, previousColor);
  }
}
