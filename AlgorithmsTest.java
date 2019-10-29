import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    Graph graph = new Graph();

    @org.junit.jupiter.api.BeforeEach
    void setUp(){

        try {
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
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addNode("5");
        graph.addNode("6");

        graph.addLink(5,"1", "2");
        graph.addLink(4,"2", "4");
        graph.addLink(3,"4", "6");
        graph.addLink(3,"1", "3");
        graph.addLink(4,"3", "5");
        graph.addLink(6,"5", "6");
        graph.addLink(10,"1", "6");

        }catch (Exception e){
            System.out.println(e);
        }
    }
    //sets the two vectors used in the test to null
    @org.junit.jupiter.api.AfterEach
    void tearDown(){

        try {

            graph.removeNode("1");
            graph.removeNode("2");
            graph.removeNode("3");
            graph.removeNode("4");
            graph.removeNode("5");
            graph.removeNode("6");

            graph.removeLink("1", "2");
            graph.removeLink("2", "4");
            graph.removeLink("4", "6");
            graph.removeLink("1", "3");
            graph.removeLink("3", "5");
            graph.removeLink("5", "6");
            graph.removeLink("1", "6");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void floydWarshall() {

        boolean testPassed = true;

        //adjacency matrx created by our Algorithm
        double matrix[][] = Algorithms.FloydWarshall(graph);

        //solution adjacency matrix
        double solution[][] =
                {{6,5,3,9,7,10},
                {5,8,8,4,12,7},
                {3,8,6,12,4,10},
                {9,4,12,6,9,3},
                {7,12,4,9,8,6},
                {10,7,10,3,6,6}};

            for (int i = 0; i < graph.getNumNodes(); ++ i){
                for (int j = 0; j < graph.getNumNodes(); ++ j){

                    //if the two are not equal set testPassed to false
                    if (matrix[i][j] != solution[i][j]){
                            testPassed = false;
                    }
                }
            }
            assert(testPassed);
    }

    @org.junit.jupiter.api.Test
    void dijkstra() throws Exception {

        boolean testPassed;

        Graph solution = new Graph();

        solution.setNodes(graph.getNodes());

        try{
            solution.addLink(graph.getLink("1","2"));
            solution.addLink(graph.getLink("2","4"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        Graph result = Algorithms.dijkstra(graph,0,3);



    }

    @org.junit.jupiter.api.Test
    void bellmanFord() {

        boolean testPass = true;

        try {
            //setups up the correct solution based off of graph
            Graph solutionGraph = new Graph();
            solutionGraph.addNode("1");
            solutionGraph.addNode("2");
            solutionGraph.addNode("3");
            solutionGraph.addNode("4");
            solutionGraph.addNode("5");
            solutionGraph.addNode("6");

            solutionGraph.addLink(5,"1", "2");
            solutionGraph.addLink(3,"1", "3");
            solutionGraph.addLink(4,"2", "4");
            solutionGraph.addLink(4,"3", "5");
            solutionGraph.addLink(10,"1", "6");

            //create the graph using the algorithm
            Graph testGraph = Algorithms.bellmanFord(graph, graph.getNode("1"));

            //print both graphs to manually check they are equal
           // System.out.println(testGraph.toString());
           // System.out.println(solutionGraph.toString());

            //Links contain a value and the two nodes it links therefor if all the links are equal the graphs are equal
            ArrayList<Link> testLinks = testGraph.getLinks();
            ArrayList<Link> solutionLinks = solutionGraph.getLinks();

            //5 is numNodes - 1
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

        } catch (Exception e){
            System.out.println(e);
        }
    }
}