package scribble;

import scribble.drawing.ShapeList;

class Action {

  private final ShapeList previousShapes;
  private final ShapeList newShapes;

  public Action(ShapeList p, ShapeList n) {
    previousShapes = p;
    newShapes = n;
  }

  public final Action revertAction(ScribbleCanvas canvas) {
    canvas.removeShapes(newShapes);
    canvas.addShapes(previousShapes);
    canvas.repaint();
    return new Action(newShapes, previousShapes);
  }
}
