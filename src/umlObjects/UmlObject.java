package umlObjects;

import shape.UmlElement;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import shape.Movable;

public abstract class UmlObject extends UmlElement implements Movable, Subject {

  protected File file;
  protected FileWriter fileWriter;
  protected BufferedWriter bufferedWriter;
  protected PrintWriter printWriter;
  protected String nameClass;
  protected String typeClass;
  protected String nameTypeClass;
  protected String nameClass2;

  protected final double EPS = 5;
  protected final int POSTYPECLAS = 20;
  protected final int TAMCLAS = 100;
  protected final int TAMRANGLPE = 10;
  protected final int TAMRANGLME = 18;
  protected final int TAMRANGLGR = 30;
  protected final int CUADSUP = 3;
  protected final int POSWITH = 5;
  protected final int POSEIGTH = 6;

  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;
  protected int x;
  protected int y;
  protected int wh;
  protected int h;

  //protected ArrayList<String> nameClasss = new ArrayList<>();
  //public ArrayList<Observer> observers;
  public ArrayList<String> nameClasss;
  public ArrayList<Observer> observers;

  public UmlObject() {
    nameClasss = new ArrayList<>();
    observers = new ArrayList<>();
  }

  @Override
  public void notifyUpdate() {
    for (Observer o : observers) {
      o.update();
    }
  }

  public void joinObject(Observer o) {
    observers.add(o);
  }

  private void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  private void overDraw(Graphics g) {
    Color curColor = g.getColor();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    draw(g);
    g.setColor(curColor);
  }

  @Override
  public final void updateFigure(Point p, Graphics g) {
    overDraw(g);
    setEnds(x1, y1, p.x, p.y);
    overDraw(g);

  }

  @Override
  public final void startFigure(Point p, Graphics g) {
    setEnds(p.x, p.y, p.x, p.y);
    overDraw(g);
  }

  @Override
  public final boolean isSelected(Point p) {
    boolean res = false;
    if (!res) {
      if ((p.x > x) && (p.x < (x + wh)) && (p.y > y)
        && (p.y < (y + h))) {
        res = true;
      }
    }
    return res;
  }

  public String getNameClass() {
    return nameClass;
  }

  public void setNameClass(String text) {
    this.nameClass = text;
  }

  public void generarCodigo(String path, ArrayList<File> path2) {
    this.generarCodigo(path, path2, this);
  }

  public String getTypeClass() {
    return typeClass;
  }

  public void setTypeClass(String typeClass) {
    this.typeClass = typeClass;
  }

  public String getNameTypeClass() {
    return nameTypeClass;
  }

  public void setNameTypeClass(String nameTypeClass) {
    this.nameTypeClass = nameTypeClass;
  }

  public ArrayList<String> getNameClasss() {
    return nameClasss;
  }

  public void setNameClasss(ArrayList<String> nameClasss) {
    this.nameClasss = nameClasss;
  }

  public void setNameClass2(String nameClass) {
    this.nameClass2 = nameClass;
  }

  public String getNameClass2() {
    return nameClass2;
  }

  @Override
  public void move(Point p) {
    x1 += p.x;
    x2 += p.x;
    y1 += p.y;
    y2 += p.y;
  }

  public abstract void generarCodigo(String path, ArrayList<File> path2, UmlObject aThis);
}
