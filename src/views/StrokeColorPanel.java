package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class StrokeColorPanel extends JPanel implements ActionListener {

  private ScribbleCanvas panelCanvas;

  private JButton okButton;
  private JButton moreColorButton;
  private Color color;

  private Color result;
  private ColorPanel colorPanel;

  public StrokeColorPanel(ScribbleCanvas panelCanvas) {
    this.panelCanvas = panelCanvas;
    setLayout(new BorderLayout());

    JLabel titleChooseColor = new JLabel("STROKE");
    add(titleChooseColor, BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    colorPanel = new ColorPanel(20, 20, 8, 8);
    topPanel.add(colorPanel, BorderLayout.CENTER);
    add(topPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
    moreColorButton = new JButton("More colors");
    moreColorButton.addActionListener(this);
    okButton = new JButton("Ok");
    okButton.addActionListener(this);
    bottomPanel.add(moreColorButton);
    bottomPanel.add(okButton);
    add(bottomPanel, BorderLayout.SOUTH);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == okButton) {
      //panelCanvas.setCurColor(colorPanel.getColor());
      result = colorPanel.getColor();
      panelCanvas.changeBorderlineSelectedShapes();
    } else if (source == moreColorButton) {
      Color selectedColor = JColorChooser.showDialog(StrokeColorPanel.this,
        "Choose color", color);
      if (selectedColor != null) {
        colorPanel.setColor(selectedColor);
        colorPanel.repaint();
      }
    }
  }
}
