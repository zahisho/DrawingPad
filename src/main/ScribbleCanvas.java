package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import javax.swing.JPanel;
import shape.Fillable;
import shape.GroupShape;
import shape.Shape;
import shape.ShapeList;
import toolkit.Tool;

public class ScribbleCanvas extends JPanel {

  private ShapeList shapes;
  private ShapeList selectedShapes;

  private Color curColor;
  private Color fillColor;

  private Tool listener;

  public int x;
  public int y;

  public ScribbleCanvas() {
    shapes = new ShapeList();
    // calling factory method
    selectedShapes = new ShapeList();
    curColor = Color.black;
    listener = null;
    fillColor = new Color(255, 255, 255, 0);
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

  public final void setCurColor(final Color curColor) {
    this.curColor = curColor;
  }

  public final Color getCurColor() {
    return curColor;
  }

  public final void addShape(final Shape shape) {
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

  @SuppressWarnings("unchecked")
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

  public final void changeFillSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape instanceof Fillable) {
          ((Fillable) shape).fill(fillColor);
          repaint();
        }
      }
    }
  }

  public final void changeBorderlineSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        shape.setColor(curColor);
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

  public final void groupSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      GroupShape group = new GroupShape(selectedShapes);
      addShape(group);
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        shapes.remove(shape);
      }
      selectedShapes = new ShapeList();
      selectedShapes.add(group);
      repaint();
    }
  }

  public final void ungroupSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      ShapeList ungrouped = new ShapeList();
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape != null && shape instanceof GroupShape) {
          Iterator iterator = ((GroupShape) shape).getShapes().iterator();
          while (iterator.hasNext()) {
            Shape shapeCast = (Shape) iterator.next();
            if (shapeCast != null) {
              ungrouped.add(shapeCast);
            }
          }
          shapes.remove(shape);
        }
      }
      Iterator it = ungrouped.iterator();
      while (it.hasNext()) {
        Shape shape = (Shape) it.next();
        if (shape != null) {
          shapes.add(shape);
        }
      }
      repaint();
    }
  }

  public void setFillColor(Color color) {
    fillColor = color;
  }

  public Color getFillColor() {
    return fillColor;
  }
}
