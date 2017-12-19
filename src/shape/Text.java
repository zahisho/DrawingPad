package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author M16U3L
 */
public class Text extends UmlElement implements Movable {

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private Point punto;

  private final double EPS = 10;

  private String text;

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
  public UmlElement getFigure() {
    return new Text();
  }

  @Override
  public void startFigure(Point p, Graphics g) {
    g.drawString(text, p.x, p.y);
    punto = p;
    overDraw(g);
  }

  @Override
  public void updateFigure(Point p, Graphics g) {
    overDraw(g);
    setEnds(p.x, p.y, p.x, p.y);
    overDraw(g);

  }

  @Override
  public void draw(Graphics g) {
    g.drawString(text, punto.x, punto.y);

  }

  @Override
  public boolean isSelected(Point p) {
    boolean res;
    res = Math.hypot(punto.x - p.x, punto.y - p.y) < EPS;
    return res;

  }

  @Override
  public void move(Point p) {
    punto.x += p.x;
    punto.y += p.y;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
