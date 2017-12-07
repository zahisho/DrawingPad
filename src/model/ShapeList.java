package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapeList implements Serializable {

  private List<ShapeAbstract> list;

  public ShapeList() {
    list = new ArrayList<>();
  }

  public final void add(ShapeAbstract s) {
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
    if (!list.isEmpty()) {
      list.remove(index);
    }
  }
  
  public final void remove(ShapeAbstract selectShape){
    if(!list.isEmpty()){
      list.remove(selectShape);
    }
  }
  
  public final void resetSelected(){
    list.forEach((shape) -> {
      shape.setSelected(false);
    });
  }
  
  public void moveToTheLas(ShapeAbstract shape){
    if(!list.isEmpty()){
      list.remove(shape);
      list.add(shape);
    }
  }
  
  public final void selectAll(){
    list.forEach((shape) -> {
      shape.setSelected(true);
    });
  }
 
}
