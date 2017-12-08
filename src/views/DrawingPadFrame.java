package views;

import views.listeners.WindowsListener;
import main.ScribbleCanvas;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import toolkit.ToolKit;

/**
 *
 * @author M16U3L
 */
public class DrawingPadFrame extends JFrame {

  private static int WIDTH = 800;
  private static int HEIGHT = 500;

  private ColorDialog panelColor;
  private DrawingpadMenu menuDrawingpad;
  private ScribbleCanvas panelCanvas;
  private ToolPanel tools;
  private ToolKit toolkit;
  private WindowsListener windowsExit;

  public DrawingPadFrame(String title) {
    super(title);
    setFocusable(true);
    init();
    initCanvas();
    initTools();
    initColors();
    initMenu();
    windowsExit = new WindowsListener(this, menuDrawingpad);
    addWindowListener(windowsExit);
    revalidate();
  }

  private void init() {
    setVisible(true);
    setSize(WIDTH, HEIGHT);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screenSize.width / 2 - WIDTH / 2,
      screenSize.height / 2 - HEIGHT / 2);
    getContentPane().setLayout(new BorderLayout());
  }

  private void initCanvas() {
    panelCanvas = new ScribbleCanvas();
    getContentPane().add(panelCanvas, BorderLayout.CENTER);
  }

  private void initTools() {
    toolkit = new ToolKit();
    tools = new ToolPanel(panelCanvas, toolkit);
    getContentPane().add(tools, BorderLayout.WEST);
  }

  private void initMenu() {
    menuDrawingpad = new DrawingpadMenu(panelCanvas, this, tools, panelColor);
    getContentPane().add(menuDrawingpad, BorderLayout.NORTH);

  }

  private void initColors() {
    panelColor = new ColorDialog(panelCanvas);
    getContentPane().add(panelColor, BorderLayout.EAST);
  }
}
