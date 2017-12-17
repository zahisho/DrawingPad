package views;

import toolkit.TextTool;
import main.ScribbleCanvas;
import toolkit.ToolKit;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import toolkit.ClassTool;
import toolkit.SelectorTool;
import toolkit.Tool;
import toolkit.RelationTool;

/**
 *
 * @author M16U3L
 */
public class ToolPanel extends JPanel {

  private ToolKit toolkit;
  private ScribbleCanvas panelCanvas;
  private ActionListener toolListener;
  private boolean isVisible;

  public ToolPanel(ScribbleCanvas panelCanvas, ToolKit toolKit) {
    this.panelCanvas = panelCanvas;
    this.toolkit = toolKit;
    isVisible = true;
    setLayout(new GridLayout(0, 1));
    initTools();
    toolListener = (ActionEvent event) -> {
      Object source = event.getSource();
      if (source instanceof AbstractButton) {
        AbstractButton button = (AbstractButton) source;
        Tool tool = toolkit.setSelectedTool(button.getText());
        panelCanvas.setTool(tool);
      }
    };
    init();
  }

  private void initTools() {
    toolkit.addTool(new SelectorTool(panelCanvas));
    toolkit.addTool(new RelationTool(panelCanvas));
    toolkit.addTool(new ClassTool(panelCanvas));
    toolkit.addTool(new TextTool(panelCanvas));
    panelCanvas.setTool(toolkit.getTool(1));
  }

  private void init() {
    int n = toolkit.getToolCount();
    for (int i = 0; i < n; i++) {
      Tool tool = toolkit.getTool(i);
      if (tool != null) {
        JButton button = new JButton(tool.getName());
        button.addActionListener(toolListener);
        add(button);
      }
    }
  }

  public JMenu createToolMenu() {
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

  public boolean getVisiblePanel() {
    return isVisible;
  }

  public void setVisiblePanel(boolean b) {
    this.setVisible(b);
    isVisible = b;
  }
}
