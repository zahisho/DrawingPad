package scribble;

public abstract class AbstractTool implements Tool {

  protected Canvas canvas;
  protected String name;

  @Override
  public String getName() {
    return name;
  }

  protected AbstractTool(Canvas canvas, String name) {
    this.canvas = canvas;
    this.name = name;
  }
}
