import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Task extends Item{

  private ArrayList <Interval> interval;

  public Task(String name, Project father)
  {
    super(name);
    this.father = father;
    this.interval = new ArrayList();
    this.father.addTask(this);
  }

  protected void startWorking()
  {
    if (!active) {
      Interval i = new Interval(this);
      active = true;
      father.setActive(true);
      interval.add(i);
      System.out.println(this.name + " starts");
    }
   }
  public void stopWorking()
  {
    active = false;
    father.setActive(false);
    interval.get(interval.size()-1).stopInterval();
    this.updateTotalTime(interval.get(interval.size()-1).getInterval());
    System.out.println(this.name + " stops");
  }

  @Override
  protected Duration getDurationBetween(LocalDateTime ini, LocalDateTime end)
  {
    Duration duration = Duration.ZERO;
    boolean cond = false;
    for (int i = 0; i < interval.size(); i++){
      cond = (interval.get(i).getInitTime().isAfter(ini) || interval.get(i).getInitTime().isEqual(ini)) &&
          (interval.get(i).getEndTime().isBefore(end) || interval.get(i).getEndTime().isEqual(end));

      if (cond)
          duration = duration.plus(interval.get(i).getInterval());
    }
    return duration;
  }

  @Override
  public Duration getTotalTime()
  {
    if(!active)
      return this.totalTime;
    else
    {
      Duration d = interval.get(interval.size()-1).getInterval();
      return this.totalTime.plus(d);
     }
  }

  @Override
  public void acceptVisitor(Visitor v){
    v.visitTask(this);
  }

  public Interval getLastInterval(){
    return interval.get(interval.size()-1);
  }
}
