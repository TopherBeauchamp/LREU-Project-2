import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;

//Adjacency list class from https://www.algotree.org/algorithms/adjacency_list/graph_as_adjacency_list_java/

public class ListGraph implements Graph{

    private int numVertices;
    private List<List<Integer>> adjList;
    private int totalEnergy; 

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


    public List<List<Node>> bfs(){ 
        List<List<Node>> components = new ArrayList<>(); 
        boolean[] visited = new boolean[numVertices]; 

        for(int startNode = 0; startNode < numVertices; startNode++){ 
            if(!visited[startNode]){ 
                List<Node> component = new ArrayList<>(); 
                Queue<Integer> queue = new LinkedList<>(); 

                queue.offer(startNode); 
                visited[startNode] = true; 

                while(!queue.isEmpty()){
                    int currentNode = queue.poll();
                    component.add(NodeRegistry.getNodeById(currentNode +1));

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

    public List<List<Node>> dfs() { 
        List<List<Node>> components = new ArrayList<>(); 
        boolean[] visited = new boolean[numVertices];

        for(int startNode = 0; startNode < numVertices; startNode++){
            if(!visited[startNode]){
                List<Node> component = new ArrayList<>(); 
                Stack<Integer> stack = new Stack<>();

                stack.push(startNode); 
                visited[startNode] = true;

                while(!stack.isEmpty()){
                    int currentNode = stack.pop();
                    component.add(NodeRegistry.getNodeById(currentNode +1));

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

    public double dijkstraShortestPath(List<Node> component){ 
        
    }
} 