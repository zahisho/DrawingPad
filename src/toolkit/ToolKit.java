package toolkit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M16U3L
 */
public class ToolKit {

  private List<Tool> tools;
  private Tool selectedTool;
  private List<Tool> extraTools;

  public ToolKit() {
    tools = new ArrayList<>();
    extraTools = new ArrayList<>();
    selectedTool = null;
  }

  public final int addExtraTool(Tool tool) {
    if (tool != null) {
      extraTools.add(tool);
      return (extraTools.size() - 1);
    }
    return -1;
  }

  public final int addTool(Tool tool) {
    if (tool != null) {
      tools.add(tool);
      return (tools.size() - 1);
    }
    return -1;
  }

  public final int getToolCount() {
    return tools.size();
  }

  public final Tool getExtraTool(int i) {
    Tool res = null;
    if (i >= 0 && i < extraTools.size()) {
      res = extraTools.get(i);
    }
    return res;
  }

  public final Tool getTool(int i) {
    Tool res = null;
    if (i >= 0 && i < tools.size()) {
      res = tools.get(i);
    }
    return res;
  }

  public final Tool findTool(String name) {
    Tool res = null;
    if (name != null) {
      for (int i = 0; i < tools.size(); i++) {
        Tool toolX = tools.get(i);
        if (name.equals(toolX.getName())) {
          res = toolX;
          break;
        }
      }
      for (int i = 0; i < extraTools.size(); i++) {
        Tool toolX = extraTools.get(i);
        if (name.equals(toolX.getName())) {
          res = toolX;
          break;
        }
      }
    }
    return res;
  }

  public void setSelectedTool(int i) {
    Tool tool = getTool(i);
    if (tool != null) {
      selectedTool = tool;
    }
  }

  public Tool setSelectedTool(String name) {
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

  public int getExtraToolCount() {
    return extraTools.size();
  }
}
