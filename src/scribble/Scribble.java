package scribble;

import scribble.drawing.Line;
import scribble.drawing.Oval;
import scribble.drawing.Rectangle;
import scribble.drawing.ScribbleStroke;
import scribble.menu.listener.AboutListener;
import scribble.menu.listener.CurrentColorListener;
import scribble.menu.listener.ExitListener;
import scribble.menu.listener.NewFileListener;
import scribble.menu.listener.SaveFileAsListener;
import scribble.menu.listener.SaveListener;
import scribble.menu.listener.OpenFileListener;
import scribble.menu.listener.UndoListener;
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
import scribble.menu.listener.ContourColorListener;
import scribble.menu.listener.SelectAllListener;
import scribble.menu.listener.DeleteSelectedListener;
import scribble.menu.listener.FillingColorListener;
import scribble.menu.listener.GroupShapesListener;
import scribble.menu.listener.RedoListener;
import scribble.menu.listener.SplitShapesListener;
import scribble.tool.DrawingTool;
import scribble.tool.SelectTool;
import scribble.tool.Tool;
import scribble.tool.ToolKit;

public class Scribble extends JFrame {

  private final ScribbleCanvas canvas;
  private JMenuBar menuBar;

  private String currentFilename;
  private final JFileChooser chooser;

  private ToolKit toolkit;

  private AboutListener aboutListener;
  private CurrentColorListener colorListener;
  private ExitListener exitListener;
  private NewFileListener newFileListener;
  private SaveFileAsListener saveFileAsListener;
  private SaveListener saveListener;
  private OpenFileListener openFileListener;
  private UndoListener undoListener;
  private RedoListener redoListener;
  private GroupShapesListener groupShapesListener;
  private SplitShapesListener splitShapesListener;
  private ContourColorListener contourColorListener;
  private FillingColorListener fillingColorListener;
  private SelectAllListener selectAllListener;
  private DeleteSelectedListener deleteSelectedListener;

  private final ScribbleWindowAdapter windowAdapter;

  public Scribble(String title) {
    super(title);
    chooser = new JFileChooser(".");
    currentFilename = null;
    canvas = new ScribbleCanvas();
    canvas.addKeyListener(new CanvasKeyListener(canvas));
    canvas.setFocusable(true);
    windowAdapter = new ScribbleWindowAdapter(this);
    initLayout();
    initTools();
    initToolBar();
  }

  private void initLayout() {
    getContentPane().setLayout(new BorderLayout());
    createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    addWindowListener(windowAdapter);
  }

  private void initToolBar() {
    ActionListener toolListener = (ActionEvent event) -> {
      Object source = event.getSource();
      if (source instanceof AbstractButton) {
        AbstractButton button = (AbstractButton) source;
        Tool tool = toolkit.setSelectedTool(button.getText());
        canvas.setTool(tool);
        canvas.clearSelectedShapes();
        canvas.repaint();
      }
    };
    JComponent toolbar = createToolBar(toolListener);
    getContentPane().add(toolbar, BorderLayout.WEST);
    JMenu menu = createToolMenu(toolListener);
    menuBar.add(menu, 2);
  }

  private void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new DrawingTool(canvas, new ScribbleStroke(), "Scribble"));
    toolkit.addTool(new DrawingTool(canvas, new Line(), "Line"));
    toolkit.addTool(new DrawingTool(canvas, new Oval(), "Oval"));
    toolkit.addTool(new DrawingTool(canvas, new Rectangle(), "Rectangle"));
    SelectTool select = new SelectTool(canvas);
    toolkit.addTool(select);
    canvas.setTool(toolkit.getTool(0));
    canvas.requestFocusInWindow();
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

  private void createMenuBar() {
    menuBar = new JMenuBar();

    buildFileMenu();
    buildEditMenu();
    buildFormatMenu();
    buildOptionMenu();

    // horizontal space
    menuBar.add(Box.createHorizontalGlue());

    buildHelpMenu();
  }

  private void buildFileMenu() {
    JMenu menu;
    JMenuItem mi;

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
  }

  private void buildEditMenu() {
    JMenu menu;
    JMenuItem mi;

    menu = new JMenu("Edit");
    menuBar.add(menu);

    selectAllListener = new SelectAllListener(this);
    mi = new JMenuItem("Select all");
    menu.add(mi);
    mi.addActionListener(selectAllListener);

    groupShapesListener = new GroupShapesListener(this);
    mi = new JMenuItem("Group");
    menu.add(mi);
    mi.addActionListener(groupShapesListener);

    splitShapesListener = new SplitShapesListener(this);
    mi = new JMenuItem("Split");
    menu.add(mi);
    mi.addActionListener(splitShapesListener);

    deleteSelectedListener = new DeleteSelectedListener(this);
    mi = new JMenuItem("Delete");
    menu.add(mi);
    mi.addActionListener(deleteSelectedListener);

    undoListener = new UndoListener(this);
    mi = new JMenuItem("Undo");
    menu.add(mi);
    mi.addActionListener(undoListener);

    redoListener = new RedoListener(this);
    mi = new JMenuItem("Redo");
    menu.add(mi);
    mi.addActionListener(redoListener);
  }

  private void buildOptionMenu() {
    JMenu menu;
    JMenuItem mi;

    menu = new JMenu("Option");
    menuBar.add(menu);

    colorListener = new CurrentColorListener(this);
    mi = new JMenuItem("Color");
    menu.add(mi);
    mi.addActionListener(colorListener);
  }

  private void buildFormatMenu() {
    JMenu menu;
    JMenuItem mi;

    menu = new JMenu("Format");
    menuBar.add(menu);

    contourColorListener = new ContourColorListener(this);
    mi = new JMenuItem("Contour color");
    menu.add(mi);
    mi.addActionListener(contourColorListener);

    fillingColorListener = new FillingColorListener(this);
    mi = new JMenuItem("Filling color");
    menu.add(mi);
    mi.addActionListener(fillingColorListener);
  }

  private void buildHelpMenu() {
    JMenu menu;
    JMenuItem mi;

    menu = new JMenu("Help");
    menuBar.add(menu);

    aboutListener = new AboutListener(this);
    mi = new JMenuItem("About");
    menu.add(mi);
    mi.addActionListener(aboutListener);
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

}
