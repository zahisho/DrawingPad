
import scribble.Tool;
import scribble.ScribbleCanvas;
import java.util.EventListener;

public class DrawingCanvas extends ScribbleCanvas {

  private DrawingCanvasListener drawingCanvasListener;

  public void setTool(Tool tool) {
    drawingCanvasListener.setTool(tool);
  }

  public Tool getTool() {
    return drawingCanvasListener.getTool();
  }

  // factory method 
  @Override
  protected EventListener makeCanvasListener() {
    return (drawingCanvasListener = new DrawingCanvasListener(this));
  }

}
