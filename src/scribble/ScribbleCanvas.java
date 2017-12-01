package scribble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

public class ScribbleCanvas extends JPanel {

  // The list of shapes of the drawing
  // The elements are instances of Stroke
  private List shapes = new ArrayList();
  private List<Shape> selectedShapes;

  private Color curColor = Color.black;

  private final EventListener listener;

  public boolean mouseButtonDown = false;
  public int x;
  public int y;

  public ScribbleCanvas() {
    // calling factory method
    listener = makeCanvasListener();
    addMouseListener((MouseListener) listener);
    addMouseMotionListener((MouseMotionListener) listener);
    selectedShapes = new ArrayList();
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
      selectedShapes.add(shape);
    }
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
          shape.draw(g);
        }
      }
    }
  }

  public final void newFile() {
    shapes.clear();
    repaint();
  }

  public final void openFile(final String filename) {
    try {
      ObjectInputStream in
              = new ObjectInputStream(new FileInputStream(filename));
      shapes = (List) in.readObject();
      in.close();
      repaint();
    } catch (IOException e1) {
      System.out.println("Unable to open file: " + filename);
    } catch (ClassNotFoundException e2) {
      System.out.println(e2);
    }
  }

  public final void saveFile(final String filename) {
    try {
      ObjectOutputStream out
              = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(shapes);
      out.close();
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.out.println("Unable to write file: " + filename);
    }
  }

  // factory method
  protected EventListener makeCanvasListener() {
    return (new ScribbleCanvasListener(this));
  }

  public final void deleteLastShape() {
    if (!shapes.isEmpty()) {
      shapes.remove(shapes.size() - 1);
    }
  }

  public List<Shape> getShapes() {
    return shapes;
  }

  public List<Shape> getSelectedShapes() {
    return selectedShapes;
  }

  public void resetSelected() {
    selectedShapes = new ArrayList();
  }

  public final void fillSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      for (Shape s : selectedShapes) {
        if (s instanceof Fillable) {
          ((Fillable) s).fill(curColor);
          repaint();
        }
      }
    }
  }

  public final void changeBorderlineSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      for (Shape s : selectedShapes) {
        s.setColor(curColor);
        repaint();
      }
    }
  }

  public final void deleteSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      for (Shape s : selectedShapes) {
        shapes.remove(s);
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
      Group group = new Group(selectedShapes);
      addShape(group);
      for (Shape s : selectedShapes) {
        shapes.remove(s);
      }
      repaint();
    }
  }

  public final void ungroupSelectedShapes() {
    if (!selectedShapes.isEmpty()) {
      Group group;
      List<Shape> ungroupedShapes = new ArrayList();
      selectedShapes.stream().filter((s)
              -> (s instanceof Group)).map((s) -> {
        ungroupedShapes.addAll(((Group) s).getShapes());
        return s;
      }).forEachOrdered((s) -> {
        shapes.remove(s);
      });
      ungroupedShapes.forEach((us) -> {
        addShape(us);
      });
      repaint();
    }
  }
}
