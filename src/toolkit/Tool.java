package toolkit;

import java.awt.Point;

/**
 *
 * @author M16U3L
 */
public interface Tool {

  public String getName();

  public void startAction(Point p);

  public void continueAction(Point p);

  public void endAction(Point p);
}
