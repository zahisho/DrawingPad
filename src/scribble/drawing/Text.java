package scribble.drawing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Text extends ShapeElement {

  private final int STRING_HEIGHT = 20;
  private final int STRING_OFFSET = 5;

  private final FontMetrics metrics;

  private StringBuilder txt;
  private int x;
  private int y;

  public Text(int x, int y, FontMetrics m) {
    txt = new StringBuilder();
    this.x = x + STRING_OFFSET;
    this.y = y + STRING_HEIGHT - STRING_OFFSET;
    metrics = m;
  }

  public final void addLetter(char ch, Graphics g) {
    overDraw(g);
    txt.append(ch);
  }

  public final void removeLastLetter(Graphics g) {
    overDraw(g);
    txt.deleteCharAt(txt.length() - 1);
  }

  public final void setText(String s) {
    txt = new StringBuilder(s);
  }

  private void overDraw(Graphics g) {
    int xi = x - STRING_OFFSET;
    int yi = y - STRING_HEIGHT + STRING_OFFSET;
    int w = metrics.stringWidth(txt.toString()) + 2 * STRING_OFFSET;

    Graphics2D gr = (Graphics2D) g;

    g.setColor(Color.white);
    gr.fillRect(xi, yi, w, STRING_HEIGHT);
  }

  public final boolean empty() {
    return txt.length() == 0;
  }

  @Override
  public final void draw(Graphics g) {
    overDraw(g);
    Graphics2D gr = (Graphics2D) g;
    gr.setColor(contourColor);
    gr.drawString(txt.toString(), x, y);
  }

  @Override
  public final boolean isSelected(Point p) {
    int xi = x - STRING_OFFSET;
    int yi = y - STRING_HEIGHT + STRING_OFFSET;
    int xf = xi + metrics.stringWidth(txt.toString()) + 2 * STRING_OFFSET;
    int yf = y + STRING_HEIGHT;

    return xi <= p.x && xf >= p.x && yi <= p.y && yf >= p.y;
  }

  @Override
  public final void move(Point p) {
    x += p.x;
    y += p.y;
  }

  @Override
  public ShapeElement copy() {
    Text nText = new Text(x - STRING_OFFSET,
            y - STRING_HEIGHT + STRING_OFFSET, metrics);
    nText.setText(txt.toString());
    nText.setContour(contourColor);

    return nText;
  }
}
