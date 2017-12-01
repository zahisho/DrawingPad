package view;

import tools.TwoEndsTool;
import tools.Select;
import tools.SelectAll;
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
import tools.ChangeColor;
import tools.ClearShape;
import tools.FillColor;
import tools.GroupFigures;
import tools.Move;
import tools.Tool;
import tools.ScribbleTool;

public class DrawingPad extends Scribble {
  
  protected ToolKit toolkit;
  protected DrawingCanvas drawingCanvas;

  public DrawingPad(String title) {
    super(title);
    init();
    initTools();
    
    ActionListener toolListener = (ActionEvent event) -> {
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
    menuBar.add(menu, 2); // insert at index position 2 
    revalidate();
  }
  
  private void init(){
    setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screenSize.width  / 2 - width  / 2,
                screenSize.height / 2 - height / 2);
    setVisible(true);
  }
  
  private void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new ScribbleTool(canvas, "Scribble"));
    toolkit.addTool(new TwoEndsTool(canvas,  "Line", TwoEndsTool.LINE));
    toolkit.addTool(new TwoEndsTool(canvas,  "Oval", TwoEndsTool.OVAL));
    toolkit.addTool(new TwoEndsTool(canvas,  "Rectangle", TwoEndsTool.RECT));
    toolkit.addTool(new ChangeColor(canvas, "Change Color"));
    toolkit.addTool(new FillColor(canvas, "Fill Color"));
    toolkit.addTool(new Select(canvas, "Select"));
    toolkit.addTool(new GroupFigures(canvas, "Group Figures"));
    toolkit.addTool(new Move(canvas, "Move"));
    toolkit.addTool(new SelectAll(canvas, "Move All"));
    toolkit.addTool(new ClearShape(canvas, "Clear Shape"));
    drawingCanvas.setTool(toolkit.getTool(0));
  }

  // factory method 
  @Override
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

}
