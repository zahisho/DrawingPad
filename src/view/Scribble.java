package view;

import java.awt.BorderLayout;
import java.awt.Color;
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

  protected JMenuBar menuBar;
  protected ScribbleCanvas canvas;
  
  protected JFileChooser chooser;
  protected String currentFilename;
  protected ActionListener exitAction;

  protected static int width = 600;
  protected static int height = 400;
  
  private ColorDialog dialog;

  public Scribble(String title) {
    super(title);
    // calling factory method 
    currentFilename = null;
    chooser = new JFileChooser(".");
    
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
  
  // factory method 
  protected ScribbleCanvas makeCanvas() {
    return new ScribbleCanvas();
  }
  
  protected JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    addFileMenu(menuBar);
    addEditMenu(menuBar);
    addOptionMenu(menuBar);
    menuBar.add(Box.createHorizontalGlue());
    addHelpMenu(menuBar);
    return menuBar;
  }
  
  private void addFileMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("File");
    JMenuItem menuFileItem;
    menuBar.add(menu);
    
    menuFileItem = new JMenuItem("New");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> newFile());

    menuFileItem = new JMenuItem("Open");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> openFileListener());

    menuFileItem = new JMenuItem("Save");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> saveFile());

    menuFileItem = new JMenuItem("Save As");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> saveAsFileListener());

    menu.add(new JSeparator());

    menuFileItem = new JMenuItem("Exit");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> exitListener());
  }
  
  private void addEditMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Edit");
    JMenuItem menuEditItem;
    menuBar.add(menu);
    
    menuEditItem = new JMenuItem("Undo");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.undo());
    
    menuEditItem = new JMenuItem("Redo");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.redo());
    
    menuEditItem = new JMenuItem("fill Color");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.fillShape());
    
    menuEditItem = new JMenuItem("Contour Color");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.changeContourColor());
    
    menuEditItem = new JMenuItem("Group Shapes");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.groupShapes());
    
    menuEditItem = new JMenuItem("Ungroup Shapes");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.ungroupShapes());
    
    menuEditItem = new JMenuItem("Select all");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.selectAll());
    
    menuEditItem = new JMenuItem("Clear all");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> canvas.clearAll());
  }
  
  private void addOptionMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Option");
    JMenuItem menuOptionItem;
    menuBar.add(menu);

    menuOptionItem = new JMenuItem("Color");
    menu.add(menuOptionItem);
    menuOptionItem.addActionListener(event -> colorListener());

  }
  
  private void addHelpMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Help");
    JMenuItem menuHelpItem;
    menuBar.add(menu);

    menuHelpItem = new JMenuItem("About");
    menu.add(menuHelpItem);
    menuHelpItem.addActionListener(event -> aboutListener());
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
    System.out.println(result);
    if (result != null) {
      canvas.setCurColor(result);
    }
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
