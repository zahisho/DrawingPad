
import scribble.Tool;
import scribble.ScribbleCanvas;
import java.util.EventListener;

public class DrawingCanvas extends ScribbleCanvas {

  private DrawingCanvasListener drawingCanvasListener;

  public DrawingCanvas() {
  }

  public final void setTool(final Tool tool) {
    drawingCanvasListener.setTool(tool);
  }

  public final Tool getTool() {
    return drawingCanvasListener.getTool();
  }

  @Override
  protected final EventListener makeCanvasListener() {
    drawingCanvasListener = new DrawingCanvasListener(this);
    return drawingCanvasListener;
  }
}
