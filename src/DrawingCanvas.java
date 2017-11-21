import java.util.EventListener;
import scribble.ScribbleCanvas;
import scribble.Tool;

public class DrawingCanvas extends ScribbleCanvas {

  public DrawingCanvas() {}

  public final void setTool(Tool tool) {
    drawingCanvasListener.setTool(tool);
  }

  public final Tool getTool() {
    return drawingCanvasListener.getTool();
  }

  // factory method
  @Override
  protected final EventListener makeCanvasListener() {
    drawingCanvasListener = new DrawingCanvasListener(this);
    return drawingCanvasListener;
  }

  protected DrawingCanvasListener drawingCanvasListener;
}
