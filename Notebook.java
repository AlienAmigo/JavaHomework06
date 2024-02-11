import java.util.*;
import java.lang.reflect.Field;

public class Notebook {
  String manufacture;
  String model;
  Double ram;
  Double hd;
  String os;
  String color;

  public Notebook(String manufacture, String model, double ram, double hd, String os, String color) {
    this.manufacture = manufacture;
    this.model = model;
    this.ram = ram;
    this.hd = hd;
    this.os = os;
    this.color = color;
  }

  static String printFeature(String title, String value) {
    return ("\n" + ConsoleColors.WHITE_BOLD + title + ConsoleColors.RESET + ": " + value);
  }

  @Override
  public String toString() {
    return (ConsoleColors.GREEN_BOLD + manufacture + " " + model + ConsoleColors.RESET
        + printFeature("RAM", Double.toString(ram)) + " GB"
        + printFeature("HDD", Double.toString(hd)) + " GB"
        + printFeature("OS", os)
        + printFeature("Color", color));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Notebook))
      return false;

    Notebook notebook = (Notebook) obj;

    return manufacture.equals(notebook.manufacture) && model.equals(notebook.model) && ram == notebook.ram
        && hd == notebook.hd && os.equals(notebook.os) &&
        color.equals(notebook.color);
  }

  @Override
  public int hashCode() {
    return manufacture.hashCode() + model.hashCode() + ram.intValue() + hd.intValue() + 7 * os.hashCode()
        + 12 * color.hashCode();
  }

  public Boolean checkCriteria(int filterIndex, String value) {
    try {
      String fieldName = Settings.filterItems[filterIndex -
          1].field;
      Field field = this.getClass()
          .getDeclaredField(fieldName);
      var fieldValue = field.get(this);
      return Settings.filterItems[filterIndex - 1].minCriteria
          ? Double.parseDouble(fieldValue.toString()) > Double.parseDouble(value)
          : fieldValue.toString().equalsIgnoreCase(value);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
      return false;
    }
  }
}
