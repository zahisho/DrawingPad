package view;

import scribble.SelectorTool;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import scribble.Canvas;
import scribble.ColorDialog;
import scribble.ScribbleTool;
import scribble.Tool;
import scribble.ToolKit;
import scribble.TwoEndsTool;

public class DrawingPad extends JFrame {

  private ToolKit toolkit;

  public Canvas canvas;
  public JMenuBar menuBar;

  private String currentFilename = null;
  private JFileChooser chooser = new JFileChooser(".");

  private ColorDialog dialog;

  private static final int WIDTH = 600;
  private static final int HEIGHT = 400;

  public DrawingPad(String title) {
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    menuBar = createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    eventCloseWindow();
    init();
    initTools();

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
    menuBar.add(menu, 2);
    revalidate();
  }

  private void init() {
    setSize(WIDTH, HEIGHT);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screenSize.width / 2 - WIDTH / 2,
      screenSize.height / 2 - HEIGHT / 2);
    setVisible(true);
  }

  public Tool getSelectedTool() {
    return toolkit.getSelectedTool();
  }

  private void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new ScribbleTool(canvas, "Scribble"));
    toolkit.addTool(new TwoEndsTool(canvas, "Line", TwoEndsTool.LINE));
    toolkit.addTool(new TwoEndsTool(canvas, "Oval", TwoEndsTool.OVAL));
    toolkit.addTool(new TwoEndsTool(canvas, "Rectangle", TwoEndsTool.RECT));
    toolkit.addTool(new SelectorTool(canvas, "Selector"));
    canvas.setTool(toolkit.getTool(0));
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

  public Canvas makeCanvas() {
    canvas = new Canvas();
    return canvas;
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

  private void eventCloseWindow() {
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent we) {
        exit();
      }
    });
  }

  private JMenuBar createMenuBar() {
    JMenuBar menuBars = new JMenuBar();
    JMenu menu;
    JMenuItem menuItem;

    // File menu 
    menu = new JMenu("File");
    menuBars.add(menu);

    menuItem = new JMenuItem("New");
    menu.add(menuItem);
    menuItem.addActionListener(event -> newFile());

    menuItem = new JMenuItem("Open");
    menu.add(menuItem);
    menuItem.addActionListener(event -> openFile());

    menuItem = new JMenuItem("Save");
    menu.add(menuItem);
    menuItem.addActionListener(event -> saveFile());

    menuItem = new JMenuItem("Save As");
    menu.add(menuItem);
    menuItem.addActionListener(event -> saveAs());

    menu.add(new JSeparator());

    menuItem = new JMenuItem("Exit");
    menu.add(menuItem);
    menuItem.addActionListener(event -> exit());

    // edit menu
    menu = new JMenu("Edit");
    menuBars.add(menu);

    menuItem = new JMenuItem("Undo");
    menu.add(menuItem);
    menuItem.addActionListener(event -> undo());

    // option menu
    menu = new JMenu("Option");
    menuBars.add(menu);

    menuItem = new JMenuItem("Color");
    menu.add(menuItem);
    menuItem.addActionListener(event -> chooseColor());

    // horizontal space 
    menuBars.add(Box.createHorizontalGlue());

    // Help menu 
    menu = new JMenu("Help");
    menuBars.add(menu);

    menuItem = new JMenuItem("About");
    menu.add(menuItem);
    menuItem.addActionListener(event -> showAbout());

    return menuBars;
  }

  private void undo() {
    canvas.getShapes().removeLast();
    repaint();
  }

  private void saveAs() {
    int retval = chooser.showDialog(null, "Save As");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (!theFile.isDirectory()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          saveFileAs(filename);
        }
      }
    }
  }

  private void showAbout() {
    JOptionPane.showMessageDialog(null,
      "DrawingPad version 1.0\nCopyright (c) Xiaoping Jia 2002", "About",
      JOptionPane.INFORMATION_MESSAGE);
  }

  private void chooseColor() {
    dialog = new ColorDialog(DrawingPad.this, "Choose color", canvas.getCurColor());
    Color result = dialog.showDialog();
    if (result != null) {
      canvas.setCurColor(result);
    }
  }

  private void openFile() {
    int retval = chooser.showDialog(null, "Open");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (theFile.isFile()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          openFile(filename);
        }
      }
    }
  }

  private void newFile() {
    currentFilename = null;
    canvas.newFile();
    setTitle("Scribble Pad");
  }

  private void openFile(String filename) {
    currentFilename = filename;
    canvas.openFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  private void saveFile() {
    if (currentFilename == null) {
      currentFilename = "Untitled";
    }
    canvas.saveFile(currentFilename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  private void saveFileAs(String filename) {
    currentFilename = filename;
    canvas.saveFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");

  }

  private void exit() {
    int result = JOptionPane.showConfirmDialog(null,
      "Do you want to exit Scribble Pad?",
      "Exit Scribble Pad?",
      JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      saveFile();
      System.exit(0);
    } else {
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
  }

  private void selector() {
    System.out.println("holaX");
  }
}
