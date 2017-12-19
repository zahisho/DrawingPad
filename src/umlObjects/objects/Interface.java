package umlObjects.objects;

import umlObjects.UmlObject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import shape.UmlElement;

/**
 *
 * @author M16U3L
 */
public class Interface extends UmlObject {

  @Override
  public final void draw(Graphics g) {
    int t = nameClass.length();

    if (typeClass != null) {
      g.drawString(("<" + " " + typeClass + " " + ">"), (x + wh) - POSTYPECLAS, y);
    }
    Graphics2D g2g = (Graphics2D) g;
    x = Math.min(x1, x2);
    y = Math.min(y1, y2);
    wh = TAMCLAS;
    h = TAMCLAS;

    if (t <= TAMRANGLPE) {
      g.drawLine(x, y + wh / CUADSUP, x + TAMCLAS, y + wh / CUADSUP);
      g.drawString(nameClass, x + wh / POSEIGTH, y + h / POSWITH);
      g.drawRect(x, y, wh, h);
    }
    if (t > TAMRANGLPE && t <= TAMRANGLME) {
      wh = wh + t * CUADSUP;
      g.drawLine(x, y + wh / CUADSUP, x + wh, y + wh / CUADSUP);
      g.drawString(nameClass, x + wh / POSEIGTH, y + h / POSWITH);
      g.drawRect(x, y, wh, h);
    }
    if (t > TAMRANGLME && t < TAMRANGLGR) {
      wh = wh + t * POSWITH;
      g.drawLine(x, y + wh / CUADSUP, x + wh, y + wh / CUADSUP);
      g.drawString(nameClass, x + wh / POSEIGTH, y + h / POSWITH);
      g.drawRect(x, y, wh, h);
    }
  }

  @Override
  public final UmlElement getFigure() {
    return new Interface();
  }

  @Override
  public void generarCodigo(String path, ArrayList<File> path2,
    UmlObject clas) {
    for (int i = 0; i < path2.size(); i++) {
      try {
        file = new File(path2.get(i).getPath() + "//"
          + clas.getNameClass() + ".java");
        fileWriter = new FileWriter(file);

        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);

        if (clas.getNameClasss().isEmpty()) {
          printWriter.println(" ");
          printWriter.println(" ");
          printWriter.println("interface " + clas.getNameClass() + " " + "{");
          printWriter.write("}");
          printWriter.close();
        } else {
          String res = " ";
          for (int j = 0; j < clas.getNameClasss().size(); j++) {
            res = res + clas.getNameClasss().get(j) + ",";
          }
          res = res.substring(0, res.length() - 1);
          printWriter.println(" ");
          printWriter.println(" ");
          printWriter.println("interface " + clas.getNameClass() + " "
            + "implements " + res + " {");
          printWriter.write("}");
          printWriter.close();
        }
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Ha sucedido un error" + e);
      }
    }
  }
}
