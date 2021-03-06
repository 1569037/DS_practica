package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import visitor.Json;
import visitor.PrintTree;
import visitor.Printer;
import visitor.TagSearcher;

import java.util.ArrayList;
import java.util.List;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger("Main");

  public static void main(String[] args) {
    logger.trace("Main");
    //Cargamos el arbol desde el fichero json
    File fi = new File("./tree.json");
    Project root = fi.readJson();
    //Cuando no exista información del árbol pasaremos el test de apéndice B
    if (root == null) {
      simpleTest();
    } else { //En caso contarario cargamos i mostramos toda la información del árbol
      testRead(root);
    }
    System.exit(0);
  }

  private static void simpleTest() {
    logger.trace("SimpleTest");
    try {
      Project root = new Project("root", null);

      Project sd = new Project("Software Design", root, new ArrayList<String>(List.of("java", "flutter")));
      Project st = new Project("Software Testing", root, new ArrayList<String>(List.of("c++", "Java", "python")));
      Project db = new Project("Databases", root, new ArrayList<String>(List.of("SQL", "python", "C++")));
      Task tt = new Task("Transportation", root);
      Project pp = new Project("Problems", sd);
      Project ptr = new Project("Project Time Tracker", sd);
      Task tfl = new Task("First list", pp, new ArrayList<String>(List.of("java")));
      Task tsl = new Task("Second list", pp, new ArrayList<String>(List.of("Dart")));
      Task rh = new Task("Read handout", ptr);
      Task fm = new Task("First milestone", ptr, new ArrayList<String>(List.of("Java", "IntelliJ")));

      Printer printer = new Printer(root);
      TagSearcher tg = new TagSearcher(root);
      String tag1 = "Java";
      tg.searchTask(tag1);
      String tag2 = "python";
      tg.searchTask(tag2);
      String tag3 = "Dart";
      tg.searchTask(tag3);
      String tag4 = "java";
      tg.searchTask(tag4);
      String tag5 = "c++";
      tg.searchTask(tag5);

      logger.warn("Start test");
      //1. start task transportation, wait 4 seconds and then stop it
      tt.startWorking();
      Thread.sleep(4000);
      tt.stopWorking();
      //2. wait 2 seconds
      Thread.sleep(2000);
      //3. start task first list, wait 6 seconds
      tfl.startWorking();
      Thread.sleep(6000);
      //4. start task second list and wait 4 seconds
      tsl.startWorking();
      Thread.sleep(4000);
      //5. stop first list
      tfl.stopWorking();
      //6. wait 2 seconds and then stop second list
      Thread.sleep(2000);
      tsl.stopWorking();
      //7. wait 2 seconds
      Thread.sleep(2000);
      //8. start transportation, wait 4 seconds and then stop it
      tt.startWorking();
      Thread.sleep(4000);
      tt.stopWorking();

      //Save tree in a json file
      Json json = new Json(root, "./tree.json");
      json.saveRoot(root);

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

  }

  private static void testRead(Project root) {
    logger.trace("testRead");
    PrintTree printer = new PrintTree(root);
  }
}
