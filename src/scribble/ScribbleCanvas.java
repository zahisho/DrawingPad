package scribble;

import drawing.Shape;
import drawing.ShapeList;
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
import java.util.Iterator;
import javax.swing.JPanel;
import tool.Tool;

public class ScribbleCanvas extends JPanel {

  // The list of shapes of the drawing
  // The elements are instances of Stroke
  private ShapeList shapes;
  private Shape selectedShape;

  private Color curColor;

  private final ScribbleCanvasListener listener;

  private boolean mouseButtonDown;
  private int x;
  private int y;

  public ScribbleCanvas() {
    shapes = new ShapeList();
    curColor = Color.black;
    mouseButtonDown = false;
    listener = new ScribbleCanvasListener(this);
    addMouseListener((MouseListener) listener);
    addMouseMotionListener((MouseMotionListener) listener);
  }

  public final void setSelectedShape(Shape s) {
    selectedShape = s;
  }

  public final boolean getMouseButtonDown() {
    return mouseButtonDown;
  }

  public final void setMouseButtonDown(boolean bool) {
    mouseButtonDown = bool;
  }

  public final void setX(int x) {
     this.x = x;
  }

  public final void setY(int y) {
    this.y = y;
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
          shape.draw(g);
        }
      }
    }
    if (selectedShape != null) {
      selectedShape.setSelected(g);
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

  public void saveFile(String filename) {
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

  private ScribbleCanvasListener makeCanvasListener() {
    return (new ScribbleCanvasListener(this));
  }

  public final void undo() {
    shapes.removeLast();
    repaint();
  }

  public final void setTool(Tool tool) {
    listener.setTool(tool);
  }

  public final Tool getTool() {
    return listener.getTool();
  }
}
