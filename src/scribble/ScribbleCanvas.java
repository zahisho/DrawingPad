package scribble;

import scribble.drawing.Shape;
import scribble.drawing.ShapeList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import javax.swing.JPanel;
import scribble.tool.Tool;

public class ScribbleCanvas extends JPanel {

  private final ShapeList selectedShapes;
  private ShapeList shapes;

  private Color curColor;

  private Tool listener;

  private int x;
  private int y;

  private boolean keyCtrlPressed;

  public ScribbleCanvas() {
    shapes = new ShapeList();
    selectedShapes = new ShapeList();
    curColor = Color.black;
    listener = null;
    keyCtrlPressed = false;
  }

  public final void setTool(Tool tool) {
    if (listener != null) {
      removeMouseListener(listener);
      removeMouseMotionListener(listener);
    }
    listener = tool;
    addMouseListener(listener);
    addMouseMotionListener(listener);
    requestFocusInWindow();
  }

  public final void setKeyCtrlPressed(boolean f) {
    keyCtrlPressed = f;
  }

  public final boolean getKeyCtrlPressed() {
    return keyCtrlPressed;
  }

  public final void clearSelectedShapes() {
    selectedShapes.clear();
  }

  public final void addSelectedShape(Shape s) {
    if (!selectedShapes.contains(s)) {
      selectedShapes.add(s);
    } else {
      selectedShapes.remove(s);
    }
  }

  public final ShapeList getSelectedShapes() {
    return selectedShapes;
  }

  public final void setCurColor(Color curColor) {
    this.curColor = curColor;
  }

  public final Color getCurColor() {
    return curColor;
  }

  public final void addShape(Shape shape) {
    if (shape != null) {
      shapes.add(shape);
    }
  }

  public final ShapeList getShapes() {
    return shapes;
  }

  @Override
  public final void paint(final Graphics g) {
    Dimension dim = getSize();
    g.setColor(Color.white);
    g.fillRect(0, 0, dim.width, dim.height);
    g.setColor(Color.black);
    if (shapes != null) {
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

  public final void openFile(String filename) {
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

  public final void saveFile(String filename) {
    try {
      try (ObjectOutputStream out = new ObjectOutputStream(
              new FileOutputStream(filename))) {
        out.writeObject(shapes);
      }
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.err.println(e);
      System.out.println("Unable to write file: " + filename);
    }
  }

  public final void undo() {
    shapes.removeLast();
    repaint();
  }

  public final void deleteAll() {
    selectedShapes.clear();
    shapes.clear();
    repaint();
  }

  public final void deleteSelected() {
    Iterator it = selectedShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      shapes.remove(s);
    }
    selectedShapes.clear();
    repaint();
  }

  public final void fillSelectedShapes() {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      ((Shape) selectedShape.next()).setFilled(true);
    }
    repaint();
  }

  public final void unfillSelectedShapes() {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      ((Shape) selectedShape.next()).setFilled(false);
    }
    repaint();
  }

  public final void moveShapes(Point p) {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      ((Shape) selectedShape.next()).move(p);
    }
    repaint();
  }

  public final void changeContour(Color c) {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      ((Shape) selectedShape.next()).setContourColor(c);
    }
    repaint();
  }

  public final void changeFilling(Color c) {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      ((Shape) selectedShape.next()).setFillingColor(c);
    }
    repaint();
  }
}
