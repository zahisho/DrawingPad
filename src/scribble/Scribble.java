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
  private ActionListener exitAction;
  private final JFileChooser chooser = new JFileChooser(".");

  protected static int width = 600;
  protected static int height = 400;

  public Scribble(final String title) {
    super(title);
    // calling factory method
    canvas = makeCanvas();
    getContentPane().setLayout(new BorderLayout());
    menuBar = createMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(final WindowEvent e) {
        if (exitAction != null) {
          exitAction.actionPerformed(new ActionEvent(Scribble.this, 0, null));
        }
      }
    });
  }

  protected final JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem mi;

    // File menu
    menu = new JMenu("File");
    menuBar.add(menu);

    mi = new JMenuItem("New");
    menu.add(mi);
    mi.addActionListener((final ActionEvent e) -> {
      newFile();
    });

    mi = new JMenuItem("Open");
    menu.add(mi);
    mi.addActionListener((final ActionEvent e) -> {
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
    });

    mi = new JMenuItem("Save");
    menu.add(mi);
    mi.addActionListener((final ActionEvent e) -> {
      saveFile();
    });

    mi = new JMenuItem("Save As");
    menu.add(mi);
    mi.addActionListener((final ActionEvent e) -> {
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
    });

    menu.add(new JSeparator());

    exitAction = (final ActionEvent e) -> {
      int result = JOptionPane.showConfirmDialog(null,
              "Do you want to exit Scribble Pad?",
              "Exit Scribble Pad?",
              JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        saveFile();
        System.exit(0);
      }
    };
    mi = new JMenuItem("Exit");
    menu.add(mi);
    mi.addActionListener(exitAction);

    menu = new JMenu("Edit");
    menuBar.add(menu);

    mi = new JMenuItem("Undo");
    menu.add(mi);
    mi.addActionListener((final ActionEvent e) -> {
      canvas.deleteLastShape();
      this.repaint();
    });

    mi = new JMenuItem("Fill Shape");
    menu.add(mi);
    mi.addActionListener((ActionEvent e) -> {
      canvas.fillSelectedShapes();
    });

    mi = new JMenuItem("Change Borderline Color");
    menu.add(mi);
    mi.addActionListener((ActionEvent e) -> {
      canvas.changeBorderlineSelectedShapes();
    });

    mi = new JMenuItem("Group");
    menu.add(mi);
    mi.addActionListener((ActionEvent e) -> {
      canvas.groupSelectedShapes();
    });

    mi = new JMenuItem("Ungroup");
    menu.add(mi);
    mi.addActionListener((ActionEvent e) -> {
      canvas.ungroupSelectedShapes();
    });

    mi = new JMenuItem("Delete");
    menu.add(mi);
    mi.addActionListener((ActionEvent e) -> {
      canvas.deleteSelectedShapes();
    });

    mi = new JMenuItem("DeleteALL");
    menu.add(mi);
    mi.addActionListener((ActionEvent e) -> {
      canvas.deleteAll();
    });

    // option menu
    menu = new JMenu("Option");
    menuBar.add(menu);

    mi = new JMenuItem("Color");
    menu.add(mi);
    mi.addActionListener(new ActionListener() {
      protected ColorDialog dialog
              = new ColorDialog(Scribble.this,
                      "Choose color", canvas.getCurColor());

      @Override
      public void actionPerformed(final ActionEvent e) {
        Color result = dialog.showDialog();
        if (result != null) {
          canvas.setCurColor(result);
        }
      }
    });

    // horizontal space
    menuBar.add(Box.createHorizontalGlue());

    // Help menu
    menu = new JMenu("Help");
    menuBar.add(menu);

    mi = new JMenuItem("About");
    menu.add(mi);
    mi.addActionListener((final ActionEvent e) -> {
      JOptionPane.showMessageDialog(null,
              "DrawingPad version 1.0\nCopyright (c) "
              + "Xiaoping Jia 2002", "About",
              JOptionPane.INFORMATION_MESSAGE);
    });

    return menuBar;
  }

  // factory method
  protected ScribbleCanvas makeCanvas() {
    return new ScribbleCanvas();
  }

  protected final void newFile() {
    currentFilename = null;
    canvas.newFile();
    setTitle("Scribble Pad");
  }

  protected final void openFile(final String filename) {
    currentFilename = filename;
    canvas.openFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  protected final void saveFile() {
    if (currentFilename == null) {
      currentFilename = "Untitled";
    }
    canvas.saveFile(currentFilename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  protected final void saveFileAs(final String filename) {
    currentFilename = filename;
    canvas.saveFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }
}
