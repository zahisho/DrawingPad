package scribble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
  protected List shapes;

  protected Color curColor;

  protected EventListener listener;

  public boolean mouseButtonDown;
  public int x;
  public int y;
  
  public ScribbleCanvas() {
    shapes = new ArrayList();
    curColor = Color.black;
    mouseButtonDown = false;
    // calling factory method 
    listener = makeCanvasListener();
    addMouseListener((MouseListener) listener);
    addMouseMotionListener((MouseMotionListener) listener);
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
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      shapes = (List) in.readObject();
      in.close();
      repaint();
    } catch (IOException e1) {
      System.out.println("Unable to open file: " + filename);
    } catch (ClassNotFoundException e2) {
      System.out.println(e2);
    }
  }

  public void saveFile(String filename) {
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(shapes);
      out.close();
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.out.println("Unable to write file: " + filename);
    }
  }

  public void undo(){
    if (!shapes.isEmpty()) {
      int lastShape = shapes.size() - 1;
      shapes.remove(lastShape);
      repaint();
    }
  }
  // factory method 
  protected EventListener makeCanvasListener() {
    return (new ScribbleCanvasListener(this));
  }
}
