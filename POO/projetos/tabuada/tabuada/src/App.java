import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class App {
    public static void main(String[] args) {
        Map<Integer,List<Integer>> todasAsTabuada = new HashMap<Integer,List<Integer>>();
    
        for(int i = 1;i<=10;i++){
            todasAsTabuada.put(i,tabuada(i));
        }
        for(int num : todasAsTabuada.keySet()){
            System.out.println("tabuada do "+num+": [");
            for(int valor : todasAsTabuada.get(num)){
                System.out.print(valor +", ");
            }
            System.out.println("]");
        }
    }
     public static List<Integer> tabuada(int num) {
        List<Integer>tabuada = new ArrayList<Integer>(10);
        
        for(int i = 1;i<=10;i++){
            tabuada.add(num*i);
        }
        return tabuada;
    }
    
}
