import java.io.Console;
import java.util.*;

class Main {
  public static void printResult(Set<Notebook> notebooks) {
    System.out.println("\n" + ConsoleColors.CYAN_BOLD + "Результаты поиска:" +
        ConsoleColors.RESET + "\n" +
        "Найдено позиций: " + notebooks.size());
    for (Notebook notebook : notebooks) {
      System.out.println("\n" + notebook);
    }
  }

  public static void main(String[] args) {
    Set<Notebook> notebooks = new HashSet<>(Arrays.asList(Data.list));
    Map<String, List<String>> filters = new HashMap<>();

    Scanner scanner = new Scanner(System.in);
    Settings.printMenu();

    while (true) {
      int choice = scanner.nextInt();
      if (choice == Settings.filterItems.length + 1) {
        break;
      }

      if (!filters.containsKey(String.valueOf(choice))) {
        filters.put(String.valueOf(choice), new ArrayList<>());
        Settings.askCriteria(choice);
        String value = scanner.next();
        filters.get(String.valueOf(choice)).add(value);
      } else {
        System.out.println(ConsoleColors.RED_BOLD + "Этот параметр уже введён!" + ConsoleColors.RESET);
      }
    }

    printResult(filterNotebooks(notebooks, filters));
  }

  public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks,
      Map<String, List<String>> filters) {
    Set<Notebook> filteredNotebooks = new HashSet<>();
    for (Notebook notebook : notebooks) {
      boolean passFilter = true;
      for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
        for (String value : entry.getValue()) {
          passFilter = notebook.checkCriteria(Integer.parseInt(entry.getKey()), value);
        }
      }
      if (passFilter) {
        filteredNotebooks.add(notebook);
      }
    }
    return filteredNotebooks;
  }
}
