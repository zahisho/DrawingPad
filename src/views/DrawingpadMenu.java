package views;

import views.listeners.ExitListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import main.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class DrawingpadMenu extends JMenuBar {

  private String currentFilename;
  private ScribbleCanvas panelCanvas;
  private JMenu menu;
  private JMenuItem menuItem;
  private final JFileChooser chooser;
  private DrawingPadFrame frame;
  private ToolPanel tool;
  private ExitListener exitListener;
  private ColorDialog panelColor;
  //private ShowColorListener showColorListener;

  public DrawingpadMenu(ScribbleCanvas panelCanvas, DrawingPadFrame frame,
    ToolPanel tool, ColorDialog panelColor) {
    this.panelCanvas = panelCanvas;
    this.frame = frame;
    this.tool = tool;
    this.panelColor = panelColor;
    chooser = new JFileChooser(".");
    createMenuBar();
  }

  private void createMenuBar() {

    // File menu 
    createFileMenu();

    // edit menu
    createEditMenu();

    //view menu
    createViewMenu();
    //tools
    createToolsMenu();

    // option menu
    createOptionMenu();

    // horizontal space 
    add(Box.createHorizontalGlue());

    // Help menu 
    createHelpMenu();
  }

  private void createFileMenu() {
    menu = new JMenu("File");
    add(menu);

    menuItem = new JMenuItem("New");
    menu.add(menuItem);
    menuItem.addActionListener(e -> newFile());

    menuItem = new JMenuItem("Open");
    menu.add(menuItem);
    menuItem.addActionListener(e -> openFile());

    menuItem = new JMenuItem("Save");
    menu.add(menuItem);
    menuItem.addActionListener(e -> saveFile());

    menuItem = new JMenuItem("Save As");
    menu.add(menuItem);
    menuItem.addActionListener(e -> saveAs());

    menu.add(new JSeparator());

    menuItem = new JMenuItem("Exit");
    exitListener = new ExitListener(frame, this);
    menuItem.addActionListener(exitListener);
    menu.add(menuItem);
  }

  private void createEditMenu() {
    menu = new JMenu("Edit");
    add(menu);

    menuItem = new JMenuItem("Undo");
    menu.add(menuItem);

    menuItem = new JMenuItem("Redo");
    menu.add(menuItem);

    menu.add(new JSeparator());

    menuItem = new JMenuItem("Group selected");
    menu.add(menuItem);
    menuItem.addActionListener(e -> panelCanvas.groupSelectedShapes());

    menuItem = new JMenuItem("Ungroup selected");
    menu.add(menuItem);
    menuItem.addActionListener(e -> panelCanvas.ungroupSelectedShapes());

    menu.add(new JSeparator());

    menuItem = new JMenuItem("Delete selected");
    menu.add(menuItem);
    menuItem.addActionListener(e -> panelCanvas.deleteSelectedShapes());

    menuItem = new JMenuItem("Delete all");
    menu.add(menuItem);
    menuItem.addActionListener(e -> panelCanvas.deleteAll());
  }

  private void createViewMenu() {
    menu = new JMenu("View");
    add(menu);

    menuItem = new JMenuItem("Tool Panel");
    menu.add(menuItem);
    menuItem.addActionListener(e -> showTools());

    menuItem = new JMenuItem("Color Panel");
    menu.add(menuItem);
    menuItem.addActionListener(e -> showColorPanel());
  }

  private void newFile() {
    currentFilename = null;
    panelCanvas.newFile();
    frame.setTitle("Scribble Pad");
  }

  private void createToolsMenu() {
    menu = tool.createToolMenu();
    add(menu);
  }

  private void createOptionMenu() {
    menu = new JMenu("Option");
    add(menu);
  }

  private void createHelpMenu() {
    menu = new JMenu("Help");
    add(menu);

    menuItem = new JMenuItem("About");
    menu.add(menuItem);
    menuItem.addActionListener(e -> about());
  }

  private void openFile() {
    int retval = chooser.showDialog(null, "Open");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (theFile.isFile()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          currentFilename = filename;
          panelCanvas.openFile(filename);
          frame.setTitle("Scribble Pad [" + currentFilename + "]");
        }
      }
    }
  }

  public void saveFile() {
    if (currentFilename == null) {
      currentFilename = "Untitled";
    }
    panelCanvas.saveFile(currentFilename);
    frame.setTitle("Scribble Pad [" + currentFilename + "]");
  }

  private void saveAs() {
    int retval = chooser.showDialog(null, "Save As");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (!theFile.isDirectory()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          currentFilename = filename;
          panelCanvas.saveFile(filename);
          frame.setTitle("Scribble Pad [" + currentFilename + "]");
        }
      }
    }
  }

  private void showTools() {
    if (tool.getVisiblePanel()) {
      tool.setVisiblePanel(false);
    } else {
      tool.setVisiblePanel(true);
    }
  }

  public void showColorPanel() {
    if (panelColor.getVisiblePanel()) {
      panelColor.setVisiblePanel(false);
    } else {
      panelColor.setVisiblePanel(true);
    }
  }

  private void exit() {
    int result = JOptionPane.showConfirmDialog(null,
      "Do you want to exit Scribble Pad?",
      "Exit Scribble Pad?",
      JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      saveFile();
      System.exit(0);
    }
  }

  private void about() {
    JOptionPane.showMessageDialog(null,
      "DrawingPad version 1.0\nCopyright (c) "
      + "Xiaoping Jia 2002", "About",
      JOptionPane.INFORMATION_MESSAGE);
  }

  public final ExitListener getExitListener() {
    return exitListener;
  }
}
