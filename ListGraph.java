import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;

//Adjacency list class from https://www.algotree.org/algorithms/adjacency_list/graph_as_adjacency_list_java/

public class ListGraph implements Graph{

    int numVertices;
    List<List<Integer>> adjList;

    ListGraph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i=0; i<numVertices; i++)
            adjList.add(new ArrayList<>());
    }

    public void addEdge(int sourceNode, int connectedNode) {
        adjList.get(sourceNode-1).add(connectedNode-1);
        adjList.get(connectedNode-1).add(sourceNode-1);
    }


    public List<List<Integer>> bfs(){ 
        List<List<Integer>> components = new ArrayList<>(); 
        boolean[] visited = new boolean[numVertices]; 

        for(int startNode = 0; startNode < numVertices; startNode++){ 
            if(!visited[startNode]){ 
                List<Integer> component = new ArrayList<>(); 
                Queue<Integer> queue = new LinkedList<>(); 

                queue.offer(startNode); 
                visited[startNode] = true; 

                while(!queue.isEmpty()){
                    int currentNode = queue.poll();
                    component.add(currentNode +1);

                    for(Integer neighbor : adjList.get(currentNode)){ 
                        if(!visited[neighbor]){
                            queue.offer(neighbor);
                            visited[neighbor] = true; 
                        }

                    }
                }
                components.add(component);
            }
        }
        return components;
    }

    public List<List<Integer>> dfs() { 
        List<List<Integer>> components = new ArrayList<>(); 
        boolean[] visited = new boolean[numVertices];

        for(int startNode = 0; startNode < numVertices; startNode++){
            if(!visited[startNode]){
                List<Integer> component = new ArrayList<>(); 
                Stack<Integer> stack = new Stack<>();

                stack.push(startNode); 
                visited[startNode] = true;

                while(!stack.isEmpty()){
                    int currentNode = stack.pop();
                    component.add(currentNode+1);

                    for(Integer neighbor : adjList.get(currentNode)){
                        if(!visited[neighbor]){
                            stack.push(neighbor);
                            visited[neighbor] = true; 
                        }
                    }
                }
                components.add(component);
            }
        }
        return components;
    }

} 