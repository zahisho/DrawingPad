package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pad.Scribble;

/**
 *
 * @author Richi007dx
 */
public class AboutListener implements ActionListener {

  private final Scribble AboutListener;

  public AboutListener(final Scribble AboutListener) {
    this.AboutListener = AboutListener;
  }

  public void actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(null, "DrawingPad version 1.0\nCopyright (c) Xiaoping Jia 2002",
            "About", JOptionPane.INFORMATION_MESSAGE);
  }

}
