package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapeList implements Serializable {

  private List<Shape> list;
  private List<Shape> shapesRedo;
  private boolean isClearAll;

  public ShapeList() {
    list = new ArrayList<>();
    shapesRedo = new ArrayList<>();
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
      list = shapesRedo;
      shapesRedo = new ArrayList<>();
      isClearAll = false;
    } 
    else{
      if (!list.isEmpty()) {
        shapesRedo.add(list.remove(index));
      }
    }
  }
  
  public final void remove(Shape selectShape){
    if(!list.isEmpty()){
      shapesRedo.add(selectShape);
      list.remove(selectShape);
    }
  }

  public final void undo(){
    if (!list.isEmpty()) {
      int last = list.size() - 1;
      if(list.get(last).getUndoState() != null){
        list.set(last, list.get(last).getUndoState());                
      }
      else{
        shapesRedo.add(list.remove(last));
      }
    }
  }

  public final void redo() {
    if (!list.isEmpty()) {
      int last = list.size() - 1;
      if (list.get(last).getRedoState() != null) {
        list.set(last, list.get(last).getRedoState());
      } else {
        if (!shapesRedo.isEmpty()) {
          int shapesUndoLast = shapesRedo.size() - 1;
          list.add(shapesRedo.remove(shapesUndoLast));
        }
      }
    } else {
      if (!shapesRedo.isEmpty()) {
        int shapesUndoLast = shapesRedo.size() - 1;
        list.add(shapesRedo.remove(shapesUndoLast));
      }
    }
  }
  
  public void moveToTheLas(Shape shape){
    if(!list.isEmpty()){
      list.remove(shape);
      list.add(shape);
    }
  }

  public final void clearAll(){
    shapesRedo = list;
    list = new ArrayList<>();
    isClearAll = true;
  }
}
