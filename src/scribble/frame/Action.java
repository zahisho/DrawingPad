package scribble.frame;

class Action {

  private final ShapeList previousShapes;
  private final ShapeList newShapes;

  private final ShapeList previousLayer;
  private final ShapeList newLayer;

  public Action(ShapeList p, ShapeList n, ShapeList pL, ShapeList nL) {
    previousShapes = p;
    newShapes = n;
    previousLayer = pL;
    newLayer = nL;
  }

  public final Action revertAction(ScribbleCanvas canvas) {
    canvas.removeShapes(newShapes, newLayer);
    canvas.addShapes(previousShapes, previousLayer);
    canvas.repaint();
    return new Action(newShapes, previousShapes, newLayer, previousLayer);
  }
}
