import scribble.Tool;
import scribble.ScribbleCanvas;
import java.util.EventListener;

public class DrawingCanvas extends ScribbleCanvas {
  
  protected DrawingCanvasListener drawingCanvasListener; 

  public DrawingCanvas() {
  }

  public void setTool(Tool tool) { 
    drawingCanvasListener.setTool(tool);
  }

  public Tool getTool() { 
    return drawingCanvasListener.getTool(); 
  }

  // factory method 
  protected EventListener makeCanvasListener() {
    return (drawingCanvasListener = new DrawingCanvasListener(this)); 
  }

}
