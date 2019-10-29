import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    Graph graph = new Graph();

    @org.junit.jupiter.api.BeforeEach
    void setUp(){

        try {
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
            System.out.println(e);
        }
    }

    @org.junit.jupiter.api.Test
    void floydWarshall() {

        boolean testPassed = true;

        double matrix[][] = Algorithms.FloydWarshall(graph);

        double solution[][] =
                {{6,5,3,9,7,10},
                {5,8,8,4,12,7},
                {3,8,6,12,4,10},
                {9,4,12,6,9,3},
                {7,12,4,9,8,6},
                {10,7,10,3,6,6}};

            for (int i = 0; i < 6; ++ i){

                for (int j = 0; j < 6; ++ j){

                    if (matrix[i][j] != solution[i][j]){
                            testPassed = false;
                    }


                }
            }

            assert(testPassed);
            //assertEquals(testPassed);


    }

    @org.junit.jupiter.api.Test
    void dijkstra() {
    }

    @org.junit.jupiter.api.Test
    void bellmanFord() {
    }
}