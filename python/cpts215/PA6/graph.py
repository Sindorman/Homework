from collections import deque

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019
Programming Project #6
04/22/19

Description: Directional graph class
'''

class Vertex:
    '''
    A class representing a Vertex for graph. 
    Vertex has ID which is a key and list of connected vertecies.
    '''
    def __init__(self, key):
        '''
        
        '''
        self.ID = key
        self.connected_to = {}

    def add_neighbor(self, neighbor, label=""):
        '''
        Method used to add a neighboor vertex.
        Parameter list: Vertex that is a neightboor, and edge label.
        '''
        self.connected_to[neighbor] = label

    def __str__(self):
        '''
        returns all of the vertices in the adjacency list, as represented by the connectedTo instance variable
        '''
        ret = str(self.ID) + ' connected to: ['
        for x in self.connected_to:
            ret += "{} -> {}, ".format(x.ID, self.connected_to[x])
        ret += "]"
        return ret

    def get_connections(self):
        '''
        Method used to get list of connected vertecies.
        return: list.
        '''
        return self.connected_to.keys()

    def get_ID(self):
        '''
        Method of accessing ID of Vertex.
        return: ID as anything
        '''
        return self.ID

    def get_label(self, neighbor):
        '''
        returns the weight of the edge from this vertex to the vertex passed as a parameter
        '''
        return self.connected_to[neighbor]
    
class Graph:
    '''
    A class representing a directional Graph. 
    Graph contains number of vertices and list of vertices.
    '''
    def __init__(self):
        '''
        
        '''
        self.vert_list = {}
        self.num_vertices = 0
        
    def __str__(self):
        '''
        
        '''
        edges = ""
        for vert in self.vert_list.values():
            for vert2 in vert.get_connections():
                edges += "(%s -> %s -> %s)\n" %(vert.get_ID(), vert.get_label(vert2), vert2.get_ID())
        return edges

    def add_vertex(self, key):
        '''
        Method used to add new Vertex to the graph.
        Parameter list: key of vertex.
        return: new Vertex class.
        '''
        self.num_vertices = self.num_vertices + 1
        new_vertex = Vertex(key)
        self.vert_list[key] = new_vertex
        return new_vertex

    def get_vertex(self, n):
        '''
        Method used to get vertex.
        Parameter list: ID of vertex.
        return: Vertex or None.
        '''
        if n in self.vert_list:
            return self.vert_list[n]
        else:
            return None

    def __contains__(self, n):
        '''
        in operator
        '''
        return n in self.vert_list

    def add_edge(self, f, t, cost=""):
        '''
        Method used to connect two vertices.
        Parameter list: ID of first vertex, ID of second vertex, their label.
        '''
        if f not in self.vert_list:
            nv = self.add_vertex(f)
        if t not in self.vert_list:
            nv = self.add_vertex(t)
        self.vert_list[f].add_neighbor(self.vert_list[t], cost)

    def get_vertices(self):
        '''
        returns the names of all of the vertices in the graph
        '''
        return self.vert_list.keys()

    def __iter__(self):
        '''
        for functionality
        '''
        return iter(self.vert_list.values())

    def bfs(self, g, start):
        '''
        Method used to do Breadth-first Search.
        Parameter list: Vertext to start.
        return: New directional Graph from Breadth-first Search.
        '''
        frontier_queue = deque()
        frontier_queue.appendleft(start)
        discovered_set = set([start])
        graph = Graph()
        graph.add_vertex(start.ID)

        while len(frontier_queue) > 0:
            curr_v = frontier_queue.pop()
            #print(curr_v)
            for adj_v in curr_v.get_connections():
                if adj_v not in discovered_set:
                    frontier_queue.appendleft(adj_v)
                    graph.add_vertex(adj_v.ID)
                    graph.add_edge(adj_v.ID, curr_v.ID, adj_v.get_label(curr_v))
                    discovered_set.add(adj_v)
        return graph

import matplotlib.pyplot as plt
import networkx as nx

if __name__ == "__main__":
    G = nx.DiGraph()
    G.add_edge(0, 3, label=5)
    G.add_edge(3, 5, label=10)
    G.add_edge(5, 6, label=5)
    G.add_edge(0, 3, label=5)
    G.add_edge(0, 1, label=10)
    G.add_edge(1, 2, label=15)
    G.add_edge(2, 4, label=5)
    G.add_edge(4, 7, label=15)
    elabels = {edge:G[edge[0]][edge[1]]["label"] for i, edge in enumerate(G.edges())}
    pos = nx.spring_layout(G)
    nx.draw_networkx_nodes(G, pos, cmap=plt.get_cmap('jet'), node_color='gold', node_size = 500)
    nx.draw_networkx_labels(G, pos, font_size=8)
    nx.draw_networkx_edge_labels(G, pos, edge_labels=elabels, font_size=8)
    nx.draw_networkx_edges(G, pos, edge_color='c', arrows=True)
    plt.axis('off')
    plt.autoscale(enable=True, tight=False)
    plt.show()