package umlObjects;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author M16U3L
 */
public interface Subject {

  public void notifyUpdate(Graphics g);

  public Point getReference();
}
