package pad;

import drawer.ToolKit;
import drawer.ScribbleTool;
import drawer.TwoEndsTool;
import drawer.Tool;
import drawer.DrawingCanvas;
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

public class DrawingPad extends Scribble {
  
  protected ToolKit toolkit;
  protected DrawingCanvas drawingCanvas;
  private static int width = 600;
  private static int height = 400;

  public DrawingPad(String title) {
    super(title);
    initTools();
    ActionListener toolListener = new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof AbstractButton) {
          AbstractButton button = (AbstractButton) source;
          Tool tool = toolkit.setSelectedTool(button.getText());
          drawingCanvas.setTool(tool);
        }
      }
    };
    JComponent toolbar = createToolBar(toolListener);
    getContentPane().add(toolbar, BorderLayout.WEST);
    JMenu menu = createToolMenu(toolListener);
    menuBar.add(menu, 1); // insert at index position 1 
  }

  public Tool getSelectedTool() {
    return toolkit.getSelectedTool();
  }

  protected void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new ScribbleTool(getCanvas(), "Scribble"));
    toolkit.addTool(new TwoEndsTool(getCanvas(), "Line", TwoEndsTool.LINE));
    toolkit.addTool(new TwoEndsTool(getCanvas(), "Oval", TwoEndsTool.OVAL));
    toolkit.addTool(new TwoEndsTool(getCanvas(), "Rectangle", TwoEndsTool.RECT));
    drawingCanvas.setTool(toolkit.getTool(0));
  }

  // factory method 
  protected ScribbleCanvas makeCanvas() {
    return (drawingCanvas = new DrawingCanvas());
  }

  protected JComponent createToolBar(ActionListener toolListener) {
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

  protected JMenu createToolMenu(ActionListener toolListener) {
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


  public static void main(String[] args) {
    JFrame frame = new DrawingPad("Drawing Pad");
    frame.setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - width / 2,
            screenSize.height / 2 - height / 2);
    frame.setVisible(true);
  }

}
