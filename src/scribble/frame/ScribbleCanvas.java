package scribble.frame;

import scribble.drawing.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyListener;
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
import scribble.drawing.LineStyle;
import scribble.drawing.ShapeElement;
import scribble.drawing.Thickness;
import scribble.tool.TextTool;
import scribble.tool.Tool;

public class ScribbleCanvas extends JPanel {

  private final Scribble scribble;

  private final Stack<Action> undoStack;
  private final Stack<Action> redoStack;

  private LayerList layers;
  private ShapeList selectedShapes;
  private ShapeList curLayer;

  private Color curContourColor;
  private Color curFillingColor;
  private float curThickness;
  private float[] curLineStyle;

  private Tool curTool;

  private final Thread thread;
  private boolean showSelected;

  public ScribbleCanvas(Scribble s) {
    scribble = s;

    curLayer = new ShapeList();

    layers = new LayerList();
    layers.add(curLayer);

    selectedShapes = new ShapeList();

    curContourColor = Color.black;
    curFillingColor = null;
    curThickness = Thickness.NORMAL.getThickness();
    curLineStyle = LineStyle.NORMAL.getStyle();

    curTool = null;

    undoStack = new Stack<>();
    redoStack = new Stack<>();

    showSelected = true;

    thread = new Thread(() -> {
      while (true) {
        if (!selectedShapes.empty()) {
          blinkSelected();
          repaint();
        }
        try {
          Thread.sleep(200);
        } catch (InterruptedException ex) {
          Logger.getLogger(ScribbleCanvas.class.getName()).log(Level.SEVERE,
                  null, ex);
        }
      }
    });
    startThread();
  }

  private void startThread() {
    thread.start();
  }

  private void blinkSelected() {
    showSelected = !showSelected;
  }

  public final void setTool(Tool tool) {
    if (curTool != null) {
      removeMouseListener(curTool);
      removeMouseMotionListener(curTool);

      if (curTool instanceof KeyListener) {
        removeKeyListener((KeyListener) curTool);
      }

      if (curTool instanceof TextTool) {
        ((TextTool) curTool).endTyping();
      }
    }
    curTool = tool;

    if (curTool instanceof KeyListener) {
      addKeyListener((KeyListener) curTool);
    }

    addMouseListener(curTool);
    addMouseMotionListener(curTool);
    requestFocusInWindow();
  }

  public final void clearSelectedShapes() {
    selectedShapes = new ShapeList();
    scribble.disableEditOptions();
  }

  public final void addSelectedShape(Shape s) {
    if (!selectedShapes.contains(s)) {
      selectedShapes.add(s);
      scribble.enableEditOptions();
    } else {
      selectedShapes.remove(s);
      if (selectedShapes.empty()) {
        scribble.disableEditOptions();
      }
    }
  }

  private void addSelectedShapes(ShapeList ss) {
    if (!ss.empty()) {
      selectedShapes.addAll(ss);
      scribble.enableEditOptions();
    }
  }

  public final ShapeList getSelectedShapes() {
    return selectedShapes;
  }

  private void clearRedo() {
    redoStack.clear();
    scribble.disableRedo();
  }

  private void pushNewAction(Action act) {
    undoStack.push(act);
    scribble.enableUndo();
  }

  private ShapeList startNewAction() {
    ShapeList pShapes = selectedShapes;
    ShapeList nShapes = pShapes.copy();

    clearRedo();

    pushNewAction(new Action(pShapes, nShapes, curLayer, curLayer));

    removeShapes(pShapes, curLayer);
    addShapes(nShapes, curLayer);

    clearSelectedShapes();
    addSelectedShapes(nShapes);

    return nShapes;
  }

  public final Color getContourColor() {
    return curContourColor;
  }

  public final void setContourColor(Color nColor) {
    this.curContourColor = nColor;
    if (!selectedShapes.empty()) {
      changeContour();
    }
  }

