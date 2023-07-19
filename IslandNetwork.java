/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

package Homework7;

import big.data.DataSource;

import java.util.*;

import java.net.URL;

/**
 * This class helps to create a network of city nodes i.e. the graph and has methods to help do some operations on the same.
 */

public class IslandNetwork {

    private  HashMap<String, City> graph;

    /**
     * This is a default constructor for this class which gets instantiated during the object creation of this class
     */

    IslandNetwork() {
         graph = new HashMap<String,City>();
    }

    /**
     * This is a accessor method for the Hashmap of the graph of city nodes.
     * @return
     * graph as a hashmap
     */
    public HashMap<String, City> getGraph() {
        return graph;
    }

    /**
     * This is a mutator method for the hashmap of the graph of city nodes
     * @param graph
     */
    public void setGraph(HashMap<String, City> graph) {
        this.graph = graph;
    }

    /**
     * This method helps to load the contents of the url.
     * @param url
     * url for the contents to be loaded
     * @return
     * Object of the Island Network class
     */
    public static IslandNetwork loadFromFile(String url) {

        if (IslandDesigner.islandNetwork.isValidUrl(url)) {

            System.out.println("Map loaded.");

            System.out.println();

            DataSource ds = DataSource.connectXML(url);

            ds.load();

            String cityNamesStr = ds.fetchString("cities");

            String[] cityNames = cityNamesStr.substring(1, cityNamesStr.length() - 1).replace("\"", "").split(",");

            String roadNamesStr = ds.fetchString("roads");

            String[] roadNames = roadNamesStr.substring(1, roadNamesStr.length() - 1).split("\",\"");


            IslandDesigner.islandNetwork.addToHashMap(cityNames);

            System.out.println();

            System.out.printf("%-40s%-10s\n", "Road", "Capacity");

            System.out.println("------------------------------------------------------------------");

            City temp=null; int cost=0;

            for(int i = 0; i < roadNames.length; i++) {

                int commaCount=0; String str=""; roadNames[i]=roadNames[i]+" "; String neighbour=""; String current="";

                for(int j=0;j< roadNames[i].length();j++)
                {
                 if(roadNames[i].charAt(j)=='"'|| roadNames[i].charAt(j)=='"')
                 {
                  continue;
                 }
                 else if(roadNames[i].charAt(j)==',')
                 {
                     commaCount++;

                     if(commaCount==1)
                     {
                         current=str;
                         temp=IslandDesigner.islandNetwork.getGraph().get(str);
                         str="";
                     }

                     if(commaCount==2) {
                         neighbour=str;
                         str="";
                     }

                 }
                 else if(roadNames[i].charAt(j)==' '&&j==roadNames[i].length()-1)
                 {
                     cost =Integer.parseInt(str);
                 }
                 else
                 {
                     str=str+roadNames[i].charAt(j);
                 }

                }
                String text=current+" to "+neighbour;

                System.out.printf("%-40s", text);
                System.out.println(cost);
                temp.setNeighbors(neighbour,cost);
            }
        }

        else
        {
            System.out.println("Invalid URL. Program terminates.");
        }

        return IslandDesigner.islandNetwork;
    }

    /**
     * This method just loads the array of names of the city to the Hashmap of the graph
     * @param cityNames
     */
    public void addToHashMap(String[] cityNames)
    {
        List<City> list = new ArrayList<City>();

        for (int i = 0; i < cityNames.length; i++) {
            IslandDesigner.islandNetwork.getGraph().put(cityNames[i],new City(cityNames[i],false,false));
            list.add(new City(cityNames[i],false,false));
        }

        Collections.sort(list);

        System.out.println("Cities:");
        System.out.println("-----------------------------------------");

        for(City i:list)
        {
            System.out.println(i);
        }
    }

    /**
     * This is a method to execute the depth first search on the hashmap of graph
     * @param from
     * Starting point for the depth first search
     * @return
     * List of city nodes after executing the depth first search
     */
    public List<String> dfs(String from)
    {
        if(graph.containsKey(from)) {

            List<String> pathway = new ArrayList<String>();
            setAllVisitedAndDiscoveredToFalse(graph);


            City node = graph.get(from);
            if (node == null) {
                System.out.println("City does not exist in the graph.");
                return null;
            } else {
                return dfsHelper(from, pathway, from);
            }
        }

        else {
            System.out.println("Such city does not exist. Enter the name of the city correctly.");
            return null;
        }
    }

