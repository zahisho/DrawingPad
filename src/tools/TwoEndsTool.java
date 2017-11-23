package tools;

import drawing.Oval;
import drawing.Line;
import drawing.Rectangle;
import drawing.Selectable;
import java.awt.Point;
import scribble.ScribbleCanvas;
import drawing.Shape;

public class TwoEndsTool extends AbstractTool {

  public static final int LINE = 0;
  public static final int OVAL = 1;
  public static final int RECT = 2;

  private Shape shape;
  private final int option;

  public TwoEndsTool(ScribbleCanvas canvas, String name, int option) {
    super(canvas, name);
    this.option = option;
  }

  @Override
  public final void startShape(Point p) {
    canvas.mouseButtonDown = true;
    Selectable figure;
    switch (option) {
      case LINE:
        figure = new Line();
        break;
      case RECT:
        figure = new Rectangle();
        break;
      default:
        figure = new Oval();
    }
    shape = new Shape(canvas.getCurColor(), figure);
    shape.startFigure(p, canvas.getGraphics());
  }

  @Override
  public final void addPointToShape(Point p) {
    if (canvas.mouseButtonDown) {
      shape.updateFigure(p, canvas.getGraphics());
    }
  }

  @Override
  public final void endShape(Point p) {
    canvas.mouseButtonDown = false;

    shape.updateFigure(p, canvas.getGraphics());
    shape.draw(canvas.getGraphics());
    canvas.addShape(shape);
  }
}
