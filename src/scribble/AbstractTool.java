package scribble;

public abstract class AbstractTool implements Tool {

  protected ScribbleCanvas canvas;
  protected String name;

  @Override
  public String getName() {
    return name;
  }

  protected AbstractTool(ScribbleCanvas canvas, String name) {
    this.canvas = canvas;
    this.name = name;
  }
}
