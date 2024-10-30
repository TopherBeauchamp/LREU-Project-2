import java.util.Random;
import java.util.List;

public class DesignateRendezvous {
    private static Random rand = new Random(); 


    public static void designate(List<Node> component){
        if(component.size() == 1){
            component.get(0).designateNode(); 
        }
        else{ 
            int designatedIndex = rand.nextInt(component.size() -1);
            component.get(designatedIndex).designateNode();
        }
    } 
}
