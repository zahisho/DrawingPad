package toolkit;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Tool implements MouseListener, MouseMotionListener {

  public abstract String getName();
}
