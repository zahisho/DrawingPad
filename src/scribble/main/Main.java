package scribble.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import scribble.frame.Scribble;

public class Main {

  private final static int WIDTH = 600;
  private final static int HEIGHT = 400;

  public static void main(String[] args) {
    Scribble frame = new Scribble("Drawing Pad");
    frame.setSize(WIDTH, HEIGHT);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - WIDTH / 2,
            screenSize.height / 2 - HEIGHT / 2);
    frame.setVisible(true);
  }
}
