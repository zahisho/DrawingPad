package scribble;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Scribble extends JFrame {

  protected ScribbleCanvas canvas;
  protected JMenuBar menuBar;

  protected String currentFilename;
  protected JFileChooser chooser;

  protected static int WIDTH = 600;
  protected static int HEIGHT = 400;

  private ExitListener exitListener;
  private SaveListener saveListener;
  private NewFileListener newFileListener;
  private OpenFileListener openFileListener;
  private SaveFileAsListener saveFileAsListener;
  private ColorListener colorListener;
  private AboutListener aboutListener;
  private final ScribbleWindowAdapter windowAdapter;

  public Scribble(String title) {
    super(title);
    // calling factory method
    chooser = new JFileChooser(".");
    currentFilename = null;
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    windowAdapter = new ScribbleWindowAdapter(this);
    addWindowListener(windowAdapter);
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

  // factory method
  protected ScribbleCanvas makeCanvas() {
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
}
