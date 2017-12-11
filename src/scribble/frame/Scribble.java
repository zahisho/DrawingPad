package scribble.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import scribble.drawing.Line;
import scribble.drawing.Oval;
import scribble.drawing.Rectangle;
import scribble.drawing.ScribbleStroke;
import scribble.menu.AboutMenuItem;
import scribble.menu.NewFileListener;
import scribble.menu.SaveFileAsListener;
import scribble.menu.SaveListener;
import scribble.menu.OpenFileListener;
import scribble.menu.UndoMenuItem;
import scribble.menu.ContourColorMenuItem;
import scribble.menu.SelectAllMenuItem;
import scribble.menu.DeleteSelectedMenuItem;
import scribble.menu.FillingColorMenuItem;
import scribble.menu.GroupShapesMenuItem;
import scribble.menu.ShowLayersMenu;
import scribble.menu.LineStyleMenu;
import scribble.menu.MoveLayerBottomMenuItem;
import scribble.menu.MoveLayerTopMenuItem;
import scribble.menu.MoveLayerBackMenuItem;
import scribble.menu.MoveLayerFrontMenuItem;
import scribble.menu.NewLayerMenuItem;
import scribble.menu.RedoMenuItem;
import scribble.menu.RemoveLayerMenuItem;
import scribble.menu.ShowToolbarCheckbox;
import scribble.menu.SplitShapesMenuItem;
import scribble.menu.ThicknessMenu;
import scribble.tool.DrawingTool;
import scribble.tool.SelectTool;
import scribble.tool.TextTool;
import scribble.tool.Tool;
import scribble.tool.ToolKit;

public class Scribble extends JFrame {

  private final ScribbleCanvas canvas;

  private JMenuBar menuBar;
  private JPanel toolbar;

  private String currentFilename;
  private final JFileChooser chooser;

  private ToolKit toolkit;

  private AboutMenuItem aboutMenuItem;
  private ExitListener exitListener;
  private NewFileListener newFileListener;
  private SaveFileAsListener saveFileAsListener;
  private SaveListener saveListener;
  private OpenFileListener openFileListener;
  private UndoMenuItem undoMenuItem;
  private RedoMenuItem redoMenuItem;
  private GroupShapesMenuItem groupShapesMenuItem;
  private SplitShapesMenuItem splitShapesMenuItem;
  private ContourColorMenuItem contourColorMenuItem;
  private FillingColorMenuItem fillingColorMenuItem;
  private SelectAllMenuItem selectAllMenuItem;
  private DeleteSelectedMenuItem deleteSelectedMenuItem;
  private ThicknessMenu thicknessMenu;
  private LineStyleMenu lineStyleMenu;
  private ShowToolbarCheckbox showToolbarCheckbox;
  private MoveLayerBottomMenuItem moveLayerBottomMenuItem;
  private MoveLayerTopMenuItem moveLayerTopMenuItem;
  private MoveLayerBackMenuItem moveLayerBackMenuItem;
  private MoveLayerFrontMenuItem moveLayerFrontMenuItem;
  private NewLayerMenuItem newLayerMenuItem;
  private RemoveLayerMenuItem removeLayerMenuItem;
  private ShowLayersMenu showLayersMenu;

  private DrawingTool scribbleTool;
  private DrawingTool lineTool;
  private DrawingTool ovalTool;
  private DrawingTool rectangleTool;
  private SelectTool selectTool;
  private TextTool textTool;

  private final ScribbleWindowAdapter windowAdapter;

  public Scribble(String title) {
    super(title);
    chooser = new JFileChooser(".");
    currentFilename = null;

    canvas = new ScribbleCanvas(this);

    windowAdapter = new ScribbleWindowAdapter(this);

    initScribble();
    initTools();
    initToolComponents();
  }

  private void initScribble() {
    addWindowListener(windowAdapter);
    setLayout(new BorderLayout());
    initMenuBar();
    initCanvas();
  }

