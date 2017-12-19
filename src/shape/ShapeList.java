package shape;

/**
 *
 * @author M16U3L
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

  public final boolean isEmpty() {
    return list.isEmpty();
  }

  public int getSize() {
    return list.size();
  }
}
