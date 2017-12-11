package scribble.drawing;

public enum LineStyle {
  NORMAL(null, "Normal"),
  DASHED(new float[]{5, 2}, "Dashed");

  private final float[] style;
  private final String label;

  LineStyle(float[] s, String l) {
    style = s;
    label = l;
  }

  public final float[] getStyle() {
    return style;
  }

  public final String getLabel() {
    return label;
  }
}
