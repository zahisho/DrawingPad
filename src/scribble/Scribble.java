package scribble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Scribble extends JFrame {

  public Canvas canvas;
  public JMenuBar menuBar;

  private String currentFilename = null;
  private JFileChooser chooser = new JFileChooser(".");

  private ColorDialog dialog;

  public static final int WIDTH = 600;
  public static final int HEIGHT = 400;

  public Scribble(String title) {
    super(title);
    // calling factory method 
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    menuBar = createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    eventCloseWindow();
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

    // option menu
    menu = new JMenu("Option");
    menuBars.add(menu);

    menuItem = new JMenuItem("Color");
    menu.add(menuItem);
    menuItem.addActionListener(event -> chooseColor());

    menuItem = new JMenuItem("Undo");
    menu.add(menuItem);
    menuItem.addActionListener(event -> undo());

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
    if (canvas.shapes.size() > 0) {
      canvas.shapes.remove(canvas.shapes.size() - 1);
      canvas.repaint();
    } else {
      canvas.repaint();
    }

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
    dialog = new ColorDialog(Scribble.this, "Choose color", canvas.getCurColor());
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

// factory method 
  public Canvas makeCanvas() {
    return new Canvas();
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
}
