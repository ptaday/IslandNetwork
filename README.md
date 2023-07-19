# Island Network

> A Java traffic simulation program for Longer Island, where major highways form a Directed Acyclic Graph (DAG) with one-way traffic. The program calculates paths between cities using Depth First Search (DFS) and determines the maximum network flow between any two cities.

## Table of Contents

- [Description](#description)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Required Classes](#required-classes)
  - [City](#city)
  - [IslandNetwork](#islandnetwork)
  - [IslandDesigner](#islanddesigner)
- [Optional Classes](#optional-classes)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Description

The Island Traffic Simulation program is designed to calculate various traffic-related details on Longer Island. It models the island's traffic network as a DAG with one-way traffic from cities to the local city, Small Pear, in the morning, and away in the evening. The program can answer two main questions: where a person can get from a given city using Depth First Search (DFS) and the maximum amount of flows between any two cities.

## Prerequisites

To run the Island Traffic Simulation program, ensure you have the following installed on your computer:

- Java Development Kit (JDK)
- Git (optional, for cloning the repository)

## Installation

1. Clone the Island Network repository to your local machine (if you haven't already).

```bash
$ git clone https://github.com/yourusername/IslandNetwork.git
$ cd IslandNetwork
```
## Usage
To compile 
```bash
$ javac islandNetwork.java
```
Run the DeliveryListManager application from the command line.
```bash
$ java islandNetwork
```
Run the IslandDesigner application.

Follow the on-screen prompts to interact with the traffic simulation program. The menu-based interface allows you to run Depth First Searches from any city to any other city, as well as finding the maximum network flow between cities. For extra credit, you can also use the shortest path algorithm to display the shortest path between cities.

Keep in my mind to create a package name and include the package name in all the classes.

## Required Classes

## City
A fully-documented class named City that represents each vertex/node of the traffic graph. Each city needs to know what roads lead out of it and their capacity. The City class should implement the Comparable interface for sorting purposes based on the city name.

## IslandNetwork
A fully-documented class named IslandNetwork that holds the traffic graph. The class should be able to load a graph from a file and calculate DFS from any node in the graph, as well as calculate the maximal network flow between any two nodes.

## IslandDesigner
A fully-documented class named IslandDesigner that allows users to interact with the traffic simulation program. The class provides a menu-based interface to run DFS from any city to any other city and find the maximum network flow between cities. For extra credit, it can also use the shortest path algorithm to display the shortest path between cities.

## Contributing
Contributions to the IslandNetwork are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
If you have any questions or feedback, you can contact the project maintainer at:

Email: pmtaday@gmail.com
GitHub: @ptaday
