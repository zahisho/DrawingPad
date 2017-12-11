package scribble.tool;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import scribble.drawing.Shape;
import java.awt.event.MouseEvent;
import scribble.frame.ScribbleCanvas;
import scribble.drawing.Text;

public class TextTool extends Tool implements KeyListener {

  private final int FST_PRINTABLE_CHAR = 32;
  private final int LST_PRINTABLE_CHAR = 126;

  private final ScribbleCanvas canvas;
  private Text text;

  public TextTool(ScribbleCanvas c) {
    canvas = c;
  }

  @Override
  public final String getName() {
    return "Text";
  }

  public final void endTyping() {
    if (text != null && !text.empty()) {
      canvas.addNewShape(new Shape(text));
    }
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
    endTyping();
    Point p = e.getPoint();
    text = new Text(p.x, p.y, canvas.getGraphics().getFontMetrics());
    text.setContour(canvas.getContourColor());
    text.draw(canvas.getGraphics());
  }

  @Override
  public final void mousePressed(MouseEvent e) {
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }

  @Override
  public final void keyTyped(KeyEvent e) {
    if (text != null) {
      char ch = e.getKeyChar();
      switch (ch) {
        case KeyEvent.VK_BACK_SPACE:
          text.removeLastLetter(canvas.getGraphics());
          break;
        default:
          if (ch >= FST_PRINTABLE_CHAR && ch <= LST_PRINTABLE_CHAR) {
            text.addLetter(e.getKeyChar(), canvas.getGraphics());
          }
      }
      text.draw(canvas.getGraphics());
    }
  }

  @Override
  public final void keyPressed(KeyEvent e) {
  }

  @Override
  public final void keyReleased(KeyEvent e) {
  }
}
