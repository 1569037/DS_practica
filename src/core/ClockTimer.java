package core;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClockTimer extends Observable {
  private Timer timer;
  private LocalDateTime dateTime;
  private static ClockTimer instance = null;
  private static final int periode = 2; //seconds
  private static final Logger logger = LoggerFactory.getLogger("Milestone1.ClockTimer");

  private ClockTimer() {
    this.start();
  }

  public static ClockTimer getInstance() {
    logger.trace("Method ClcokTimer getInstance");
    if (instance == null) {
      instance = new ClockTimer();
    }
    return instance;
  }

  public void start() {
    timer = new Timer();
    TimerTask repeatedTask = new TimerTask() {
      public void run() {
        dateTime = LocalDateTime.now();
        setChanged();
        notifyObservers(dateTime);
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * periode);
  }

  public void stop() {
    logger.trace("Method ClockTimer stop");
    timer.cancel();
  }

  public LocalDateTime getDate() {
    return dateTime;
  }

  public static int getPeriode() {
    return periode;
  }
}
