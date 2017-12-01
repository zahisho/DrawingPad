
import java.util.ArrayList;
import java.util.List;
import scribble.Tool;

public class ToolKit {

  /**
   * Add a new tool to the tool kit. Return the index of the new tool.
   *
   * @param tool
   * @return
   */
  public final int addTool(final Tool tool) {
    if (tool != null) {
      tools.add(tool);
      return (tools.size() - 1);
    }
    return -1;
  }

  public final int getToolCount() {
    return tools.size();
  }

  public final Tool getTool(final int i) {
    if (i >= 0
            && i < tools.size()) {
      return (Tool) tools.get(i);
    }
    return null;
  }

  public final Tool findTool(final String name) {
    if (name != null) {
      for (int i = 0; i < tools.size(); i++) {
        Tool tool = (Tool) tools.get(i);
        if (name.equals(tool.getName())) {
          return tool;
        }
      }
    }
    return null;
  }

  public final void setSelectedTool(final int i) {
    Tool tool = getTool(i);
    if (tool != null) {
      selectedTool = tool;
    }
  }

  public final Tool setSelectedTool(final String name) {
    Tool tool = findTool(name);
    if (tool != null) {
      selectedTool = tool;
    }
    return tool;
  }

  public final void setSelectedTool(final Tool tool) {
    selectedTool = tool;
  }

  public final Tool getSelectedTool() {
    return selectedTool;
  }

  private final List tools = new ArrayList(16);
  private Tool selectedTool = null;

}
