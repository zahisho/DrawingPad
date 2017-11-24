package scribble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapeList implements Serializable {

  private final List<Shape> list;
  private final List<Shape> shapesUndo;

  public ShapeList() {
    list = new ArrayList<>();
    shapesUndo = new ArrayList<>();
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
  
  public final int size(){
    return list.size();
  }
  
  public final void remove(int index){
    if (!list.isEmpty()){
      shapesUndo.add(list.remove(index));
    }
  }
  
  public final void redo(){
    if(!shapesUndo.isEmpty()){
      list.add(shapesUndo.remove(shapesUndo.size() -1 ));
    }
  }
}
