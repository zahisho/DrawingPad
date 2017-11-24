package pad;

import view.listeners.AboutListener;
import view.listeners.ColorListener;
import view.listeners.ExitListener;
import view.listeners.NewFileListener;
import view.listeners.OpenFileListener;
import view.listeners.SaveAsFileListener;
import view.listeners.SaveFileListener;
import view.listeners.UndoListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Scribble extends JFrame {

  public ScribbleCanvas canvas;
  protected JMenuBar menuBar;

  protected String currentFileName;
  protected ActionListener exitAction;
  public JFileChooser chooser;

  private NewFileListener newFileListener;

  public Scribble(String title) {
    super(title);
    this.currentFileName = null;
    this.chooser = new JFileChooser(".");
    init();
    
  }
  
  private void init (){
    
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    menuBar = createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (exitAction != null) {
          exitAction.actionPerformed(new ActionEvent(Scribble.this, 0, null));
        }
      }
    });
  
  
  }

  private JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem mi;

    menu = new JMenu("File");
    menuBar.add(menu);

    mi = new JMenuItem("New");
    menu.add(mi);
    mi.addActionListener(new NewFileListener(this));

    mi = new JMenuItem("Open");
    menu.add(mi);
    mi.addActionListener(new OpenFileListener(this));

    mi = new JMenuItem("Save");
    menu.add(mi);
    mi.addActionListener(new SaveFileListener(this));

    mi = new JMenuItem("Save As");
    menu.add(mi);
    mi.addActionListener(new SaveAsFileListener(this));

    menu.add(new JSeparator());

    exitAction = new ExitListener(this);
    mi = new JMenuItem("Exit");
    menu.add(mi);
    mi.addActionListener(exitAction);

    // option menu
    menu = new JMenu("Option");
    menuBar.add(menu);

    mi = new JMenuItem("Color");
    menu.add(mi);
    mi.addActionListener(new ColorListener(this));

    mi = new JMenuItem("Undo");
    menu.add(mi);
    mi.addActionListener(new UndoListener(this));

    // horizontal space 
    menuBar.add(Box.createHorizontalGlue());

    // Help menu 
    menu = new JMenu("Help");
    menuBar.add(menu);

    mi = new JMenuItem("About");
    menu.add(mi);
    mi.addActionListener(new AboutListener(this));

    return menuBar;
  }

  // factory method 
  protected ScribbleCanvas makeCanvas() {
    return new ScribbleCanvas();
  }

  public void newFile() {
    currentFileName = null;
    canvas.newFile();
    setTitle("Scribble Pad");
  }

  public void openFile(String filename) {
    currentFileName = filename;
    canvas.openFile(filename);
    setTitle("Scribble Pad [" + currentFileName + "]");
  }

  public void saveFile() {
    if (currentFileName == null) {
      currentFileName = "Untitled";
    }
    canvas.saveFile(currentFileName);
    setTitle("Scribble Pad [" + currentFileName + "]");
  }

  public void saveFileAs(String filename) {
    currentFileName = filename;
    canvas.saveFile(filename);
    setTitle("Scribble Pad [" + currentFileName + "]");
  }

  public void makeUndo() {
    canvas.undo();
  }

  /**
   *
   * @param name
   */
  public void setCurrentFile(String name) {
    currentFileName = name;

  }

  public String getCurrentFile() {

    return currentFileName;

  }

  public void setCanvas(ScribbleCanvas canv) {
    canvas = canv;

  }

  public void setCanvasCurColor(ScribbleCanvas canv, Color color) {

    canvas = canv;
    canv.setCurColor(color);

  }

  public ScribbleCanvas getCanvas() {

    return canvas;

  }

}
