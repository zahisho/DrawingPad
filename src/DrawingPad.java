
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import scribble.ScribbleCanvas;
import scribble.Tool;
import scribble.Scribble;
import scribble.ScribbleTool;

public class DrawingPad extends Scribble {

  public DrawingPad(final String title) {
    super(title);
    initTools();
    ActionListener toolListener = (final ActionEvent event) -> {
      Object source = event.getSource();
      if (source instanceof AbstractButton) {
        AbstractButton button = (AbstractButton) source;
        Tool tool = toolkit.setSelectedTool(button.getText());
        drawingCanvas.setTool(tool);
      }
    };
    JComponent toolbar = createToolBar(toolListener);
    getContentPane().add(toolbar, BorderLayout.WEST);
    JMenu menu = createToolMenu(toolListener);
    menuBar.add(menu, 1);
  }

  public final Tool getSelectedTool() {
    return toolkit.getSelectedTool();
  }

  protected final void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new ScribbleTool(canvas, "Scribble"));
    toolkit.addTool(new TwoEndsTool(canvas, "Line", TwoEndsTool.LINE));
    toolkit.addTool(new TwoEndsTool(canvas, "Oval", TwoEndsTool.OVAL));
    toolkit.addTool(new TwoEndsTool(canvas, "Rectangle", TwoEndsTool.RECT));
    toolkit.addTool(new SelectTool(canvas, "Select"));
    drawingCanvas.setTool(toolkit.getTool(0));
  }

  @Override
  protected final ScribbleCanvas makeCanvas() {
    drawingCanvas = new DrawingCanvas();
    return (drawingCanvas);
  }

  protected final JComponent createToolBar(final ActionListener toolListener) {
    JPanel toolbar = new JPanel(new GridLayout(0, 1));
    int n = toolkit.getToolCount();
    for (int i = 0; i < n; i++) {
      Tool tool = toolkit.getTool(i);
      if (tool != null) {
        JButton button = new JButton(tool.getName());
        button.addActionListener(toolListener);
        toolbar.add(button);
      }
    }
    return toolbar;
  }

  protected final JMenu createToolMenu(final ActionListener toolListener) {
    JMenu menu = new JMenu("Tools");
    int n = toolkit.getToolCount();
    for (int i = 0; i < n; i++) {
      Tool tool = toolkit.getTool(i);
      if (tool != null) {
        JMenuItem menuitem = new JMenuItem(tool.getName());
        menuitem.addActionListener(toolListener);
        menu.add(menuitem);
      }
    }
    return menu;
  }

  private ToolKit toolkit;
  private DrawingCanvas drawingCanvas;

  public static void main(final String[] args) {
    JFrame frame = new DrawingPad("Drawing Pad");
    frame.setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - width / 2,
            screenSize.height / 2 - height / 2);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  }

}