  private void initMenuBar() {
    menuBar = new JMenuBar();

    buildFileMenu();
    buildEditMenu();
    buildFormatMenu();
    buildWindowMenu();

    // horizontal space
    menuBar.add(Box.createHorizontalGlue());

    buildHelpMenu();

    disableUndo();
    disableRedo();
    disableEditOptions();

    add(menuBar, BorderLayout.NORTH);
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

    menu = new JMenu("Edit");
    menuBar.add(menu);

    selectAllMenuItem = new SelectAllMenuItem(this);
    menu.add(selectAllMenuItem);

    groupShapesMenuItem = new GroupShapesMenuItem(this);
    menu.add(groupShapesMenuItem);

    splitShapesMenuItem = new SplitShapesMenuItem(this);
    menu.add(splitShapesMenuItem);

    deleteSelectedMenuItem = new DeleteSelectedMenuItem(this);
    menu.add(deleteSelectedMenuItem);

    undoMenuItem = new UndoMenuItem(this);
    menu.add(undoMenuItem);

    redoMenuItem = new RedoMenuItem(this);
    menu.add(redoMenuItem);

    menu.add(new JSeparator());

    moveLayerBottomMenuItem = new MoveLayerBottomMenuItem(this);
    menu.add(moveLayerBottomMenuItem);

    moveLayerTopMenuItem = new MoveLayerTopMenuItem(this);
    menu.add(moveLayerTopMenuItem);

    moveLayerBackMenuItem = new MoveLayerBackMenuItem(this);
    menu.add(moveLayerBackMenuItem);

    moveLayerFrontMenuItem = new MoveLayerFrontMenuItem(this);
    menu.add(moveLayerFrontMenuItem);

    newLayerMenuItem = new NewLayerMenuItem(this);
    menu.add(newLayerMenuItem);

    removeLayerMenuItem = new RemoveLayerMenuItem(this);
    menu.add(removeLayerMenuItem);

    showLayersMenu = new ShowLayersMenu(this);
    menu.add(showLayersMenu);
  }

  private void buildFormatMenu() {
    JMenu menu;

    menu = new JMenu("Format");
    menuBar.add(menu);

    contourColorMenuItem = new ContourColorMenuItem(this);
    menu.add(contourColorMenuItem);

    fillingColorMenuItem = new FillingColorMenuItem(this);
    menu.add(fillingColorMenuItem);

    thicknessMenu = new ThicknessMenu(this);
    menu.add(thicknessMenu);

    lineStyleMenu = new LineStyleMenu(this);
    menu.add(lineStyleMenu);
  }

  private void buildWindowMenu() {
    JMenu menu;

    menu = new JMenu("Window");
    menuBar.add(menu);

    showToolbarCheckbox = new ShowToolbarCheckbox(this);
    showToolbarCheckbox.setSelected(true);
    menu.add(showToolbarCheckbox);
  }

  private void buildHelpMenu() {
    JMenu menu;

    menu = new JMenu("Help");
    menuBar.add(menu);

    aboutMenuItem = new AboutMenuItem(this);
    menu.add(aboutMenuItem);
  }

  private void initCanvas() {
    canvas.requestFocusInWindow();
    canvas.setFocusable(true);
    add(canvas, BorderLayout.CENTER);
  }

  private void initTools() {
    toolkit = new ToolKit();

    scribbleTool = new DrawingTool(canvas, new ScribbleStroke(), "Scribble");
    toolkit.addTool(scribbleTool);

    lineTool = new DrawingTool(canvas, new Line(), "Line");
    toolkit.addTool(lineTool);

    ovalTool = new DrawingTool(canvas, new Oval(), "Oval");
    toolkit.addTool(ovalTool);

    rectangleTool = new DrawingTool(canvas, new Rectangle(), "Rectangle");
    toolkit.addTool(rectangleTool);

    textTool = new TextTool(canvas);
    toolkit.addTool(textTool);

    selectTool = new SelectTool(canvas);
    toolkit.addTool(selectTool);

    canvas.setTool(toolkit.getTool(0));
  }

  private void initToolComponents() {
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

    initToolBar(toolListener);

    initToolMenu(toolListener);
  }

  private void initToolBar(ActionListener toolListener) {
    toolbar = new JPanel(new GridLayout(0, 1));

    int n = toolkit.getToolCount();
    for (int i = 0; i < n; i++) {
      Tool tool = toolkit.getTool(i);
      if (tool != null) {
        JButton button = new JButton(tool.getName());
        button.addActionListener(toolListener);
        toolbar.add(button);
      }
    }

    add(toolbar, BorderLayout.WEST);
  }

  private void initToolMenu(ActionListener toolListener) {
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

    menuBar.add(menu, 2);
  }

  public final void hideToolbar() {
    remove(toolbar);
    revalidate();
  }

  public final void showToolbar() {
    add(toolbar, "West");
    revalidate();
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

  public final void selectAllFromCanvas() {
    canvas.setTool(selectTool);
    canvas.selectAll();
  }

  public final void enableUndo() {
    undoMenuItem.setEnabled(true);
  }

  public final void disableUndo() {
    undoMenuItem.setEnabled(false);
  }

  public final void enableRedo() {
    redoMenuItem.setEnabled(true);
  }

  public final void disableRedo() {
    redoMenuItem.setEnabled(false);
  }

  public final void enableEditOptions() {
    deleteSelectedMenuItem.setEnabled(true);
    groupShapesMenuItem.setEnabled(true);
    splitShapesMenuItem.setEnabled(true);
  }

  public final void disableEditOptions() {
    deleteSelectedMenuItem.setEnabled(false);
    groupShapesMenuItem.setEnabled(false);
    splitShapesMenuItem.setEnabled(false);
  }

  public final void updateLayersMenu() {
    showLayersMenu.addItems();
    revalidate();
  }
}
