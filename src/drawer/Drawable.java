
package drawer;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Richi007dx
 */
public interface Drawable {
 
 public void setColor(Color color);
 public Color getColor();
 public abstract void draw(Graphics g);
 
  
  
  
}
