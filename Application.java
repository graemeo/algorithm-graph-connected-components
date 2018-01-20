import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Application
{
   /*
   Graph (Undirected)

   0 -> 1, 2
   1 -> 0, 4, 5
   2 -> 0, 3
   3 -> 2
   4 -> 1
   5 -> 1
   6 -> 7, 8
   7 -> 6, 8
   8 -> 6, 7

   0 -- 1 -- 5       6 -- 8
   |      \          |   /
   2 -- 3   -- 4     7 -- 

   */

    public static void main(String[] args) {
        List<List<Integer>> adjacencyList = getAdjacencyList();
        printAdjacencyList(adjacencyList);
        
        boolean[] unexplored = new boolean[adjacencyList.size()];
        int numberOfComponents = 0;

        for(int i=0; i < unexplored.length; i++) {
            if (!unexplored[i]) {
                numberOfComponents++;
                findConnectedComponentsByBreathFirstSearch(adjacencyList, unexplored, i);
            }
        } 
        System.out.println(numberOfComponents);
    }

    public static void findConnectedComponentsByBreathFirstSearch(List<List<Integer>> adjacencyList, boolean[] unexplored, int start) {
        boolean[] visited = new boolean[adjacencyList.size()];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visited[start] = true;
        unexplored[start] = true;

        while(!queue.isEmpty()) {
            Integer current = queue.remove();
            for(Integer vertex : adjacencyList.get(current)) {
                if (!visited[vertex]) {
                    queue.add(vertex);
                }
                visited[vertex] = true;
                unexplored[vertex] = true;
            }
        } 
           
    }

    public static  List<List<Integer>> getAdjacencyList() {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        adjacencyList.add(Arrays.asList(1, 2));
        adjacencyList.add(Arrays.asList(0, 4, 5));
        adjacencyList.add(Arrays.asList(0, 3));
        adjacencyList.add(Arrays.asList(2));
        adjacencyList.add(Arrays.asList(1));
        adjacencyList.add(Arrays.asList(1));
        adjacencyList.add(Arrays.asList(7, 8));
        adjacencyList.add(Arrays.asList(6, 8));
        adjacencyList.add(Arrays.asList(6, 7));

        return adjacencyList;
    }

    public static void printAdjacencyList(List<List<Integer>> adjacencyList) {
        int index = 0;
        System.out.println("Adjacency List");
        for(List<Integer> adjacency : adjacencyList) {
            System.out.print(index + " -> ");
            for(Integer vertex : adjacency) {
                System.out.print(vertex + " ");
            }
            System.out.println("");
            index++;
        }

    }
}
