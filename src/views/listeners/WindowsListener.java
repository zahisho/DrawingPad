package views.listeners;

/**
 *
 * @author M16U3L
 */
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import views.DrawingPadFrame;
import views.DrawingpadMenu;

public class WindowsListener extends WindowAdapter {

  private final DrawingPadFrame frame;
  private DrawingpadMenu menu;

  public WindowsListener(DrawingPadFrame drawinPad, DrawingpadMenu menuDrawindPad) {
    frame = drawinPad;
    menu = menuDrawindPad;
  }

  @Override
  public void windowClosing(final WindowEvent e) {
    ExitListener exitListener = menu.getExitListener();
    if (exitListener != null) {
      exitListener.actionPerformed(new ActionEvent(frame, 0, null));
    }
  }
}
