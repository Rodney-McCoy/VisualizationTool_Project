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


    }

    @org.junit.jupiter.api.Test
    void dijkstra() {
    }

    @org.junit.jupiter.api.Test
    void bellmanFord() {
    }
}