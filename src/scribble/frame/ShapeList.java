package scribble.frame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import scribble.drawing.Shape;

public class ShapeList implements Serializable {

  private final List<Shape> list;

  public ShapeList() {
    list = new ArrayList<>();
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

  public final void remove(Shape s) {
    int i = 0;

    while (i < list.size() && list.get(i) != s) {
      i++;
    }
    if (i < list.size()) {
      list.remove(i);
    }
  }

  public final boolean contains(Shape s) {
    boolean res = false;

    for (Shape shape : list) {
      if (s == shape) {
        res = true;
        break;
      }
    }

    return res;
  }

  public final boolean empty() {
    return list.isEmpty();
  }

  public final void addAll(ShapeList shapes) {
    Iterator it = shapes.iterator();
    while (it.hasNext()) {
      list.add((Shape) it.next());
    }
  }

  public final void removeAll(ShapeList shapes) {
    Iterator it = shapes.iterator();
    while (it.hasNext()) {
      list.remove((Shape) it.next());
    }
  }

  public final ShapeList copy() {
    ShapeList nShapeList = new ShapeList();
    list.forEach((s) -> {
      nShapeList.add(s.copy());
    });
    return nShapeList;
  }

  public final int size() {
    return list.size();
  }
}
