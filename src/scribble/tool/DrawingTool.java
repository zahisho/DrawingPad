package scribble.tool;

import scribble.drawing.Figure;
import scribble.drawing.Shape;
import java.awt.event.MouseEvent;
import scribble.frame.ScribbleCanvas;

public class DrawingTool extends Tool {

  private final ScribbleCanvas canvas;
  private final Figure figure;
  private final String name;

  private Figure nFigure;

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

    nFigure = figure.getFigure();
    nFigure.setContour(canvas.getContourColor());
    nFigure.setThickness(canvas.getThickness());
    nFigure.setLineStyle(canvas.getLineStyle());

    nFigure.startFigure(e.getPoint(), canvas.getGraphics());
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    nFigure.updateFigure(e.getPoint(), canvas.getGraphics());
    nFigure.draw(canvas.getGraphics());
    canvas.addNewShape(new Shape(nFigure));
  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    nFigure.updateFigure(e.getPoint(), canvas.getGraphics());
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }
}
