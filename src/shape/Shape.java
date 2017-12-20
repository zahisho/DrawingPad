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
import umlObjects.UmlRelationship;

public class Shape implements Serializable {

  private final UmlElement umlElement;
  private final int GROSOR = 4;
  private Color color;
  private final float[] DASHED_STROKE = new float[]{5, 2};

  public Shape(UmlElement figure) {
    umlElement = figure;
  }

  public final void startFigure(Point p, Graphics g) {
    umlElement.startFigure(p, g);
  }

  public final void updateFigure(Point p, Graphics g) {
    umlElement.updateFigure(p, g);
  }

  public final void draw(Graphics g) {
    umlElement.draw(g);
  }

  public final void setSelected(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    Stroke previous = graph.getStroke();
    graph.setStroke(new BasicStroke(GROSOR,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
      0, DASHED_STROKE, 0));
    umlElement.draw(g);
    graph.setStroke(previous);
  }

  public final boolean isSelected(Point p) {
    return umlElement.isSelected(p);
  }

  public void setText(String text) {
    if (umlElement instanceof Text) {
      ((Text) umlElement).setText(text);
    }
  }

  public final void move(Point p) {
    if (umlElement instanceof Movable) {
      ((Movable) umlElement).move(p);
    }
  }

  public final void setContourColor(Color c) {
    color = c;
  }

  public void generarCodigo(String path, ArrayList<File> path2) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).generarCodigo(path, path2);
    }

  }

  public String getNameClass() {
    String x = null;
    if (umlElement instanceof UmlObject) {
      x = ((UmlObject) umlElement).getNameClass();
      return x;

    }
    return x;
  }

  public void setNameClass(String text) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setNameClass(text);
    }
  }

  public void typeClassAbstract() {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setTypeClass("A");
    }
  }

  public void typeClassInterface() {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setTypeClass("I");
    }
  }

  public void setTypeClass(String text) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setTypeClass(text);

    }
  }

  public String getTypeClass() {
    String x = null;
    if (umlElement instanceof UmlObject) {
      x = ((UmlObject) umlElement).getTypeClass();
      return x;

    }
    return x;
  }

  public String getNameTypeClass() {
    String x = null;
    if (umlElement instanceof UmlObject) {
      x = ((UmlObject) umlElement).getNameTypeClass();
      return x;

    }
    return x;
  }

  public void setNameTypeClass(String text) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setNameTypeClass(text);
    }
  }

  public ArrayList<String> getNameClasss() {
    ArrayList<String> x = null;
    if (umlElement instanceof UmlObject) {
      x = ((UmlObject) umlElement).getNameClasss();
      return x;

    }
    return x;
  }

  public void setNameClasss(ArrayList<String> text) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setNameClasss(text);
    }
  }

  public void setNameClass2(String nameClass) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).setNameClass2(nameClass);
    }
  }

  public void addNameClasss(String text) {
    if (umlElement instanceof UmlObject) {
      ((UmlObject) umlElement).getNameClasss().add(text);//nameClasss.add(text);
    }

  }

  public String getNameClass2() {
    String x = null;
    if (umlElement instanceof UmlObject) {
      x = ((UmlObject) umlElement).getNameClass2();
      return x;

    }
    return x;
  }

  public boolean getSelect() {
    return umlElement.getSelect();
  }

  public void setSelect(boolean b) {
    umlElement.setSelect(b);
  }

  public UmlElement getFigure() {
    return umlElement;
  }

  public void setReference(Point point) {
    ((UmlObject) umlElement).setReference(point);
  }
}
