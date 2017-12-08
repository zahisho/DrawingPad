package toolkit;

import java.awt.Point;
import main.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class TextTool implements Tool {

  private ScribbleCanvas panelCanvas;

  public TextTool(ScribbleCanvas panelCanvas) {
    this.panelCanvas = panelCanvas;
  }

  @Override
  public String getName() {
    return "Text";
  }

  @Override
  public void startAction(Point p) {
  }

  @Override
  public void continueAction(Point p) {
  }

  @Override
  public void endAction(Point p) {
  }
}
