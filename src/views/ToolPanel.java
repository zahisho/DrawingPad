package views;

import umlObjects.objects.Interface;
import umlObjects.objects.UmlClass;
import umlObjects.objects.UmlAbstractClass;
import shape.Text;
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
import umlObjects.relations.AgregationLink;
import umlObjects.relations.AssociationLink;
import umlObjects.relations.DirectAssociationLink;
import umlObjects.relations.CompositionLink;
import umlObjects.relations.InheritanceLink;
import umlObjects.relations.ImplementationLink;
import toolkit.AbstractClassTool;
import toolkit.InterfaceTool;
import toolkit.ClassTool;
import toolkit.RelasionshipTool;
import toolkit.InheritanceTool;
import toolkit.ImplementsTool;
import toolkit.Tool;
import toolkit.SelectorTool;

/**
 *
 * @author M16U3L
 */
public class ToolPanel extends JPanel {

  private ToolKit toolkit;
  private ScribbleCanvas canvas;
  private ActionListener toolListener;
  private boolean isVisible;

  public ToolPanel(ScribbleCanvas panelCanvas, ToolKit toolKit) {
    this.canvas = panelCanvas;
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
    toolkit.addTool(new SelectorTool(canvas));
    toolkit.addTool(new ClassTool(canvas, new UmlClass(),
      "Class"));
    toolkit.addTool(new AbstractClassTool(canvas, new UmlAbstractClass(),
      "Abstract Class"));
    toolkit.addTool(new InterfaceTool(canvas, new Interface(),
      "Interface"));
    toolkit.addTool(new InheritanceTool(canvas, new InheritanceLink(),
      "Inheritance"));
    toolkit.addTool(new ImplementsTool(canvas, new ImplementationLink(),
      "Implements"));
    toolkit.addTool(new RelasionshipTool(canvas, new DirectAssociationLink(),
      "Assosiation Direct"));
    toolkit.addTool(new RelasionshipTool(canvas, new AssociationLink(),
      "Assosiation"));
    toolkit.addTool(new RelasionshipTool(canvas, new AgregationLink(),
      "Agregation"));
    toolkit.addTool(new RelasionshipTool(canvas, new CompositionLink(),
      "Composition"));
    toolkit.addTool(new TextTool(canvas, new Text(), "Text"));
    canvas.setTool(toolkit.getTool(1));
    canvas.requestFocusInWindow();
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
