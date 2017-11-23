package tools;

import drawing.Shape;
import drawing.Stroke;
import java.awt.Point;
import scribble.ScribbleCanvas;

public class ScribbleTool extends AbstractTool {

  private Shape shape;

  public ScribbleTool(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  @Override
  public final void startShape(Point p) {
    shape = new Shape(canvas.getCurColor(), new Stroke());
    shape.startFigure(p, canvas.getGraphics());
  }

  @Override
  public final void addPointToShape(Point p) {
    shape.updateFigure(p, canvas.getGraphics());
  }

  @Override
  public final void endShape(Point p) {
    shape.updateFigure(p, canvas.getGraphics());
    shape.draw(canvas.getGraphics());
    canvas.addShape(shape);
  }
}
