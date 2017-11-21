package scribble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorDialog extends JDialog implements ActionListener {

  private JButton okButton;
  private JButton cancelButton;
  private JButton moreColorButton;
  private ColorPanel colorPanel;
  private JColorChooser chooser = new JColorChooser();
  private Color color = null;

  private Color result = null;

  public ColorDialog(JFrame owner, String title) {
    this(owner, title, Color.black);
  }

  public ColorDialog(JFrame owner, String title, Color color) {
    super(owner, title, true);
    this.color = color;

    getContentPane().setLayout(new BorderLayout());

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    colorPanel = new ColorPanel(20, 20, 8, 8);
    topPanel.add(colorPanel, BorderLayout.CENTER);
    moreColorButton = new JButton("More colors");
    moreColorButton.addActionListener(this);
    topPanel.add(moreColorButton, BorderLayout.SOUTH);
    getContentPane().add(topPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    okButton = new JButton("Ok");
    okButton.addActionListener(this);
    bottomPanel.add(okButton);
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    bottomPanel.add(cancelButton);
    getContentPane().add(bottomPanel, BorderLayout.SOUTH);

    pack();
  }

  public Color showDialog() {
    result = null;
    colorPanel.setColor(color);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dialogSize = getSize();
    setLocation(screenSize.width / 2 - dialogSize.width / 2,
      screenSize.height / 2 - dialogSize.height / 2);
    //modificado
    setVisible(rootPaneCheckingEnabled);
    if (result != null) {
      color = result;
    }
    return result;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == okButton) {
      result = colorPanel.getColor();
    } else if (source == moreColorButton) {
      Color selectedColor = JColorChooser.showDialog(ColorDialog.this,
        "Choose color",
        color);
      if (selectedColor != null) {
        colorPanel.setColor(selectedColor);
        colorPanel.repaint();
      }
      return;
    }
    setVisible(false);
  }
}
