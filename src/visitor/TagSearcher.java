package visitor;

import core.Interval;
import core.Item;
import core.Project;
import core.Task;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class TagSearcher implements Visitor {
  private final Project root;
  private static final Logger logger = LoggerFactory.getLogger("Milestone2.Visitor.TagSearcher");

  private String tag;
  protected ArrayList<Item> tagfound;

  public TagSearcher(Project root) {
    this.root = root;
    this.tagfound = new ArrayList<Item>();
  }

  public void searchTask(String tag) {
    logger.trace("Method searchTask");
    logger.warn("RESULTS FOR TASKS WITH TAG " + tag + ":");
    this.tag = tag;
    root.acceptVisitor(this);
  }

  @Override
  public void visitTask(Task t) {
    logger.trace("Method visitTask");
    ArrayList<String> tasktag = t.getTag();
    for (int j = 0; j < tasktag.size(); j++) {
      if (tasktag.get(j).toLowerCase().equals(this.tag.toLowerCase())) {
        tagfound.add(t);
        logger.info(t.getName());
        j = tasktag.size();
      }
    }
  }

  @Override
  public void visitInterval(Interval i) {

  }

  @Override
  public void visitProject(Project p) {
    logger.trace("Method visitProject");
    ArrayList<String> projecttag = p.getTag();
    for (int j = 0; j < projecttag.size(); j++) {
      if (projecttag.get(j).toLowerCase().equals(this.tag.toLowerCase())) {
        tagfound.add(p);
        String name = p.getName();
        logger.info(name);
      }
    }
    for (int i = 0; i < p.getItem().size(); i++) {
      p.getItem().get(i).acceptVisitor(this);
    }
  }
}
