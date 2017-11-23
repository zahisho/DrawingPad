package scribble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

  protected ScribbleCanvas canvas;
  protected JMenuBar menuBar;

  protected String currentFilename = null;
  protected ActionListener exitAction;
  protected JFileChooser chooser = new JFileChooser(".");

  protected static int width = 600;
  protected static int height = 400;
  
  private ColorDialog dialog;

  public Scribble(String title) {
    super(title);
    // calling factory method 
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    menuBar = createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exitListener();
      }
    });
  }

  protected JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem mi;

    // File menu 
    menu = new JMenu("File");
    menuBar.add(menu);

    mi = new JMenuItem("New");
    menu.add(mi);
    mi.addActionListener(event -> newFile());

    mi = new JMenuItem("Open");
    menu.add(mi);
    mi.addActionListener(event -> openFileListener());

    mi = new JMenuItem("Save");
    menu.add(mi);
    mi.addActionListener(event -> saveFile());

    mi = new JMenuItem("Save As");
    menu.add(mi);
    mi.addActionListener(event -> saveAsFileListener());

    menu.add(new JSeparator());

    mi = new JMenuItem("Exit");
    menu.add(mi);
    mi.addActionListener(event -> exitListener());

    // option menu
    menu = new JMenu("Option");
    menuBar.add(menu);

    mi = new JMenuItem("Color");
    menu.add(mi);
    mi.addActionListener(event -> colorListener());
    
    mi = new JMenuItem("Undo");
    menu.add(mi);
    mi.addActionListener(event -> undoListener());

    // horizontal space 
    menuBar.add(Box.createHorizontalGlue());

    // Help menu 
    menu = new JMenu("Help");
    menuBar.add(menu);

    mi = new JMenuItem("About");
    menu.add(mi);
    mi.addActionListener(event -> aboutListener());

    return menuBar;
  }

  // factory method 
  protected ScribbleCanvas makeCanvas() {
    return new ScribbleCanvas();
  }

  private void newFile() {
    currentFilename = null;
    canvas.newFile();
    setTitle("Scribble Pad");
  }
  
  private void openFileListener() {
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
  
  private void saveFile() {
    if (currentFilename == null) {
      currentFilename = "Untitled";
    }
    canvas.saveFile(currentFilename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }
  
  private void saveAsFileListener() {
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
  
  private void colorListener() {
    dialog = new ColorDialog(Scribble.this, "Choose color", canvas.getCurColor());
    Color result = dialog.showDialog();
    if (result != null) {
      canvas.setCurColor(result);
    }
    dialog.setVisible(true);
  }
  
  private void undoListener(){
    canvas.undo();
  }
  
  private void exitListener() {
    int result = JOptionPane.showConfirmDialog(null,
            "Do you want to exit Scribble Pad?",
            "Exit Scribble Pad?",
            JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      saveFile();
      System.exit(0);
    }
    else {
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
  }

  private void aboutListener() {
    JOptionPane.showMessageDialog(null,
            "DrawingPad version 1.0\nCopyright (c) Xiaoping Jia 2002", "About",
            JOptionPane.INFORMATION_MESSAGE);
  }
  
  protected void openFile(String filename) {
    currentFilename = filename;
    canvas.openFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  protected void saveFileAs(String filename) {
    currentFilename = filename;
    canvas.saveFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }
  
}
