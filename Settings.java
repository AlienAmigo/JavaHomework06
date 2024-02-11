import java.util.*;

public class Settings {

  static class FilterItem {
    String title;
    String field;
    Boolean minCriteria;

    FilterItem(String title, String field, Boolean minCriteria) {
      this.title = title;
      this.field = field;
      this.minCriteria = minCriteria;
    }
  };

  public static FilterItem[] filterItems = {
      new FilterItem("ОЗУ", "ram", true),
      new FilterItem("Объем ЖД", "hd", true),
      new FilterItem("Операционная система", "os", false),
      new FilterItem("Цвет", "color", false)
  };

  public static String createMenu() {
    String rez = "";
    for (int i = 0; i < filterItems.length; i++) {
      rez += ConsoleColors.YELLOW_BOLD + (i + 1) + ConsoleColors.RESET + " — " + filterItems[i].title;
      rez += "\n";
    }
    rez += ConsoleColors.YELLOW_BOLD + (filterItems.length + 1) + ConsoleColors.RESET + " — " + "Поиск\n";
    return rez;
  }

  public static void askCriteria(int value) {
    System.out
        .print((value <= filterItems.length && filterItems[value - 1] != null && filterItems[value - 1].minCriteria)
            ? filterItems[value - 1].title + " — мин. значение: "
            : filterItems[value - 1].title + ": ");
  }

  public static void printMenu() {
    System.out.println("\n" + ConsoleColors.CYAN_BOLD + "Введите цифру, соответствующую необходимому критерию:" +
        ConsoleColors.RESET + "\n" +
        createMenu());
  }
}
