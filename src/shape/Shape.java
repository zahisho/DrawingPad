package shape;

import umlObjects.UmlObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Shape implements Serializable {

  private final UmlElement umElement;
  private final int GROSOR = 4;
  private Color color;
  private final float[] DASHED_STROKE = new float[]{5, 2};

  public Shape(UmlElement figure) {
    umElement = figure;
  }

  public final void startFigure(Point p, Graphics g) {
    umElement.startFigure(p, g);
  }

  public final void updateFigure(Point p, Graphics g) {
    umElement.updateFigure(p, g);
  }

  public final void draw(Graphics g) {
    g.setColor(color);
    umElement.draw(g);
  }

  public final void setSelected(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    Stroke previous = graph.getStroke();
    graph.setColor(Color.lightGray);
    graph.setStroke(new BasicStroke(GROSOR,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
      0, DASHED_STROKE, 0));
    umElement.draw(g);
    graph.setStroke(previous);
  }

  public final boolean isSelected(Point p) {
    return umElement.isSelected(p);
  }

  public void setText(String text) {
    if (umElement instanceof Text) {
      ((Text) umElement).setText(text);
    }
  }

  public final void move(Point p) {
    if (umElement instanceof Movable) {
      ((Movable) umElement).move(p);
    }
  }

  public final void setContourColor(Color c) {
    color = c;
  }

  public void generarCodigo(String path, ArrayList<File> path2) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).generarCodigo(path, path2);
    }

  }

  public String getNameClass() {
    String x = null;
    if (umElement instanceof UmlObject) {
      x = ((UmlObject) umElement).getNameClass();
      return x;

    }
    return x;
  }

  public void setNameClass(String text) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setNameClass(text);
    }
  }

  public void typeClassAbstract() {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setTypeClass("A");
    }
  }

  public void typeClassInterface() {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setTypeClass("I");
    }
  }

  public void setTypeClass(String text) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setTypeClass(text);

    }
  }

  public String getTypeClass() {
    String x = null;
    if (umElement instanceof UmlObject) {
      x = ((UmlObject) umElement).getTypeClass();
      return x;

    }
    return x;
  }

  public String getNameTypeClass() {
    String x = null;
    if (umElement instanceof UmlObject) {
      x = ((UmlObject) umElement).getNameTypeClass();
      return x;

    }
    return x;
  }

  public void setNameTypeClass(String text) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setNameTypeClass(text);
    }
  }

  public ArrayList<String> getNameClasss() {
    ArrayList<String> x = null;
    if (umElement instanceof UmlObject) {
      x = ((UmlObject) umElement).getNameClasss();
      return x;

    }
    return x;
  }

  public void setNameClasss(ArrayList<String> text) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setNameClasss(text);
    }
  }

  public void setNameClass2(String nameClass) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).setNameClass2(nameClass);
    }
  }

  public void addNameClasss(String text) {
    if (umElement instanceof UmlObject) {
      ((UmlObject) umElement).getNameClasss().add(text);//nameClasss.add(text);
    }

  }

  public String getNameClass2() {
    String x = null;
    if (umElement instanceof UmlObject) {
      x = ((UmlObject) umElement).getNameClass2();
      return x;

    }
    return x;
  }

  public boolean getSelect() {
    return umElement.getSelect();
  }

  public void setSelect(boolean b) {
    umElement.setSelect(b);
  }
}
