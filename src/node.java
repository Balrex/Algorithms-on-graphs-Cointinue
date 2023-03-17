import java.util.ArrayList;

public class node {
    boolean visited, inStak; // для Тарьяна

    int prev, next; // для Флёри

    ArrayList<Integer> ErgodicСlass = new ArrayList<>();
    public node(boolean visited, boolean inStak){
        this.inStak=inStak;
        this.visited=visited;
    }
    public node(int prev, int next){
        this.next=next;
        this.prev=prev;
    }
    public node(int peak){
        ErgodicСlass.add(peak);
    }
}
