package toolkit;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author M16U3L
 */
public abstract class Tool implements MouseListener, MouseMotionListener {

  public abstract String getName();
}
