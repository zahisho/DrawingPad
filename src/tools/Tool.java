package tools;

import java.awt.Point;

public interface Tool {

  public String getName();

  public void startShape(Point p);

  public void addPointToShape(Point p);

  public void endShape(Point p);
}
