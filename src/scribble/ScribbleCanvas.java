package scribble;

import java.awt.BasicStroke;
import scribble.drawing.Shape;
import scribble.drawing.ShapeList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import scribble.drawing.Figure;
import scribble.tool.Tool;

public class ScribbleCanvas extends JPanel {

  private final float[] DASHED_STROKE = new float[]{5, 2};
  private int dashed_phase;

  private final Thread thread;

  private final Stack<Action> undoStack;
  private final Stack<Action> redoStack;

  private ShapeList selectedShapes;
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
    undoStack = new Stack<>();
    redoStack = new Stack<>();
    dashed_phase = 0;
    thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          if (!selectedShapes.empty()) {
            dashed_phase += 2;
            repaint();
          }
          try {
            Thread.sleep(200);
          } catch (InterruptedException ex) {
            Logger.getLogger(ScribbleCanvas.class.getName()).log(Level.SEVERE, null, ex);
          }          
        }
      }
    });
    thread.start();
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

  public final ShapeList getShapes() {
    return shapes;
  }

  public final void addShape(Shape shape) {
    ShapeList newShapes = new ShapeList();
    newShapes.add(shape);
    redoStack.clear();
    undoStack.push(new Action(null, newShapes));
    addShapes(newShapes);
    repaint();
  }

  public final void addShapes(ShapeList ss) {
    if (ss != null) {
      shapes.addAll(ss);
    }
  }

  public final void deleteSelected() {
    if (!selectedShapes.empty()) {
      redoStack.clear();
      ShapeList pShapes = selectedShapes;
      undoStack.push(new Action(pShapes, null));
      removeShapes(pShapes);
      selectedShapes = new ShapeList();
      repaint();
    }
  }

  public final void removeShapes(ShapeList ss) {
    if (ss != null) {
      shapes.removeAll(ss);
    }
  }

  public final void startMovement() {
    ShapeList pShapes = selectedShapes;
    ShapeList nShapes = pShapes.copy();
    redoStack.clear();
    undoStack.push(new Action(pShapes, nShapes));
    removeShapes(pShapes);
    addShapes(nShapes);
    selectedShapes = new ShapeList();
    selectedShapes.addAll(nShapes);
  }

  public final void moveShapes(Point p) {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      Shape s = (Shape) selectedShape.next();
      s.move(p);
    }
    repaint();
  }

  public final void changeContour(Color c) {
    ShapeList nShapes = selectedShapes.copy();
    Iterator it = nShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.setContourColor(c);
    }
    ShapeList pShapes = selectedShapes;
    removeShapes(pShapes);
    addShapes(nShapes);
    redoStack.clear();
    undoStack.push(new Action(pShapes, nShapes));
    selectedShapes = new ShapeList();
    selectedShapes.addAll(nShapes);
    repaint();
  }

  public final void changeFilling(Color c) {
    ShapeList nShapes = selectedShapes.copy();
    Iterator it = nShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.setFillingColor(c);
    }
    ShapeList pShapes = selectedShapes;
    removeShapes(pShapes);
    addShapes(nShapes);
    redoStack.clear();
    undoStack.push(new Action(pShapes, nShapes));
    selectedShapes = new ShapeList();
    selectedShapes.addAll(nShapes);
    repaint();
  }

  public final void groupShapes() {
    if (selectedShapes.size() > 1) {
      Shape nShape = new Shape();
      Iterator it = selectedShapes.iterator();
      while (it.hasNext()) {
        List<Figure> figures = ((Shape) it.next()).getFigures();
        figures.forEach(figure -> {
          nShape.addFigure(figure);
        });
      }
      ShapeList nShapes = new ShapeList();
      nShapes.add(nShape);
      ShapeList pShapes = selectedShapes;
      removeShapes(pShapes);
      addShapes(nShapes);
      redoStack.clear();
      undoStack.push(new Action(pShapes, nShapes));
      selectedShapes = new ShapeList();
      selectedShapes.add(nShape);
    }
  }

  public final void splitShapes() {
    ShapeList nShapes = new ShapeList();
    ShapeList pShapes = selectedShapes;
    Iterator it = selectedShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.getFigures().forEach((figure) -> {
        nShapes.add(new Shape(figure));
      });
    }
    redoStack.clear();
    undoStack.push(new Action(pShapes, nShapes));
    removeShapes(pShapes);
    addShapes(nShapes);
    selectedShapes = new ShapeList();
    selectedShapes.addAll(nShapes);
  }

  public final void selectAll() {
    selectedShapes.clear();
    selectedShapes.addAll(shapes);
    repaint();
  }

  public final void undo() {
    if (!undoStack.isEmpty()) {
      Action act = undoStack.pop();
      redoStack.push(act.revertAction(this));
      selectedShapes.clear();
      repaint();
    }
  }

  public final void redo() {
    if (!redoStack.isEmpty()) {
      Action act = redoStack.pop();
      undoStack.push(act.revertAction(this));
      selectedShapes.clear();
      repaint();
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
          if (selectedShapes.contains(shape)) {
            Graphics2D graph = (Graphics2D) g;
            Stroke previous = graph.getStroke();
            graph.setColor(Color.gray);
            graph.setStroke(new BasicStroke(2,
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, DASHED_STROKE, dashed_phase));
            shape.draw(graph);
            graph.setStroke(previous);

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

}
