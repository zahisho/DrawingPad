package scribble;

import java.awt.Point;

public interface Tool {

  public String getName();

  public void startAction(Point p);

  public void continueAction(Point p);

  public void endAction(Point p);

}
