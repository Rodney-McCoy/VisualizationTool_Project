package VisualizationTool_Project;

import java.util.Scanner;

public class Controller {
    public static int checkMainMenuInput(Scanner in) throws Exception {
        int input;
        if(in.hasNextInt()){
            input = in.nextInt();
            if(input < 1 || input > 10){
                in.close();
                throw new Exception("Incorrect syntax for input, expecting number between 1-10");
            }
            in.close();
            return input;
        }else{
            in.close();
            throw new Exception("Incorrect syntax for input, expecting a whole number");
        }
    }

    public static void makeNode(Scanner in) throws Exception {
        String name;
        double value;
        if(in.hasNextDouble()){
            in.close();
            throw new Exception("Incorrect syntax for Node's name");
        }else if(in.hasNext()){
            name = in.next();
            in.close();
            Model.addNode(name);
        }else{
            in.close();
            throw new Exception("Incorrect syntax for Node's name");
        }
    }

    public static void makeLink(Scanner in) throws Exception {
        double value;
        int id1;
        int id2;
        String node1;
        String node2;
        if(in.hasNextDouble()){
            value = in.nextDouble();
            if(in.hasNextInt()){
                id1 = in.nextInt();
                if(in.hasNextInt()){
                    id2 = in.nextInt();
                    in.close();
                    Model.addLink(value,id1,id2);
                }else{
                    in.close();
                    throw new Exception("Incorrect syntax for Node two's id");
                }
            }else if(in.hasNext()) {
                node1 = in.next();
                if(in.hasNext()){
                    node2 = in.next();
                    in.close();
                    Model.addLink(value,node1,node2);
                }else{
                    in.close();
                    throw new Exception("Incorrect syntax for Node two's name");
                }
            }else {
                in.close();
                throw new Exception("Incorrect syntax for Node one's name or id");
            }
        }else{
            in.close();
            throw new Exception("Incorrect syntax for Link's value");
        }
    }

    public static void removeNode(Scanner in) throws Exception {
        int id;
        String name;
        if(in.hasNextInt()){
            id = in.nextInt();
            in.close();
            Model.removeNode(id);
        }else if(in.hasNext()){
            name = in.next();
            in.close();
            Model.removeNode(name);
        }else{
            in.close();
            throw new Exception("Incorrect syntax for Node's name or id");
        }
    }

    public static void removeLink(Scanner in) throws Exception {
        int id1;
        int id2;
        String node1;
        String node2;
        if(in.hasNextInt()){
            id1 = in.nextInt();
            if(in.hasNextInt()){
                id2 = in.nextInt();
                in.close();
                Model.removeLink(id1,id2);
            }else{
                in.close();
                throw new Exception("Incorrect syntax for Node two's id");
            }
        }else if(in.hasNext()){
            node1 = in.next();
            if(in.hasNext()){
                node2 = in.next();
                in.close();
                Model.removeLink(node1,node2);
            }else{
                in.close();
                throw new Exception("Incorrect syntax for Node two's name");
            }
        }else{
            in.close();
            throw new Exception("Incorrect syntax for Node one's name or id");
        }
    }

    public static void editNode(Scanner in) throws Exception {
        int id;
        String name;
        double newValue;
        String newName;
        if(in.hasNextInt()){
            id = in.nextInt();
            if(in.hasNext()){
                newName = in.next();
                in.close();
                Model.editNode(id,newName);
            }else{
                in.close();
                throw new Exception("Incorrect syntax for Node's new name");
            }
        }else if(in.hasNext()){
            name = in.next();
            if(in.hasNext()){
                newName = in.next();
                in.close();
                Model.editNode(name,newName);
            }else{
                in.close();
                throw new Exception("Incorrect syntax for Node's new name");
            }
        }else{
            in.close();
            throw new Exception("Incorrect syntax for Node's old name or id");
        }
    }

    public static void editLink(Scanner in) throws Exception {
        int id1;
        int id2;
        String name1;
        String name2;
        double newValue;
        if(in.hasNextInt()){
            id1 = in.nextInt();
            if(in.hasNextInt()){
                id2 = in.nextInt();
                if(in.hasNextDouble()){
                    newValue = in.nextDouble();
                    in.close();
                    Model.editLink(id1,id2,newValue);
                }else{
                    in.close();
                    throw new Exception("Incorrect syntax for Link's new value");
                }
            }else{
                in.close();
                throw new Exception("Incorrect syntax for Node two's id");
            }
        }else if(in.hasNextDouble()){
            in.close();
            throw new Exception("Incorrect syntax for Node one's name or id");
        }else if(in.hasNext()){
            name1 = in.next();
            if(in.hasNext()){
                name2 = in.next();
                if(in.hasNextDouble()){
                    newValue = in.nextDouble();
                    in.close();
                    Model.editLink(name1,name2,newValue);
                }else{
                    in.close();
                    throw new Exception("Incorrect syntax for Link's new value");
                }
            }else{
                in.close();
                throw new Exception("Incorrect syntax for Node two's name");
            }
        }else{
            in.close();
            throw new Exception("Incorrect syntax for Node one's name or id");
        }
    }

    public static void printGraph() throws Exception {
        Model.printGraph();
    }

    public static void printFloydWarshall() {
        Model.printFloydWarshall();
    }

    public static void bellmanFord(Scanner in) throws Exception{

        if(in.hasNextDouble()){
            in.close();
            throw new Exception("Incorrect syntax for source node");
        }else if(in.hasNext()){
            Model.bellmanFord(in.next());
            in.close();
        }else{
            in.close();
            throw new Exception("No input read for source node");
        }

    }

    public static void printDijkstra(Scanner in) throws Exception {

        String start;
        String end;

        if(in.hasNextDouble()){
            in.close();
            throw new Exception("Incorrect syntax for source node");
        }else if(in.hasNext()){
            start = in.next();
            if(in.hasNextDouble()) {
                in.close();
                throw new Exception("Incorrect syntax for end node");
            }else if(in.hasNext()){
                end = in.next();
                in.close();
            }else{
                in.close();
                throw new Exception("No input read for end node");
            }
        }else{
            in.close();
            throw new Exception("No input read for source node");
        }
        Model.printDijkstra(start,end);
    }
}