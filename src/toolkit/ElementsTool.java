package toolkit;

import shape.UmlElement;
import shape.Shape;
import shape.ShapeList;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import main.ScribbleCanvas;

public class ElementsTool extends Tool {

  private final ScribbleCanvas canvas;
  private final UmlElement figure;
  private final String name;
  private Shape initClass;

  private Shape shape;

  public ElementsTool(ScribbleCanvas canvas, UmlElement figure, String name) {
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

  }

  @Override
  public final void mouseReleased(MouseEvent e) {

  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }

}
