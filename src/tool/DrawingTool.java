package tool;

import drawing.Figure;
import drawing.Shape;
import java.awt.Point;
import scribble.ScribbleCanvas;

public class DrawingTool implements Tool {

  private final ScribbleCanvas canvas;
  private final Figure figure;
  private final String name;

  private Shape shape;

  public DrawingTool(ScribbleCanvas canvas, Figure figure, String name) {
    this.canvas = canvas;
    this.figure = figure;
    this.name = name;
  }

  @Override
  public final void startAction(Point p) {
    shape = new Shape(canvas.getCurColor(), figure.getFigure());
    shape.startFigure(p, canvas.getGraphics());
  }

  @Override
  public final void continueAction(Point p) {
    shape.updateFigure(p, canvas.getGraphics());
  }

  @Override
  public final void endAction(Point p) {
    shape.updateFigure(p, canvas.getGraphics());
    shape.draw(canvas.getGraphics());
    canvas.addShape(shape);
  }

  @Override
  public String getName() {
    return name;
  }
}
