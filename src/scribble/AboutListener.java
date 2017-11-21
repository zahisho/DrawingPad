package scribble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AboutListener implements ActionListener {

  private final Scribble frame;

  public AboutListener(Scribble f) {
    frame = f;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(null,
            "DrawingPad version 1.0\nCopyright (c) Xiaoping Jia 2002",
            "About", JOptionPane.INFORMATION_MESSAGE);
  }
}
