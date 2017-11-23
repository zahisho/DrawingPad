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
import java.util.EventListener;
import java.util.Iterator;
import javax.swing.JPanel;

public class Canvas extends JPanel {

  private ShapeList shapes;
  private Color curColor = Color.black;

  private EventListener listener;

  public boolean mouseButtonDown = false;
  public int x, y;
  private CanvasListener scribbleCanvasListener;

  public Canvas() {
    this.shapes = new ShapeList();
    //calling factory method
    listener = makeCanvasListener();
    addMouseListener((MouseListener) listener);
    addMouseMotionListener((MouseMotionListener) listener);
  }

  public void setTool(Tool tool) {
    scribbleCanvasListener.setTool(tool);
  }

  public Tool getTool() {
    return scribbleCanvasListener.getTool();
  }

  // factory method 
  protected EventListener makeCanvasListener() {
    return (scribbleCanvasListener = new CanvasListener(this));
  }

  public void setCurColor(Color curColor) {
    this.curColor = curColor;
  }

  public Color getCurColor() {
    return curColor;
  }

  public void addShape(Shape shape) {
    if (shape != null) {
      shapes.add(shape);
    }
  }

  @Override
  public void paint(Graphics g) {
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

  public void newFile() {
    shapes.clear();
    repaint();
  }

  public void openFile(String filename) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
      shapes = (ShapeList) in.readObject();
    } catch (IOException e1) {
      System.out.println("Unable to open file: " + filename);
    } catch (ClassNotFoundException e2) {
      System.out.println(e2);
    }
    repaint();
    repaint();
  }

  public void saveFile(String filename) {
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
}
