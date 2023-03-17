import java.util.ArrayList;

public class TestGraphB {
    public static void main(String[] args) {
        int[][] RightA = {
                {0,1,1,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,1,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,1,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0}};
        int[][] WrongA = {
                {0,1,1,0,0},
                {0,0,0,0,1},
                {0,1,0,0,1},
                {0,1,0,0,0},
                {0,0,0,1,0}};
        GraphB test = new GraphB();
        int[] answer = test.Taryana(WrongA);
        if (answer!=null) {
            System.out.print("[");
            for (int i = 0; i < answer.length; i++)
                System.out.print(answer[i] + " ");
            System.out.println("]");
        }
        System.out.println("----------------------");
        answer = test.Taryana(RightA);
        if (answer!=null) {
            System.out.print("[");
            for (int i = 0; i < answer.length; i++)
                System.out.print(answer[i] + " ");
            System.out.println("]");
        }
        System.out.println("----------------------");
        RightA = new int[][]{
                {0,1,0,0,0,0},
                {0,0,1,0,0,1},
                {0,0,0,1,1,0},
                {1,0,0,0,0,1},
                {0,1,0,1,0,0},
                {0,0,1,0,1,0}};
        ArrayList<node> couples = test.Fleury(RightA, 0);
        if (couples!=null)
            for (int i=0; i<couples.size(); ++i)
                System.out.println(couples.get(i).prev+" -> "+couples.get(i).next);
        else
            System.out.println("В данном графе нет эйлерова цикла!");
        System.out.println("----------------------");
        RightA = new int[][]{
                {0,1,0,0,0,0},
                {0,0,1,0,0,1},
                {0,0,0,1,1,0},
                {1,0,0,0,0,1},
                {0,1,0,1,0,0},
                {0,0,1,0,1,0}};
        ArrayList<Integer> path = test.CyclesUnion(RightA, 0);
        if (path!=null) {
            for (int i = path.size()-1; i > -1; --i)
                System.out.print(path.get(i)+" -> ");
            System.out.println("END");
        }else
            System.out.println("В данном графе нет эйлерова цикла!");
        System.out.println("----------------------");
    }
}
