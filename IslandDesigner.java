/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

package Homework7;

import java.util.List;
import java.util.Scanner;

/**
 * This class acts like design for an island where you can load and find out dfs, maximum flow and shortest path.
 */

public class IslandDesigner {

    public static IslandNetwork islandNetwork;

    /**
     * This is main method which consists of a menu driven program that gives user to carry out certain operations.
     * @param args
     * arguments of string
     */

    public static void main(String[] args) {


        Scanner scanner=new Scanner(System.in);

        islandNetwork=new IslandNetwork();

        System.out.println();

        System.out.println("Welcome to the Island Designer, because, when you're trying to stay above water, Seas get degrees!\n");

        System.out.println("Please enter an url:");
        String url=scanner.nextLine();

       islandNetwork.loadFromFile(url);

        islandNetwork.setListOfNeighbours(islandNetwork.getGraph());

        System.out.println();

        boolean check=true;

        if(islandNetwork.isValidUrl(url))
        {

        while(check) {

            System.out.println("Menu:\n" +
                    "    D) Destinations reachable (Depth First Search)\n" +

                    "    F) Maximum Flow\n" +

                    "    S) Shortest Path (Extra Credit)\n" +
                    "    Q) Quit");
            System.out.println();

            System.out.print("Enter an option:");

            char choice = scanner.next().charAt(0);

            choice = Character.toUpperCase(choice);


            scanner.nextLine();


            switch (choice) {
                case 'D':
                    System.out.print("Please enter a starting city:");

                    String startingCity = scanner.nextLine();

                    List<String> temp = islandNetwork.dfs(startingCity);

                    if (temp != null) {

                        System.out.println("DFS Starting From " + startingCity + ":");
                        for (int i = 0; i < temp.size(); i++) {

                            if (i == temp.size() - 1)
                                System.out.print(temp.get(i));
                            else
                                System.out.print(temp.get(i) + ", ");
                        }
                        System.out.println();
                    }

                    System.out.println();

                    break;

                case 'F':
                    System.out.print("Please enter a starting city:");
                    String startingPoint = scanner.nextLine();

                    System.out.print("Please enter a destination:");
                    String destinationPoint = scanner.nextLine();

                    String returnedValue = islandNetwork.maxFlow(startingPoint, destinationPoint);

                    if (returnedValue != null) {
                        System.out.println("Routing:");
                        System.out.println(returnedValue);
                        islandNetwork.setMaxFlow(0);
                        islandNetwork.setRoutes("");
                        System.out.println();
                    }
                    break;

                case 'S':
                    System.out.print("Please enter a starting node:");
                    String start= scanner.nextLine();

                    System.out.print("Please enter a destination node:");
                    String end = scanner.nextLine();

                    islandNetwork.djikstra(start,end);

                    if(islandNetwork.getCount()!=0) {

                        System.out.println(islandNetwork.shortestPathAndValue(islandNetwork.getAllThePaths(), islandNetwork.getPathCount()));
                    }

                    System.out.println();

                    islandNetwork.clearLists();

                    break;

                case 'Q':
                    System.out.println("You can go your own way! Goodbye!");
                    check = false;
                    break;

                default:
                    System.out.println("Wrong option selected.");
                    break;
            }
        }
        }
    }
}