  private void changeContour() {
    ShapeList nShapes = startNewAction();
    Iterator it = nShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.setContourColor(curContourColor);
    }
    repaint();
  }

  public final Color getFillingColor() {
    return curFillingColor;
  }

  public final void setFillingColor(Color nColor) {
    this.curFillingColor = nColor;
    if (!selectedShapes.empty()) {
      changeFilling();
    }
  }

  private void changeFilling() {
    ShapeList nShapes = startNewAction();
    Iterator it = nShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.setFillingColor(curFillingColor);
    }
    repaint();
  }

  public final float getThickness() {
    return curThickness;
  }

  public final void setCurThickness(float t) {
    curThickness = t;

    if (!selectedShapes.empty()) {
      changeThickness();
    }
  }

  private void changeThickness() {
    ShapeList nShapes = startNewAction();
    Iterator it = nShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.setThickness(curThickness);
    }
    repaint();
  }

  public final float[] getLineStyle() {
    return curLineStyle;
  }

  public final void setCurLineStyle(float[] s) {
    curLineStyle = s;

    if (!selectedShapes.empty()) {
      changeLineStyle();
    }
  }

  private void changeLineStyle() {
    ShapeList nShapes = startNewAction();
    Iterator it = nShapes.iterator();
    while (it.hasNext()) {
      Shape s = (Shape) it.next();
      s.setLineStyle(curLineStyle);
    }
    repaint();
  }

  public final ShapeList getCurLayer() {
    return curLayer;
  }

  public final void addNewShape(Shape shape) {
    ShapeList nShapes = new ShapeList();
    nShapes.add(shape);

    clearRedo();
    pushNewAction(new Action(null, nShapes, curLayer, curLayer));

    addShapes(nShapes, curLayer);

    repaint();
  }

  public final void addShapes(ShapeList ss, ShapeList layer) {
    if (ss != null && curLayer != null) {
      layer.addAll(ss);
    }
  }

  public final void deleteSelectedShapes() {
    if (!selectedShapes.empty()) {
      clearRedo();

      ShapeList pShapes = selectedShapes;

      pushNewAction(new Action(pShapes, null, curLayer, curLayer));

      removeShapes(pShapes, curLayer);
      clearSelectedShapes();

      repaint();
    }
  }

  public final void removeShapes(ShapeList ss, ShapeList layer) {
    if (ss != null) {
      layer.removeAll(ss);
    }
  }

  public final void startMovement() {
    startNewAction();
  }

  public final void moveShapes(Point p) {
    Iterator selectedShape = selectedShapes.iterator();
    while (selectedShape.hasNext()) {
      Shape s = (Shape) selectedShape.next();
      s.move(p);
    }
    repaint();
  }

  public final void groupShapes() {
    if (selectedShapes.size() > 1) {
      Shape nShape = new Shape();
      Iterator it = selectedShapes.iterator();
      while (it.hasNext()) {
        List<ShapeElement> figures = ((Shape) it.next()).getElements();
        figures.forEach(figure -> {
          nShape.addShapeElement(figure);
        });
      }
      ShapeList nShapes = new ShapeList();
      nShapes.add(nShape);
      ShapeList pShapes = selectedShapes;
      removeShapes(pShapes, curLayer);
      addShapes(nShapes, curLayer);
      clearRedo();
      pushNewAction(new Action(pShapes, nShapes, curLayer, curLayer));
      clearSelectedShapes();
      addSelectedShapes(nShapes);
    }
  }

  public final void splitShapes() {
    if (!selectedShapes.empty()) {
      ShapeList nShapes = new ShapeList();
      ShapeList pShapes = selectedShapes;
      Iterator it = selectedShapes.iterator();
      while (it.hasNext()) {
        Shape s = (Shape) it.next();
        s.getElements().forEach((figure) -> {
          nShapes.add(new Shape(figure));
        });
      }
      clearRedo();
      pushNewAction(new Action(pShapes, nShapes, curLayer, curLayer));
      removeShapes(pShapes, curLayer);
      addShapes(nShapes, curLayer);
      clearSelectedShapes();
      addSelectedShapes(nShapes);
    }
  }

  public final void selectAll() {
    clearSelectedShapes();
    addSelectedShapes(curLayer);
    repaint();
  }

  public final void undo() {
    if (!undoStack.isEmpty()) {
      Action act = undoStack.pop();

      if (undoStack.isEmpty()) {
        scribble.disableUndo();
      }

      redoStack.push(act.revertAction(this));
      scribble.enableRedo();
      clearSelectedShapes();
      repaint();
    }
  }

  public final void redo() {
    if (!redoStack.isEmpty()) {
      Action act = redoStack.pop();

      if (redoStack.isEmpty()) {
        scribble.disableRedo();
      }

      pushNewAction(act.revertAction(this));
      clearSelectedShapes();
      repaint();
    }
  }

  public final void moveLayerFront() {
    if (curLayer != null) {
      int index = layers.indexOf(curLayer);
      if (index < layers.size() - 1) {
        layers.remove(curLayer);
        layers.add(index + 1, curLayer);
      }
      scribble.updateLayersMenu();
      clearSelectedShapes();
    }
  }

  public final void moveLayerBack() {
    if (curLayer != null) {
      int index = layers.indexOf(curLayer);
      if (index > 0) {
        layers.remove(curLayer);
        layers.add(index - 1, curLayer);
      }
      scribble.updateLayersMenu();
      clearSelectedShapes();
    }
  }

  public final void moveLayerTop() {
    if (curLayer != null) {
      int index = layers.indexOf(curLayer);
      if (index < layers.size() - 1) {
        layers.remove(curLayer);
        layers.add(curLayer);
      }
      scribble.updateLayersMenu();
      clearSelectedShapes();
    }
  }

  public final void moveLayerBottom() {
    if (curLayer != null) {
      int index = layers.indexOf(curLayer);
      if (index > 0) {
        layers.remove(curLayer);
        layers.add(0, curLayer);
      }
      scribble.updateLayersMenu();
      clearSelectedShapes();
    }
  }

  public final void showLayer(int index) {
    layers.get(index).show();
    scribble.updateLayersMenu();
  }

  public final void hideLayer(int index) {
    layers.get(index).hide();
    if (curLayer == layers.get(index)) {
      curLayer = null;
      selectedShapes.clear();
    }
    scribble.updateLayersMenu();
    clearSelectedShapes();
  }

  public final void newLayer() {
    ShapeList nLayer = new ShapeList();
    layers.add(nLayer);
    scribble.updateLayersMenu();
    clearSelectedShapes();
  }

  public final void removeCurrentLayer() {
    layers.remove(curLayer);
    curLayer = null;
    scribble.updateLayersMenu();
    clearSelectedShapes();
  }

  public final void setCurLayer(int index) {
    if (index >= 0 && index < layers.size()) {
      curLayer = layers.get(index);
    } else {
      curLayer = null;
    }
    clearSelectedShapes();
  }

  public final LayerList getLayers() {
    return layers;
  }

  @Override
  public final void paint(final Graphics g) {
    Dimension dim = getSize();
    g.setColor(Color.white);
    g.fillRect(0, 0, dim.width, dim.height);
    g.setColor(Color.black);
    layers.forEach((layer) -> {
      if (layer.getShow()) {
        Iterator iter = layer.iterator();
        while (iter.hasNext()) {
          Shape shape = (Shape) iter.next();
          if (shape != null) {
            if (selectedShapes.contains(shape)) {
              if (showSelected) {
                shape.draw(g);
              }
            } else {
              shape.draw(g);
            }
          }
        }
      }
    });
    revalidate();
  }

  public final void newFile() {
    layers = new LayerList();
    curLayer = new ShapeList();
    repaint();
  }

  public final void openFile(String filename) {
    try {
      try (ObjectInputStream in = new ObjectInputStream(
              new FileInputStream(filename))) {
        layers = (LayerList) in.readObject();
        scribble.updateLayersMenu();
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
        out.writeObject(layers);
      }
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.err.println(e);
      System.out.println("Unable to write file: " + filename);
    }
  }
}
