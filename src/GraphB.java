import java.util.ArrayList;
import java.util.Stack;

public class GraphB {
    private Stack<Integer> stack = new Stack<>();

    // алгоритм Тарьяна для топологической сортировки
    public int[] Taryana(int[][] orig){
        int[] answer = new int[orig[0].length];
        node[] stackVis = new node[answer.length];
        boolean marker=true;
        for (int i = 0; i < orig[0].length; ++i)
            stackVis[i] = new node(false, false);
        for (int i=0; i<answer.length; ++i)
            if (stackVis[i].visited==false) {
                marker = InDepth(i, orig, stackVis, marker);
            }
        if (marker) {
            for (int i = 0; i < answer.length; ++i)
                answer[i] = stack.pop();
        }
        else {
            System.out.println("Для данного графа не существует топологической сортировки");
            answer=null;
        }
        return answer;
    }
    private boolean InDepth(int peak, int[][] orig, node[] visited, boolean marker) {
        visited[peak].visited = true;
        for (int i = peak, j = 0; j < visited.length; ++j) {
            if (visited[j].visited == false && orig[i][j] != 0)
                marker=InDepth(j, orig, visited, marker);
           else if (visited[j].inStak != true && orig[i][j]!=0)
               marker = false;
        }
        if (marker) {
            stack.push(peak);
            visited[peak].inStak = true;
//            System.out.println(peak);
        }//else
//            System.out.println("--"+peak);
        return marker;
    }

    // алгоритм Флёри
    public ArrayList<node> Fleury(int[][] orig, int start){
        ArrayList<node> answer = new ArrayList<>();
        int [] checkVertical = new int[orig[0].length];
        int[] checkHorizontal = new int[orig[0].length];
        int[] findBridge = new int[orig[0].length];
        int count=0;                                // количество ребер
        for (int i=0; i<orig[0].length; ++i)
            for (int j = 0; j < orig[0].length; ++j)
                if (orig[i][j] != 0) {
                    count++;
                    ++checkHorizontal[i];
                    ++checkVertical[j];
                    ++findBridge[j];
                }
        for (int i=0; i<orig[0].length; ++i)
            if (checkVertical[i]!=checkHorizontal[i] && checkHorizontal[i]!=0 && checkVertical[i]!=0)
                return null;

        while (count>0){
            for (int i=start, j=0; j<orig[0].length; ++j)
                if (orig[i][j]!=0 && findBridge[j]>1){
                    orig[i][j]=0;
                    answer.add(new node(i, j));
                    start = j;
                    --checkHorizontal[i];
                    --count;
                    break;
                }else if (orig[i][j]!=0 && checkHorizontal[i]==1){
                    orig[i][j]=0;
                    answer.add(new node(i, j));
                    start = j;
                    --checkHorizontal[i];
                    --count;
                    break;
                }
        }
        return answer;
    }

    // алгоритм поиска эйлерова цикла на основе объединения циклов
    public ArrayList<Integer> CyclesUnion(int[][] orig, int start){
        ArrayList<Integer> answer = new ArrayList<>();
        int [] checkVertical = new int[orig[0].length];
        int[] checkHorizontal = new int[orig[0].length];
        for (int i=0; i<orig[0].length; ++i)
            for (int j = 0; j < orig[0].length; ++j)
                if (orig[i][j] != 0) {
                    ++checkHorizontal[i];
                    ++checkVertical[j];
                }
        for (int i=0; i<orig[0].length; ++i)
            if (checkVertical[i]!=checkHorizontal[i] && checkHorizontal[i]!=0 && checkVertical[i]!=0)
                return null;

        answer = FindEulerPath(orig, start, answer);
        return answer;
    }
    private ArrayList<Integer> FindEulerPath(int[][] orig, int i, ArrayList<Integer> answer){
        for (int j=0; j<orig[0].length; ++j)
            if (orig[i][j]!=0){
                orig[i][j] = 0;
                answer = FindEulerPath(orig, j, answer);
            }
        answer.add(i);
        return answer;
    }

    //  алгоритм Косарайю
    public ArrayList<node> Kosaraju(int[][] orig){
        ArrayList<node> answer = new ArrayList<>();

        for (int i=0; i<orig[0].length-1; ++i)
            for (int j=i+1; j<orig[0].length; ++j){
                int tmp = orig[i][j];
                orig[i][j]=orig[j][i];
                orig[j][i]=tmp;
            }

        boolean[] visited = new boolean[orig[0].length];
        for (int i=0; i<visited.length; ++i)
            visited[i]=false;
        stack.push(-5);
        //System.out.println("*******************");
        for (int i=0; i<visited.length; ++i)
            if (!visited[i])
                dfs_inv(orig, i, visited);
        //System.out.println("*******************");

        for (int i=0; i<visited.length; ++i)
            visited[i]=false;
        while (stack.peek()!=-5){
            int tmp = stack.pop();
            if (visited[tmp] == false) {
                //System.out.println("/-|| "+ tmp);
                answer.add(dfs(orig, tmp, visited, new node()));
                //System.out.println("+++++++++++++++++++");
            }
        }
        stack.pop();
        return answer;
    }
    private void dfs_inv(int[][] orig, int peak, boolean[] visited){
        visited[peak]=true;
        for (int i=peak, j=0; j<orig[0].length; ++j)
            if (orig[i][j]!=0 && visited[j] == false)
                dfs_inv(orig, j, visited);
        //System.out.println(peak);
        stack.push(peak);
    }
    private node dfs(int[][] orig, int peak, boolean[] visited, node PartOfAnswer){
        visited[peak] = true;
        PartOfAnswer.ErgodicСlass.add(peak);
        //System.out.println(peak);
        for (int j = peak, i = 0; i < visited.length; ++i)
            if (visited[i]==false && orig[i][j] != 0)
                PartOfAnswer = dfs(orig, i, visited, PartOfAnswer);
        return PartOfAnswer;
    }
}
