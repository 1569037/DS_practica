import com.google.gson.Gson;
import org.json.JSONObject;


public class Main {

    public static void main(String[] args)  {
        simpleTest();
    }
    private static void simpleTest()
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {


                try
                {
                    Project SD = new Project("software design");
                    Project ST = new Project("software testing");
                    Project DB = new Project("databases");
                    Project TT = new Project("task transportation");
                    Project PP = new Project("projects problems)", SD);
                    Project PTR = new Project("project time tracker", SD);
                    Task TFL = new Task("task first list", PP);
                    Task TSL = new Task("task second list", PP);
                    Task RH = new Task("read handout", TT);
                    Task FM = new Task("first milestone", TT);


                    TFL.startWorking();
                    Thread.sleep(2000);
                    TSL.startWorking();
                    Thread.sleep(2000);
                    RH.startWorking();
                    Thread.sleep(4000);

                    TFL.stopWorking();
                    Thread.sleep(2000);
                    TSL.stopWorking();
                    Thread.sleep(2000);
                    RH.stopWorking();
                    Thread.sleep(4000);
                    
                    Gson gson = new Gson();
                    String JSON = gson.toJson(SD);
                    Task FM3 = new Task("first milestone", TT);
                }catch (Exception e){


                }
            }
        });
        /*
        */
        //instance clocktimer
        /*Project SD = new Project("software design");
        Project ST = new Project("software testing");
        Project DB = new Project("databases");
        Project TT = new Project("task transportation");
        Project PP = new Project("projects problems)", SD);
        Project PTR = new Project("project time tracker", SD);
        Task TFL = new Task("task first list", PP);
        Task TSL = new Task("task second list", PP);
        Task RH = new Task("read handout", TT);
        Task FM = new Task("first milestone", TT);
        t0.startWorking();
        System.out.println("Task in progress...");
        t0.stopWorking();
        t0.startWorking();
        t0.stopWorking();
        System.out.println("Task in progress...");


        System.out.println(t0.init);
        System.out.println(t0.end);
        System.out.println(t0.totalTime);
        //hay que revisar todo esto*/

    }
}
