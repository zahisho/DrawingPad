
import scribble.ScribbleCanvasListener;
import tools.Tool;

public class DrawingCanvasListener extends ScribbleCanvasListener {

  public DrawingCanvasListener(DrawingCanvas canvas) {
    super(canvas, null);
  }

  public final Tool getTool() {
    return tool;
  }

  public final void setTool(Tool tool) {
    this.tool = tool;
  }
}
