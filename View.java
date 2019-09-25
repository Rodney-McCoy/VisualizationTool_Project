import java.util.Scanner;

public class View {
    public static void mainMenu(Scanner in){
        while (true){
            System.out.println("" +
                    "(1) Make Node\n" +
                    "(2) Make Link\n" +
                    "(3) Remove Node\n" +
                    "(4) Remove Link\n" +
                    "(5) Edit Node\n" +
                    "(6) Edit Link\n" +
                    "(7) Print Graph\n" +
                    "(8) Run Floyd-Warshall\n" +
                    "(9) Run Bellman Ford\n" +
                    "(10) Load Graph\n" +
                    "(11) Save Graph\n" +
                    "(12) Quit\n");
            String userInput = in.nextLine();
            int choice = 0;
            try{
                choice = Controller.checkMainMenuInput(new Scanner(userInput));
            }catch (Exception e){
                System.out.println(e);
                in.nextLine();
            }

            switch (choice){
                case 1:
                    System.out.println("Make Node");
                    System.out.println("Enter a name for the Node followed by its value\n" +
                                       "Ex. testNode 14");
                    userInput = in.nextLine();
                    try{
                        Controller.makeNode(new Scanner(userInput));
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 2:
                    System.out.println("Make Link");
                    System.out.println("Enter a value for the Link followed a name\n" +
                                       "for two Nodes (or ID if known)\n" +
                                       "Ex. 5 node0 node1 OR 5 0 1");
                    userInput = in.nextLine();
                    try{
                        Controller.makeLink(new Scanner(userInput));
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Remove Node");
                    System.out.println("Enter a name or id of the Node\n" +
                                       "Ex. testNode OR 0");
                    userInput = in.nextLine();
                    try{
                        Controller.removeNode(new Scanner(userInput));
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("Remove Link");
                    System.out.println("Enter two names or id's of the two Nodes\n" +
                                       "connected by the link\n" +
                                       "Ex. node0 node1 OR 0 1");
                    userInput = in.nextLine();
                    try{
                        Controller.removeLink(new Scanner(userInput));
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 5:
                    System.out.println("Edit Node");
                    System.out.println("Enter a name or id of the Node then a new\n" +
                                       "name, value, or both\n" +
                                       "Ex. testNode newName 34 OR 0 newName 34");
                    userInput = in.nextLine();
                    try{
                        Controller.editNode(new Scanner(userInput));
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Edit Link");
                    System.out.println("Enter two names or id's of the two Nodes connected\n" +
                                       "by the link then a new value for the link\n" +
                                       "Ex. node0 node1 5 OR 0 1 5");
                    userInput = in.nextLine();
                    try{
                        Controller.editLink(new Scanner(userInput));
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 7:
                    System.out.println("Print Graph");
                    try{
                        Controller.printGraph();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    in.nextLine();
                    break;
                case 8:
                    Controller.printFloydWarshall();
                    in.nextLine();
                    break;
                case 9:
                    System.out.println("Enter a source Node name\n");
                    userInput = in.nextLine();
                    System.out.println("Running Bellman Ford");
                    try{
                        Controller.bellmanFord(new Scanner(userInput));
                        in.nextLine();
                    }catch (Exception e){
                        System.out.println(e);
                        in.nextLine();
                    }
                    break;
                case 10:
                    System.out.println("Load Graph");
                    break;
                case 11:
                    System.out.println("Save Graph");
                    break;
                case 12:
                    System.out.println("Quit");
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error in Main Switch");
                    break;
            }
        }
    }
}
