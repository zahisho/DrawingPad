
import java.util.EventListener;
import scribble.ScribbleCanvas;
import tools.Tool;

public class DrawingCanvas extends ScribbleCanvas {

  private DrawingCanvasListener drawingCanvasListener;

  public DrawingCanvas() {
  }

  public final void setTool(Tool tool) {
    drawingCanvasListener.setTool(tool);
  }

  public final Tool getTool() {
    return drawingCanvasListener.getTool();
  }

  // factory method
  @Override
  public final EventListener makeCanvasListener() {
    drawingCanvasListener = new DrawingCanvasListener(this);
    return drawingCanvasListener;
  }
}
