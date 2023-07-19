/**
 * @author Pushkar Taday
 */

import java.util.*;

/**
 * This class represents a "city" node in the graph.
 */

public class City implements Comparable {

    HashMap<String, Integer> neighbors;
    String name;
    boolean discovered;
    boolean visited;
    List listOfNeighbors = new ArrayList<String>();

    //default constructor
    City() {
    }

    /**
     * This represents a parameterized constructor which assigns some of the member variables of class when object of this class gets instantiated.
     * @param name
     * name of the city
     * @param discovered
     * if its discovered or not
     * @param visited
     * if its visited or not
     */

    City(String name, boolean discovered, boolean visited) {
        this.name = name;
        this.discovered = discovered;
        this.visited = visited;
        neighbors = new HashMap<String, Integer>();
    }

    /**
     * This method is an accessor method for the name of the city
     * @return
     * name is the city
     */

    public String getName() {
        return name;
    }

    /**
     * This method is an accessor method for the neighbors of the city in the form of hashmap
     * @return
     * Hashmap of neighbors
     */
    public HashMap<String, Integer> getNeighbors() {
        return neighbors;
    }

    /**
     * This method is an accessor method whether the city is discovered or not
     * @return
     * boolean value
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * This method is an accessor method whether the city is visited or not
     * @return
     * boolean value
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * This method is a mutator method for the name of the city
     * @param name
     * name of the city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is a mutator method for the discovered member variable
     * @param discovered
     * boolean value
     */
    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    /**
     * This method is a mutator method for the visited member variable
     * @param visited
     * boolean value
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * This method take in a string and a it value to be assigned to hashmap.
     * @param name
     * name to be inserted
     * @param cost
     * cost to be inserted
     */
    public void setNeighbors(String name, Integer cost) {
        neighbors.put(name, cost);
    }

    /**
     * This method acts a mutator method for the hashmap for neighbors of the city.
     * @param neighbors
     * Hashmap of neighbors
     */
    public void setNeighborsHash(HashMap<String, Integer> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * This method set the contents of hashmap into arraylist in an alphabetical order
     * @param map
     * HashMap of type string and integer
     */

    public void setListOfNeighbors(HashMap<String, Integer> map) {

        Set set = map.keySet();

        listOfNeighbors.addAll(set);

        Collections.sort(listOfNeighbors);
    }

    /**
     * This method is a mutator method for the list of neighbors
     * @param listOfNeighbors
     */

    public void setListOfNeighbors(List listOfNeighbors) {
        this.listOfNeighbors = listOfNeighbors;
    }

    /**
     * This method is an accessor method for the list of neighbors
     * @return
     */
    public List<String> getListOfNeighbors() {
        return listOfNeighbors;
    }

    /**
     * This method is used to compare two int values
     * @param o
     * o object of class Object
     * @return
     * compared int value
     */

    @Override
    public int compareTo(Object o) {

        City city = (City) o;

        return (this.name).compareTo(city.getName());
    }

    /**
     * This method is invoked when the value of the object is needed
     * @return
     * name of the city
     */
    public String toString() {
        return this.name;
    }

    /**
     * This method makes a deep copy of the object when called
     * @return
     * cloned object of type Object
     */
    public Object clone() {
        City city = new City(this.getName(), isDiscovered(), isVisited());

        HashMap<String, Integer> copy = new HashMap<String, Integer>();

        for (Map.Entry<String, Integer> entry : this.getNeighbors().entrySet()) {
            copy.put(entry.getKey(),
                    entry.getValue());
        }

        city.setNeighborsHash(copy);

        city.setListOfNeighbors(getNeighbors());

        return city;
    }
}
