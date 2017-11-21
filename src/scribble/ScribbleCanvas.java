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
import java.util.List;
import javax.swing.JPanel;

public class ScribbleCanvas extends JPanel {

  protected List<Shape> shapes = new ArrayList<>();

  protected Color curColor = Color.black;

  protected EventListener listener;

  public boolean mouseButtonDown = false;
  public int x, y;

  public ScribbleCanvas() {
    //calling factory method
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

  @Override
  public void paint(Graphics g) {
    Dimension dim = getSize();
    g.setColor(Color.white);
    g.fillRect(0, 0, dim.width, dim.height);
    g.setColor(Color.black);
    if (shapes != null) {
      for (Shape shape : shapes) {
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

  ///lista
  @SuppressWarnings("unchecked")
  public void openFile(String filename) {
    try {
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
        shapes = (List<Shape>) in.readObject();
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
      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
        out.writeObject(shapes);
      }
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.out.println("Unable to write file: " + filename);
    }
  }

  // factory method 
  protected EventListener makeCanvasListener() {
    return (new ScribbleCanvasListener(this));
//      return null;
  }

}
