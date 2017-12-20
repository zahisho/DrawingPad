package main;

import shape.Shape;
import shape.ShapeList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import toolkit.Tool;

public class ScribbleCanvas extends JPanel {

  private ShapeList shapes;
  private ShapeList selectedShapes;

  private Color curColor;

  private Tool listener;

  public int x;
  public int y;

  public ScribbleCanvas() {
    shapes = new ShapeList();
    // calling factory method
    selectedShapes = new ShapeList();
    curColor = Color.black;
    listener = null;
  }

  public void setTool(Tool tool) {
    if (listener != null) {
      removeMouseListener(listener);
      removeMouseMotionListener(listener);
    }
    listener = tool;
    addMouseListener(listener);
    addMouseMotionListener(listener);
    requestFocusInWindow();
  }

  public final Color getCurColor() {
    return curColor;
  }

  public final void createShape(final Shape shape) {
    if (shape != null) {
      shapes.add(shape);
    }
  }

  public final void addSelectedShape(final Shape shape) {
    if (shape != null) {
      if (!selectedShapes.contains(shape)) {
        selectedShapes.add(shape);
      }
    }
  }

  public void removeSelectedShape(Shape shape) {
    if (shape != null) {
      if (selectedShapes.contains(shape)) {
        selectedShapes.remove(shape);
      }
    }
  }

  @Override
  public final void paint(final Graphics g) {
    Dimension dim = getSize();
    g.setColor(Color.white);
    g.fillRect(0, 0, dim.width, dim.height);
    g.setColor(Color.black);
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape != null) {
          if (selectedShapes.contains(shape)) {
            shape.setSelected(g);
          } else {
            shape.draw(g);
          }
        }
      }
    }
    revalidate();
  }

  public final void newFile() {
    shapes.clear();
    repaint();
  }

  public final void openFile(final String filename) {
    try {
      try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream(filename))) {
        shapes = (ShapeList) in.readObject();
      }
      repaint();
    } catch (IOException e1) {
      System.out.println("Unable to open file: " + filename);
    } catch (ClassNotFoundException e2) {
      System.out.println(e2);
    }
  }

  public final void saveFile(final String filename) {
    try {
      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
        out.writeObject(shapes);
      }
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.out.println("Unable to write file: " + filename);
    }
  }

  public ShapeList getShapes() {
    return shapes;
  }

  public ShapeList getSelectedShapes() {
    return selectedShapes;
  }

  public void resetSelected() {
    selectedShapes = new ShapeList();
  }

  public void changeBorderlineSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        shape.setContourColor(curColor);
        repaint();
      }
    }
  }

  public final void deleteSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        shapes.remove(shape);
      }
      repaint();
    }
  }

  public final void deleteAll() {
    if (!shapes.isEmpty()) {
      shapes.clear();
      repaint();
    }
  }

  public void generateCode(String filename) {
    ArrayList<File> files = new ArrayList<>();
    try {
      File f1 = new File(filename);
      f1.mkdir();

      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.getNameClass() != null) {
          File f2 = new File(f1.getPath());
          f2.mkdirs();
          files.add(f2);
        }
      }
      Iterator it = shapes.iterator();
      while (it.hasNext()) {
        Shape shape = (Shape) it.next();
        shape.generarCodigo(f1.getPath(), files);
      }
      JOptionPane.showMessageDialog(null, "SAVE:  " + f1.getPath());
    } catch (HeadlessException e) {
      JOptionPane.showMessageDialog(null, "ERROR " + e);
    }
  }
}
