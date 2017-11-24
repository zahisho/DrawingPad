package drawer;

public class DrawingCanvasListener extends ScribbleCanvasListener {

  public DrawingCanvasListener(DrawingCanvas canvas) {
    super(canvas, null);
  }

  public Tool getTool() {
    return tool;
  }

  public void setTool(Tool tool) {
    this.tool = tool;
  }

}
