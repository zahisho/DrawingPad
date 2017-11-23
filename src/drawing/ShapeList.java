package drawing;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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

  public final void removeLast() {
    if (list.size() > 0) {
      list.remove(list.size() - 1);
    }
  }
}
