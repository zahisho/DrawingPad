package scribble;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CanvasKeyListener implements KeyListener {

  private final int CTRL_CODE = 17;
  private final ScribbleCanvas canvas;

  public CanvasKeyListener(ScribbleCanvas c) {
    canvas = c;
  }

  @Override
  public final void keyTyped(KeyEvent e) {
  }

  @Override
  public final void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == CTRL_CODE) {
      canvas.setKeyCtrlPressed(true);
    }
  }

  @Override
  public final void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == CTRL_CODE) {
      canvas.setKeyCtrlPressed(false);
    }
  }
}
