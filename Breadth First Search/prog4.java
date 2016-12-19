/** 
 * Eric Hernandez
 * Comp 282 Mon/Wed
 * Assignment #4
 * 5/5/15
 * Contained in this file is an 
 * implementation of breadth first search
 * and depth first search for a graph.
 */

import java.io.*; // for BufferedReader
import java.util.*; // for StringTokenizer

class Edge_Node {
    Vertex_Node target;
    Edge_Node next;

    public Edge_Node(Vertex_Node t, Edge_Node e) {
        target = t;
	next = e;
    }

    public Vertex_Node GetTarget() {
        return target;
    }


    public Edge_Node GetNext() {
        return next;
    }
}

class Vertex_Node {
    String name;
    Edge_Node edge_head;
    int distance;
    Vertex_Node next, parent;

    public Vertex_Node(String s, Vertex_Node v) {
        name = s;
	next = v;
        distance = -1;
    }

    public String GetName() {
        return name;
    }

    public int GetDistance() {
        return distance;
    }

    //Setter for distance, used by BFS and DFS
    public void SetDistance(int d) {
        distance = d;
    }

    public Edge_Node GetNbrList() {
        return edge_head;
    }
    
    public Vertex_Node GetNext() {
        return next;
    }
    
    //Getter for a vertex node's parent, used by DFS and BFS
    public Vertex_Node GetParent() {
        return parent;
    }
    
    //Setter for vertex node's parent, used by DFS and BFS
    public void SetParent(Vertex_Node p) {
        parent = p;
    }
}

class Graph {
    Vertex_Node head;
    int size;

    public Graph() {
        head = null;
        size = 0;
    }
    
    public void clearDist() {
        Vertex_Node pt = head;
        while (pt != null) {
            pt.distance = -1;
            pt = pt.next;
        }
    }

    public Vertex_Node findVertex(String s) {
        Vertex_Node pt = head;
        while (pt != null && s.compareTo(pt.name) != 0)
            pt = pt.next;
        return pt;
    }

    public Vertex_Node input(String fileName) throws IOException {
        String inputLine, sourceName, targetName;
        Vertex_Node source = null, target;
        Edge_Node e;
        StringTokenizer input;
        BufferedReader inFile = new BufferedReader(new FileReader(fileName));
        inputLine = inFile.readLine();
        while (inputLine != null) {
            input = new StringTokenizer(inputLine);
            sourceName = input.nextToken();
            source = findVertex(sourceName);
            if (source == null) {
                head = new Vertex_Node(sourceName, head);
                source = head;
                size++;
            }
            if (input.hasMoreTokens()) {
                targetName = input.nextToken();
                target = findVertex(targetName);
                if (target == null) {
                    head = new Vertex_Node(targetName, head);
                    target = head;
                    size++;
                }
                
                // put edge in one direction -- after checking for repeat
                e = source.edge_head;
                while (e != null) {
                    if (e.target == target) {
                        System.out.print("Multiple edges from " + source.name
                                + " to ");
                        System.out.println(target.name + ".");
                        break;
                    }
                    e = e.next;
                }
                source.edge_head = new Edge_Node(target, source.edge_head);
                
                // put edge in the other direction
                e = target.edge_head;
                while (e != null) {
                    if (e.target == source) {
                        System.out.print("Multiple edges from " + target.name
                                + " to ");
                        System.out.println(source.name + ".");
                        break;
                    }
                    e = e.next;
                }
                target.edge_head = new Edge_Node(source, target.edge_head);
            }
            inputLine = inFile.readLine();
        }
        inFile.close();
        return source;
    }

    public void output() {
        Vertex_Node v = head;
        Edge_Node e;
        while (v != null) {
            System.out.print(v.name + ":  ");
            e = v.edge_head;
            while (e != null) {
                System.out.print(e.target.name + "  ");
                e = e.next;
            }
            System.out.println();
            v = v.next;
        }
    }

    //Driver for BFS
    public void output_bfs(Vertex_Node s) {
        
        //Initial clearing of vertex distances (all distances set to -1)
        clearDist();
        
        //First do BFS with starting vertex passed in 
        output_bfs2(s);
        
        //If the graph is disconnected, go through vertex list
        //and do BFS on nodes that have not been visited yet.
        //ie nodes with distances less than 0
        Vertex_Node pt = head;
        while (pt != null) {
            if (pt.GetDistance() < 0) {
                output_bfs2(pt);
            }
            pt = pt.GetNext();
        }
    }
    
    //Iterative method for BFS
    private static void output_bfs2(Vertex_Node s) {
        
        //Initial steps: "visit" starting vertex (set parent and distance),
        //then add to queue to start BFS. 
        s.SetParent(s.GetParent());
        s.SetDistance(0);
        Queue<Vertex_Node> q = new LinkedList<Vertex_Node>();
        q.add(s);
        System.out.println(s.GetName() + ", " + s.GetDistance() + ", null");
        
        //While the queue is not empty, dequeue head of queue, enqueue its 
        //successors, and set successors distance and parent
        while (!q.isEmpty()) {
            Vertex_Node pred = q.remove();
            for (Edge_Node e = pred.GetNbrList(); e != null; e = e.GetNext()){ 
                if (e.GetTarget().GetDistance() < 0) {
                    e.GetTarget().SetParent(pred);
                    
                    //distance is always the distance of 
                    //the parent node + 1
                    e.GetTarget().SetDistance(pred.GetDistance() + 1);
                    q.add(e.GetTarget());
                    System.out.println(e.GetTarget().GetName() + ", " + 
                    e.GetTarget().GetDistance() + ", " + 
                    e.GetTarget().GetParent().GetName());
                }
            }
        }
    }

    //Driver for DFS
    public void output_dfs(Vertex_Node s) {
        
        //Initial clearing of distances (all distances set to -1)
        clearDist();
        
        //First do DFS with starting vertex passed in & null as parent
        output_dfs2(s, s.GetParent());
        
        //If the graph is disconnected, go through vertex list
        //and do DFS on nodes that have not been visited yet.
        //ie nodes with distances less than 0
        Vertex_Node pt = head;
        while (pt != null) {
            if (pt.GetDistance() < 0) {
                output_dfs2(pt, pt.GetParent());
            }
            pt = pt.GetNext();
        }
    }
    
    //Recursive method for DFS. Extra parameter for parent node.
    private static void output_dfs2(Vertex_Node s, Vertex_Node p) {
        
        //Set parent and distance for node passed in
        s.SetParent(p);
        if (p == null) {
            s.SetDistance(0);
        }
        else {
            s.SetDistance(p.GetDistance() + 1);
        }
        
        //Print out node's data
        if (s.GetParent() == null) {
            System.out.println(s.GetName() + ", " + 
            s.GetDistance() + ", null");
        }
        else {
            System.out.println(s.GetName() + ", " + 
            s.GetDistance() + ", " + s.GetParent().GetName());
        }
        
        //Go through node's edge list and make recursive calls
        //on nodes that have not been visited yet.
        //Backtracks when node has no edge list or all edges
        //have been visited.
        for (Edge_Node e = s.GetNbrList(); e != null; e = e.GetNext()) {
            if (e.GetTarget().GetDistance() < 0) {
                output_dfs2(e.GetTarget(), s);
            }
        }
    }
}

