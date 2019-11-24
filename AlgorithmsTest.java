import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    Graph graph = new Graph();

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        //created a complicated graph to test the algorithms against.
         /*
       4 -----  6  ----  5
       |   3    |   6    |
       |        |        |
      4|        |10      |4
       |        |        |
       |   5    |   3    |
       2 -----  1 -----  3
     */
     try {
         graph.addNode("1");
         graph.addNode("2");
         graph.addNode("3");
         graph.addNode("4");
         graph.addNode("5");
         graph.addNode("6");
     }catch(Exception e){}

        graph.addLink(5,"1", "2");
        graph.addLink(4,"2", "4");
        graph.addLink(3,"4", "6");
        graph.addLink(3,"1", "3");
        graph.addLink(4,"3", "5");
        graph.addLink(6,"5", "6");
        graph.addLink(10,"1", "6");
    }

    @org.junit.jupiter.api.Test
    void floydWarshall() {

        boolean testPassed = true;

        //adjacency matrx created by our Algorithm
        double matrix[][] = Algorithms.FloydWarshall(graph);

        //solution adjacency matrix
        double solution[][] =
                {{0,5,3,9,7,10},
                {5,0,8,4,12,7},
                {3,8,0,12,4,10},
                {9,4,12,0,9,3},
                {7,12,4,9,0,6},
                {10,7,10,3,6,0}};

        if (matrix.length == solution.length && matrix[0].length == solution[0].length){
            for (int i = 0; i < graph.getNumNodes(); ++ i){
                for (int j = 0; j < graph.getNumNodes(); ++ j){

                    //if the two are not equal set testPassed to false
                    if (matrix[i][j] != solution[i][j]){
                            testPassed = false;
                    }
                }
            }
        }else {
            testPassed = false;
        }
        
        assert(testPassed);
        this.graph = null;
    }

    @org.junit.jupiter.api.Test
    void dijkstra() throws Exception {

        boolean testPass = true;

        Graph solution = new Graph();

        solution.setNodes(graph.getNodes());

        solution.addLink(graph.getLink("1","2"));
        solution.addLink(graph.getLink("2","4"));

        Graph result = Algorithms.dijkstra(graph,0,3);

        //Links contain a value and the two nodes it links therefor if all the links are equal the graphs are equal
        List<Link> testLinks = result.getLinks();
        List<Link> solutionLinks = solution.getLinks();

        for (int i = 0; i < graph.getNumNodes() - 1; ++i) {

            //checks if the values/weights of the links are the same
            if (testLinks.get(i).getValue() != solutionLinks.get(i).getValue()){
                testPass = false;
            }
            //checks if the links first node is the same for both graphs
            if (testLinks.get(i).getNode1() != solutionLinks.get(i).getNode1()){
                testPass = false;
                //this fixes the issue where a graph could have assigned the two nodes n1, n2 or n2, n1.
                if (testLinks.get(i).getNode2() != solutionLinks.get(i).getNode1()){
                    testPass= true;
                }
            }
            //repeat for the second node.
            if (testLinks.get(i).getNode2() != solutionLinks.get(i).getNode2()){
                testPass = false;
                if (testLinks.get(i).getNode2() != solutionLinks.get(i).getNode1()){
                    testPass= true;
                }
            }
        }

        assert(testPass);
        this.graph = null;
    }

    @org.junit.jupiter.api.Test
    void bellmanFord() {

        boolean testPass = true;

        //setups up the correct solution based off of graph
        Graph solutionGraph = new Graph();
        try {
            solutionGraph.addNode("1");
            solutionGraph.addNode("2");
            solutionGraph.addNode("3");
            solutionGraph.addNode("4");
            solutionGraph.addNode("5");
            solutionGraph.addNode("6");
        }catch(Exception e){}

        solutionGraph.addLink(5,"1", "2");
        solutionGraph.addLink(3,"1", "3");
        solutionGraph.addLink(4,"2", "4");
        solutionGraph.addLink(4,"3", "5");
        solutionGraph.addLink(10,"1", "6");

        //create the graph using the algorithm
        try {
            Graph testGraph = Algorithms.bellmanFord(graph, graph.getNode("1"));

        //Links contain a value and the two nodes it links therefor if all the links are equal the graphs are equal

        List<Link> testLinks = testGraph.getLinks();
        List<Link> solutionLinks = solutionGraph.getLinks();


        //Iterates through numNodes - 1
        for (int i = 0; i < graph.getNumNodes() - 1; ++i) {

            //checks if the values/weights of the links are the same
            if (testLinks.get(i).getValue() != solutionLinks.get(i).getValue()){
                testPass = false;
            }
            //checks if the links first node is the same for both graphs
            if (testLinks.get(i).getNode1() != solutionLinks.get(i).getNode1()){
                testPass = false;
                //this fixes the issue where a graph could have assigned the two nodes n1, n2 or n2, n1.
                if (testLinks.get(i).getNode2() != solutionLinks.get(i).getNode1()){
                    testPass= true;
                }
            }
            //repeat for the second node.
            if (testLinks.get(i).getNode2() != solutionLinks.get(i).getNode2()){
                testPass = false;
                if (testLinks.get(i).getNode2() != solutionLinks.get(i).getNode1()){
                    testPass= true;
                }
            }
        }
        }catch(Exception e){}
        assert(testPass);
        this.graph = null;
    }
}
