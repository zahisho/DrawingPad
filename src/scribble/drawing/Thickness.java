package scribble.drawing;

public enum Thickness {
  NORMAL(1f, "Normal"),
  THICK(2f, "Thick"),
  THICKER(3f, "Thicker");

  private final float thickness;
  private final String label;

  Thickness(float t, String l) {
    thickness = t;
    label = l;
  }

  public final float getThickness() {
    return thickness;
  }

  public final String getLabel() {
    return label;
  }
}
