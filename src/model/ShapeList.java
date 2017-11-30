package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapeList implements Serializable {

  private List<Shape> list;
  private List<Shape> shapesUndo;
  private boolean isClearAll;

  public ShapeList() {
    list = new ArrayList<>();
    shapesUndo = new ArrayList<>();
    isClearAll = false;
  }

  public final void add(Shape s) {
    list.add(s);
  }

  public final Iterator iterator() {
    return list.iterator();
  }

  public final void clear() {
    list.clear();
  }

  public final void removeLast() {
    if (list.size() > 0) {
      list.remove(list.size() - 1);
    }
  }
  
  public final int size() {
    return list.size();
  }

  public final void remove(int index) {
    if (isClearAll) {
      changeListToShape();
    } 
    else{
      if (!list.isEmpty()) {
        shapesUndo.add(list.remove(index));
      }
    }
  }
  
  public final void remove(Shape selectShape){
    if(!list.isEmpty()){
      list.remove(selectShape);
      shapesUndo.add(selectShape);
    }
  }

  public final void redo() {
    if (!shapesUndo.isEmpty()) {
      list.add(shapesUndo.remove(shapesUndo.size() - 1));
    }
  }

  public final void clearAll(){
    shapesUndo = list;
    list = new ArrayList<>();
    isClearAll = true;
  }
  
  private void changeListToShape(){
    list = shapesUndo;
    shapesUndo = new ArrayList<>();
    isClearAll = false;
  }
}
