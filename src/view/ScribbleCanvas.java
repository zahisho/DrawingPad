package view;

import model.ShapeAbstract;
import model.ShapeList;
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
import model.GroupShapes;

public class ScribbleCanvas extends JPanel {

  protected ShapeList shapes;
  protected List<ShapeList> undoList;
  protected List<ShapeList> redoList;

  protected Color curColor;

  protected EventListener listener;

  public boolean mouseButtonDown;
  public int x;
  public int y;
  
  public ScribbleCanvas() {
    shapes = new ShapeList();
    undoList = new ArrayList<>();
    undoList.add(clonList());
    redoList = new ArrayList<>();
//    redoList.add(clonList());
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

  public void addShape(ShapeAbstract shape) {
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
        ShapeAbstract shape = (ShapeAbstract) iter.next();
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
      shapes = (ShapeList) in.readObject();
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
    if(undoList.size() > 0){
      redoList.add(clonList());      
      shapes = undoList.remove(undoList.size() - 1);     
    }
    repaint();
  }
  
  public void redo(){
    if(redoList.size() > 0){
      undoList.add(clonList());
      shapes = redoList.remove(redoList.size() - 1);
    }
    repaint();
  }
    
  public void selectAll(){
    shapes.selectAll();
    repaint();
  }
  
  public void clearAll(){
    undoList.add(clonList());
    shapes = new ShapeList();
    repaint();
  }
  
  public void fillShape(){
    if (shapes != null) {
      undoList.add(clonList());
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          if (shape.getSelected()){
            shape.fillColor(curColor);
          }
        }
      }
    }
    repaint();
  }
  
  public void changeContourColor(){
    if (shapes != null) {
      undoList.add(clonList());
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          if (shape.getSelected()){
            shape.setColor(curColor);
          }
        }
      }
    }
    repaint();
  }
  
  public void groupShapes(){
    undoList.add(clonList());
    ShapeList selectedShapes = new ShapeList();
    
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          if (shape.getSelected()){
            selectedShapes.add(shape);
          }
        }
      }
    }
    if(selectedShapes != null){
      Iterator iter = selectedShapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shapes.remove(shape);
        }
      }
    }
    
    GroupShapes group = new GroupShapes();
    group.setShapes(selectedShapes);
    
    shapes.add(group);
    shapes.resetSelected();
    repaint();
  }
  
  public void ungroupShapes(){
    undoList.add(clonList());
    ShapeList groupSelectedShapes = new ShapeList();
    ShapeList updateShapes = new ShapeList();
    if(shapes != null){
      Iterator iter = shapes.iterator();
      while (iter.hasNext()){
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if (shape != null){
          if(shape.getSelected() && shape instanceof GroupShapes){
            groupSelectedShapes.add(shape);
          }
          else{
            updateShapes.add(shape);
          }
        }
      }
    }
    
    if(groupSelectedShapes != null){
      Iterator iter = groupSelectedShapes.iterator();
      while (iter.hasNext()){
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if (shape != null){
          addExGroupListShapes( (GroupShapes) shape, updateShapes);
        }
      }
    }
    
    shapes = updateShapes;
    shapes.resetSelected();
    repaint();
    
  }
  
  private void addExGroupListShapes(GroupShapes groupShapes, ShapeList updateShapes){
    ShapeList exGroupShapes = groupShapes.getShapes();
    if(exGroupShapes != null){
      Iterator iter = exGroupShapes.iterator();
      while (iter.hasNext()){
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if (shape != null){
          updateShapes.add(shape);
        }
      }
    }
  }
  
  // factory method 
  protected EventListener makeCanvasListener() {
    return (new ScribbleCanvasListener(this));
  }
  
  public ShapeAbstract belongShape(Point p){
    if(shapes != null){
      Iterator iter = shapes.iterator();
      while (iter.hasNext()){
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if (shape != null){
          if(shape.isSelected(p)){
            return shape;
          }
        }
      }
    }
    return null;
  }
  
  public ShapeList getShapes(){
    return shapes;
  }
  
  public ShapeList clonList(){
    ShapeList clonShapes = new ShapeList();
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          clonShapes.add(shape.clonShape());
        }
      }
    }
    return clonShapes;
  }
  
}
