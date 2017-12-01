package scribble.action;

import scribble.ScribbleCanvas;

public abstract class Action {

  public abstract Action revertAction(ScribbleCanvas canvas);
}
