package scribble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import scribble.frame.Scribble;

public class AboutMenuItem extends JMenuItem implements ActionListener {

  private final Scribble frame;

  public AboutMenuItem(Scribble f) {
    super("About");
    frame = f;
    addListener();
  }

  private void addListener() {
    addActionListener(this);
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(null,
            "DrawingPad version 1.0\nCopyright (c) Xiaoping Jia 2002",
            "About", JOptionPane.INFORMATION_MESSAGE);
  }
}