    /**
     * This method is an helper method for the dfs method to carry out recursive execution for depth first search
     * @param from
     * The current city node
     * @param dfs
     * The list of the nodes traversed
     * @param head
     * Starting point for the depth first search
     * @return
     * List of city nodes after executing the depth first search
     */
    public List<String> dfsHelper(String from,List<String> dfs,String head)
    {

        City node=graph.get(from);

        if(node.isVisited()==false) {
            dfs.add(from);
            node.setVisited(true);
        }

        if(node.getNeighbors()!=null && node.isDiscovered()==false) {

            node.setDiscovered(true);

            for (int i = 0; i < node.getListOfNeighbors().size(); i++) {
                dfsHelper(node.getListOfNeighbors().get(i), dfs, head);
            }
        }
        if(from==head)
            return dfs;

        else
            return null;
    }

    /**
     * This method helps calculate the maximum flow and the combination of routes that add up to the maximum flow
     * @param from
     * Starting point to calculate the maximum flow
     * @param to
     * Destination till you want to calculate maximum flow
     * @return
     * String to be printed i.e. routes  with  maximumflow
     */

    public String maxFlow(String from, String to)
    {


        List<String> list= dfs(from);

        if(list!=null) {

            if(from.equals(to)) {
                System.out.println("Same starting point and destination selected. Hence, cannot determine the maxflow.");
                System.out.println();
                return null;
            }

            setAllVisitedAndDiscoveredToFalse(graph);

            HashMap<String, City> copy = new HashMap<String, City>();

            copy = clone(getGraph());

            List<String> routes = new ArrayList<String>();

            boolean check = false;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).equals(to)) {
                    check = true;
                    break;
                }
            }

            if (!check)
                System.out.println("No route available!");

            else {
                Stack newStack = new Stack();


                return maxFlow(to, from, newStack, from, copy) + "Maxflow:" + maxFlow;

            }
        }

        System.out.println();
   return null;

    }


    String routes=""; //to keep a track of routes for max flow
    int maxFlow=0;  // to keep a track of maximum flow
    String temp=null;

    boolean checkIfItsDirectFromAndTo=false;

    /**
     * This method acts like an helper method for the above maxflow method to calculate the maximum flow
     * @param to
     * Destination till you want to calculate maximum flow
     * @param from
     * The current node
     * @param stack
     * to keep track of the path from start to end
     * @param head
     * Starting point to calculate the maximum flow
     * @param duplicate
     * @return
     * String to be printed i.e. routes  with  maximumflow
     */

    public String maxFlow(String to, String from, Stack stack, String head,HashMap<String,City>duplicate)
    {
        City current=duplicate.get(from);

        if(current!=duplicate.get(to))
        {

            stack.push(current);
        }

        if(current==duplicate.get(to))
        {
            stack.push(current);
            int min=calculatingTheMinLength(stack);
            routes=routes+routeStoring(stack,min);

            if(stack.size()==2)
                checkIfItsDirectFromAndTo=true;

             maxFlow=maxFlow+min;
             modifyGraph(min,stack);

            stack.pop();
            temp="found the track";
            return temp;

        }

        else
        {
            if(current.getNeighbors().size()==0) {
                stack.pop();
                return null;
            }

            if( current.isVisited()==false) {

                for (int i = 0; i < current.getNeighbors().size(); i++) {

                    maxFlow(to, current.getListOfNeighbors().get(i), stack,head,duplicate);

                    if(temp!=null)
                        break;
                }
            }
        }

       if(current.getName().equalsIgnoreCase(head)&&temp==null||current.getName().equalsIgnoreCase(head)&&checkIfItsDirectFromAndTo) {
           return routes;
       }

        else if(current.getName().equalsIgnoreCase(head)&&temp!=null) {
            stack.pop();
           temp=null;
           setAllVisitedAndDiscoveredToFalse(duplicate);
           return maxFlow(to, head, stack, head, duplicate);
        }

       else
       {
           stack.pop();
           current.setVisited(true);
           return null;
       }
    }

    List<String> allThePaths=new ArrayList<String>(); //list to keep tracks of the all the possible paths
    List<Integer> pathCount=new ArrayList<Integer>(); // list to keep the distance covered by each path

    /**
     * This method is used to calculate the shortest path from start to end
     * @param from
     * The starting city node
     * @param to
     * The destination city node
     */
    public void djikstra(String from, String to)
    {
        List<String> list=dfs(from);

        if(list!=null) {
            setAllVisitedAndDiscoveredToFalse(graph);

            boolean same=false;

            if(from.equals(to)) {
                System.out.println("Same starting point and destination selected. Hence, cannot determine the shortest path.Therefore,");
                same=true;
            }
            boolean check = false;

            for (int i = 1; i < list.size(); i++) {
                if (to.equals(list.get(i))) {
                    check = true;
                    break;
                }
            }

            if (!check&&!same) {
                System.out.println("No routes available.");

            } else {
                Stack stack = new Stack();
                djikstraHelper(from, to, stack, from);
            }
        }

    }

    int count=0;//to keep a count of all the paths
    int c=0;

    /**
     * This method is an helper method for above method used to recursively find the shortest path
     * @param from
     * The current node
     * @param to
     * The destination node
     * @param stack
     * To keep a track of a path
     * @param head
     * The starting node
     */
    public void djikstraHelper(String from, String to, Stack stack, String head)
    {
        City present=graph.get(from);

        stack.push(present);

        if(present==graph.get(to))
        {
            storePath(stack);
            present.setVisited(true);

            if(stack.size()>1&&c==0) {
                System.out.println("Shortest path and cost:");
                c=1;
            }

            count++;

            stack.pop();
        }
        else{

            for (int i = 0; i <present.getNeighbors().size();i++)
            {
                djikstraHelper(present.getListOfNeighbors().get(i),to,stack,head);
            }

            stack.pop();
        }
    }

    /**
     * This method checks whether the url entered by the user is valid or not
     * @param str
     * the url entered by the user
     * @return
     * a boolean value
     */
    public boolean isValidUrl(String str)
    {
        try {
            new URL(str).toURI();
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }

    /**
     * This method sets visited and discovered member variables of all the city nodes to false
     * @param tempGraph
     * The hashmap having the graph
     */
    public void setAllVisitedAndDiscoveredToFalse(HashMap<String,City>tempGraph)
    {

        for (String name: tempGraph.keySet()) {
            String key = name;
            City value = tempGraph.get(key);
            value.setVisited(false);
            value.setDiscovered(false);
        }
    }

    /**
     * This method is acts as an cloning method for the hashmap
     * @param original
     * the hashmap you want to clone
     * @return
     * A deep cloned Hashmap of the hashmap entered
     */
    public HashMap<String, City> clone(HashMap<String,City> original)
    {
        HashMap<String, City> copy = new HashMap<String, City>();
        for (Map.Entry<String,City> entry : original.entrySet())
        {
            copy.put(entry.getKey(),
                    (City)(entry.getValue().clone()));
        }
        return copy;
    }

    /**
     * This method calculates the min cost of the path found during maximum flow
     * @param stack
     * the path found
     * @return
     * minimum cost found on the path
     */
    public int calculatingTheMinLength(Stack stack)
    {
        int min=((City)stack.get(0)).getNeighbors().get(((City)stack.get(1)).getName());

        for(int i=1;i<stack.size()-1;i++)
        {
            int temp=((City)stack.get(i)).getNeighbors().get(((City)stack.get(i+1)).getName());

            if(temp<min)
                min=temp;
        }
        return  min;
    }

    /**
     * This method helps to store the route of the path found during calculating maximum flow
     * @param stack
     * The path found
     * @param min
     * The minimum cost in the path
     * @return
     * String containing the route and the minimum cost of the path
     */
    public String routeStoring(Stack stack, int min)
    {
        String s="";

        for(int i=0;i<stack.size();i++) {

            if (i == stack.size() - 1)
                s = s + ((City) stack.get(i)).getName() + ": " + min + "\n";

            else
                s = s + ((City) stack.get(i)).getName() + "->";
        }


      return s;
    }

    /**
     * This method modifies the neighbors of the node that has the minimum cost
     * @param min
     * the minimum cost
     * @param newStack
     * The path found
     */
    public void modifyGraph(int min,Stack newStack)
    {
        City temp=null; int place=0;

        for(int i=0;i<newStack.size()-1;i++)
        {
            if( min==((City)newStack.get(i)).getNeighbors().get(((City)newStack.get(i+1)).getName())) {
                place = i;
                break;
            }
        }

        temp=(City)newStack.get(place);

        int a=0;
        a= (int) ((City)newStack.get(place)).getNeighbors().remove(((City)newStack.get(place+1)).getName());

       String nameToFind= ((City)newStack.get(place+1)).getName();int index=0;

       for(int  j=0;j<((City) newStack.get(place)).getListOfNeighbors().size();j++)
       {
           if(nameToFind.equalsIgnoreCase(((City) newStack.get(place)).getListOfNeighbors().get(j)))
               index=j;

       }

          ((City) newStack.get(place)).getListOfNeighbors().remove(index);

    }

    /**
     * This method sets the list of neighbours of a hashmap
     * @param map
     * hashmap whose neighbors have to be set as arraylist
     */
    public void setListOfNeighbours(HashMap<String,City>map)
    {

        Set keySet = map.keySet();
        List<String> list= new ArrayList(keySet);

        for(int i=0;i<list.size();i++)
        {
            graph.get(list.get(i)).setListOfNeighbors(graph.get(list.get(i)).neighbors);
        }

    }

    /**
     * This is a mutator method for the maxflow
     * @param maxFlow
     * to set maxflow
     */
    public void setMaxFlow(int maxFlow) {
        this.maxFlow = maxFlow;
    }

    /**
     * This is a mutator method for the routes
     * @param routes
     * to set the route
     */
    public void setRoutes(String routes) {
        this.routes = routes;
    }


    /**
     * This method helps to store path from stack to an arraylist
     * @param stack
     * Stack of path from start to destination
     */

    public void storePath(Stack stack)
    {
        String s=""; int total=0;

        for(int i=0;i<stack.size();i++) {

            if (i == stack.size() - 1)
                s = s + ((City) stack.get(i)).getName() ;

            else {
                s = s + ((City) stack.get(i)).getName() + "->";
                total=total+((City) stack.get(i)).getNeighbors().get( ((City) stack.get(i+1)).getName());
            }
        }

        allThePaths.add(s);
        pathCount.add(total);
    }

    /**
     * This method is to reset the counter and two arraylist used for djikstras
     */
    public void clearLists()
    {
        allThePaths.clear();
        pathCount.clear();
        count=0;
        c=0;
    }

    /**
     * This method calculates the route and the minimum cost from all the paths
     * @param allThePaths
     * list of all the paths
     * @param pathCount
     * cost of all the paths
     * @return
     * String containing cost and route of the shortest path
     */
    public String shortestPathAndValue(List<String>allThePaths,List<Integer>pathCount)
    {
        int min=pathCount.get(0);int index=0;

        for(int i=1;i<pathCount.size();i++)
        {
            int temp=pathCount.get(i);

            if(min>temp) {
                min = temp;
                index = i;
            }
        }

        return allThePaths.get(index)+"\nCost: "+pathCount.get(index);
    }

    /**
     * This is an accessor method for list of distance
     * @return
     * List of distance of all paths
     */
    public List<Integer> getPathCount() {
        return pathCount;
    }

    /**
     * This is an accessor method for the list of paths
     * @return
     * list of all the paths
     */
    public List<String> getAllThePaths() {
        return allThePaths;
    }

    /**
     * This is an mutator method for the list of paths
     * @param allThePaths
     * list of all the paths that has to be set
     */
    public void setAllThePaths(List<String> allThePaths) {
        this.allThePaths = allThePaths;
    }

    /**
     * This is an mutator method for the list for list of distance of the ptahs
     * @param pathCount
     * List of distances of all the paths that has to be set
     */
    public void setPathCount(List<Integer> pathCount) {
        this.pathCount = pathCount;
    }

    /**
     * This is an accessor method for the count of the paths
     * @return
     * count of all the paths
     */
    public int getCount() {
        return count;
    }
}
