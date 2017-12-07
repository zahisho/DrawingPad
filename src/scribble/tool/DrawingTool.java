package scribble.tool;

import scribble.drawing.Figure;
import scribble.drawing.Shape;
import java.awt.event.MouseEvent;
import scribble.ScribbleCanvas;

public class DrawingTool extends Tool {

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
  public final String getName() {
    return name;
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
  }

  @Override
  public final void mousePressed(MouseEvent e) {
    canvas.clearSelectedShapes();
    Figure nFigure = figure.getFigure();
    nFigure.setContour(canvas.getCurColor());
    shape = new Shape(nFigure);
    shape.startFigure(e.getPoint(), canvas.getGraphics());
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    shape.updateFigure(e.getPoint(), canvas.getGraphics());
    shape.draw(canvas.getGraphics());
    canvas.addShape(shape);
  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    shape.updateFigure(e.getPoint(), canvas.getGraphics());
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }
}
