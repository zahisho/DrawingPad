
import java.util.ArrayList;
import java.util.List;
import scribble.Tool;

public class ToolKit {
  protected List<Tool> tools;
  protected Tool selectedTool;

  public ToolKit() {
    tools = new ArrayList<>(16);
    selectedTool = null;
  }

  /**
   * Add a new tool to the tool kit. Return the index of the new tool.
   * @param tool
   * @return
   */
  public int addTool(Tool tool) {
    int res = -1;
    if (tool != null) {
      tools.add(tool);
      res = (tools.size() - 1);
    }
    return res;
  }

  public final int getToolCount() {
    return tools.size();
  }

  public final Tool getTool(int i) {
    if (i >= 0
            && i < tools.size()) {
      return (Tool) tools.get(i);
    }
    return null;
  }

  public final Tool findTool(String name) {
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

  public final void setSelectedTool(int i) {
    Tool tool = getTool(i);
    if (tool != null) {
      selectedTool = tool;
    }
  }

  public final Tool setSelectedTool(String name) {
    Tool tool = findTool(name);
    if (tool != null) {
      selectedTool = tool;
    }
    return tool;
  }

  public final void setSelectedTool(Tool tool) {
    selectedTool = tool;
  }

  public final Tool getSelectedTool() {
    return selectedTool;
  }
}
