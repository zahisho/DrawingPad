package scribble;

import drawing.Line;
import drawing.Oval;
import drawing.Rectangle;
import drawing.Stroke;
import menu.listener.AboutListener;
import menu.listener.ColorListener;
import menu.listener.ExitListener;
import menu.listener.NewFileListener;
import menu.listener.SaveFileAsListener;
import menu.listener.SaveListener;
import menu.listener.OpenFileListener;
import menu.listener.UndoListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import tool.DrawingTool;
import tool.SelectTool;
import tool.Tool;
import tool.ToolKit;

public class Scribble extends JFrame {

  private final ScribbleCanvas canvas;
  private JMenuBar menuBar;

  private String currentFilename;
  private final JFileChooser chooser;

  private ToolKit toolkit;

  private AboutListener aboutListener;
  private ColorListener colorListener;
  private ExitListener exitListener;
  private NewFileListener newFileListener;
  private SaveFileAsListener saveFileAsListener;
  private SaveListener saveListener;
  private OpenFileListener openFileListener;
  private UndoListener undoListener;

  private final ScribbleWindowAdapter windowAdapter;

  public Scribble(String title) {
    super(title);
    chooser = new JFileChooser(".");
    currentFilename = null;
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    windowAdapter = new ScribbleWindowAdapter(this);
    addWindowListener(windowAdapter);
    initTools();
    initToolBar();
  }

  public final JFileChooser getChooser() {
    return chooser;
  }

  public final ScribbleCanvas getCanvas() {
    return canvas;
  }

  public final ExitListener getExitListener() {
    return exitListener;
  }

  private void createMenuBar() {
    menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem mi;

    // File menu
    menu = new JMenu("File");
    menuBar.add(menu);

    newFileListener = new NewFileListener(this);
    mi = new JMenuItem("New");
    menu.add(mi);
    mi.addActionListener(newFileListener);

    openFileListener = new OpenFileListener(this);
    mi = new JMenuItem("Open");
    menu.add(mi);
    mi.addActionListener(openFileListener);

    saveListener = new SaveListener(this);
    mi = new JMenuItem("Save");
    menu.add(mi);
    mi.addActionListener(saveListener);

    saveFileAsListener = new SaveFileAsListener(this);
    mi = new JMenuItem("Save As");
    menu.add(mi);
    mi.addActionListener(saveFileAsListener);

    menu.add(new JSeparator());

    exitListener = new ExitListener(this);
    mi = new JMenuItem("Exit");
    menu.add(mi);
    mi.addActionListener(exitListener);

    // edit menu
    menu = new JMenu("Edit");
    menuBar.add(menu);

    undoListener = new UndoListener(this);
    mi = new JMenuItem("Undo");
    menu.add(mi);
    mi.addActionListener(undoListener);

    // option menu
    menu = new JMenu("Option");
    menuBar.add(menu);

    colorListener = new ColorListener(this);
    mi = new JMenuItem("Color");
    menu.add(mi);
    mi.addActionListener(colorListener);

    // horizontal space
    menuBar.add(Box.createHorizontalGlue());

    // Help menu
    menu = new JMenu("Help");
    menuBar.add(menu);

    aboutListener = new AboutListener(this);
    mi = new JMenuItem("About");
    menu.add(mi);
    mi.addActionListener(aboutListener);
  }

  public final void undo() {
    canvas.undo();
  }

  private ScribbleCanvas makeCanvas() {
    return new ScribbleCanvas();
  }

  public final void newFile() {
    currentFilename = null;
    canvas.newFile();
    setTitle("Scribble Pad");
  }

  public final void openFile(String filename) {
    currentFilename = filename;
    canvas.openFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  public final void saveFile() {
    if (currentFilename == null) {
      currentFilename = "Untitled";
    }
    canvas.saveFile(currentFilename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  public final void saveFileAs(String filename) {
    currentFilename = filename;
    canvas.saveFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  private void initToolBar() {
    ActionListener toolListener = (ActionEvent event) -> {
      Object source = event.getSource();
      if (source instanceof AbstractButton) {
        AbstractButton button = (AbstractButton) source;
        Tool tool = toolkit.setSelectedTool(button.getText());
        canvas.setTool(tool);
      }
    };
    JComponent toolbar = createToolBar(toolListener);
    getContentPane().add(toolbar, BorderLayout.WEST);
    JMenu menu = createToolMenu(toolListener);
    menuBar.add(menu, 2); // insert at index position 1
  }

  public final Tool getSelectedTool() {
    return toolkit.getSelectedTool();
  }

  private void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new DrawingTool(canvas, new Stroke(), "Scribble"));
    toolkit.addTool(new DrawingTool(canvas, new Line(), "Line"));
    toolkit.addTool(new DrawingTool(canvas, new Oval(), "Oval"));
    toolkit.addTool(new DrawingTool(canvas, new Rectangle(), "Rectangle"));
    toolkit.addTool(new SelectTool(canvas));
    canvas.setTool(toolkit.getTool(0));
  }

  private JComponent createToolBar(ActionListener toolListener) {
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

  private JMenu createToolMenu(ActionListener toolListener) {
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
