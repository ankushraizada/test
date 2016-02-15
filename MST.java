package ImplementationBalaji.spPQ;

import java.util.Scanner;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MST<T> {
    static final int infinity = Integer.MAX_VALUE;
    
    static int PrimMST1(Graph g)
    {
        int wmst = 0;
        Vertex src = g.verts.get(1);
        
        //Code for Prim's algorithm
        //Pre processing the graph
        for(Vertex u: g){
            u.seen=false;
            u.parent=null;
        }
        src.seen=true;
        wmst=0;
        
        //adding all the edges of the src to the PQ
//        PriorityQueue<Edge> pqPrim1 = new PriorityQueue<Edge>(10, new Comparator<Edge>() {
//        public int compare(Edge edge1, Edge edge2) {
//            if(edge1.Weight > edge2.Weight) return 1;
//            else if(edge1.Weight < edge2.Weight) return -1;
//            else return 0;
//        }
//    });
        Object[] pq1 = new Object[g.verts.size()];

        
//        for(Edge e: src.Adj)
//            pqPrim1.add(e);

        int i=0;
        for(Edge e: src.Adj){
            pq1[i]=e;
                    i++;
        }
        
        BinaryHeap pqPrim1 = new BinaryHeap(pq1);
                    
        Vertex u, v, w;
        
//        while(!pqPrim1.isEmpty()){
//            Edge e=(Edge)pqPrim1.remove();
//            if(e.From.seen && e.To.seen) continue;
//            
//            if(e.From.seen && !e.To.seen) e.To.parent=e.From;
//           
//            wmst+=e.Weight;
//            e.To.seen=true;
//            
//            for(Edge f: e.To.Adj){
//                if(!(f.From.seen && f.To.seen))
//                    pqPrim1.add(f);
//                
//                w=f.otherEnd(e.To);
//                if(! w.seen)
//                pqPrim1.add(f); 
//            }
//        }
        int prim1Counter=0;
        while(prim1Counter<pqPrim1.pq.length){
            Edge e=(Edge)pqPrim1.pq[prim1Counter];
            if(e.From.seen && e.To.seen) continue;
            
            if(e.From.seen && !e.To.seen) e.To.parent=e.From;
           
            wmst+=e.Weight;
            e.To.seen=true;
            
            for(Edge f: e.To.Adj){
                if(!(f.From.seen && f.To.seen))
                    pqPrim1.add(f);
                
                w=f.otherEnd(e.To);
                if(! w.seen)
                pqPrim1.add(f); 
            }
            prim1Counter++;
        }        
        return wmst;
    }

    static int PrimMST2(Graph g)
    {
        int wmst = 0;
        Vertex src = g.verts.get(1);
        
        // Code for Prim's algorithm
        //Pre processing the graph
        for(Vertex u: g){
            u.seen=false;
            u.parent=null;
            u.distance=infinity;
        }
        src.distance=0;
        wmst=0;
        
        //adding all the vertices of the src to the PQ
//        PriorityQueue<Vertex> pqPrim2 = new PriorityQueue<Vertex>(10, new Comparator<Vertex>() {
//        public int compare(Vertex vertex1, Vertex vertex2) {
//            if(vertex1.distance > vertex2.distance) return 1;
//            else if(vertex1.distance < vertex2.distance) return -1;
//            else return 0;
//        }
//    });
        
        Object[] pqPrim2 = new Object[g.verts.size()];
        
        int j=0;
        for(Vertex u: g){
            pqPrim2[j]=u;
            j++;
        }
            
        //IndexedHeap binPrim2 = new IndexedHeap(pqPrim2);
        Vertex u = null, v, w;
        
        while(pqPrim2.length!=0){
          //  u=(Vertex)binPrim2.remove();
            u.seen = true;
            wmst+=u.distance;
            
            for(Edge e: u.Adj){
                v = e.otherEnd(u);
                if(!v.seen && e.Weight<v.distance)
                    v.distance=e.Weight;
                    v.parent=u;
            //        binPrim2.percolateUp();
            }
        }
        
        return wmst;
    }
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        
        Integer[] a= new Integer[]{0,4,2,3,1,5,21,23,7,17,33,29};
        
        //starting timer before prim1
        long startTime1 = System.currentTimeMillis();
        System.out.println("The prim's lenght or weight here is:"+PrimMST1(g));
        long endTime1   = System.currentTimeMillis();
        //end time for prim1
        long totalTime1 = endTime1 - startTime1;
        System.out.println("Total time in ms for Prim1 is: "+totalTime1);

        System.out.println();
        long startTime2 = System.currentTimeMillis();
        //System.out.println("The prim's lenght or weight here is:"+PrimMST2(g));
        long endTime2   = System.currentTimeMillis();
        //end time for prim2
        long totalTime2 = endTime2 - startTime2;
        System.out.println("Total time in ms for Prim2 is: "+totalTime2);
        
        
        System.out.println();
        long startTime3 = System.currentTimeMillis();
        //BinaryHeap bin = new BinaryHeap(a);
        long endTime3   = System.currentTimeMillis();
        //end time for binary heap
        long totalTime3 = endTime3 - startTime3;
        System.out.println("Total time in ms for Prim2 is: "+totalTime3);
        
    }
}
