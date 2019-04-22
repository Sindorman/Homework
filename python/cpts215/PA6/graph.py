from collections import deque

class Vertex:
    '''
    keep track of the vertices to which it is connected, and the weight of each edge
    '''
    def __init__(self, key):
        '''
        
        '''
        self.ID = key
        self.connected_to = {}

    def add_neighbor(self, neighbor, label=""):
        '''
        add a connection from this vertex to anothe
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
        
        '''
        return self.connected_to.keys()

    def get_ID(self):
        '''
        
        '''
        return self.ID

    def get_label(self, neighbor):
        '''
        returns the weight of the edge from this vertex to the vertex passed as a parameter
        '''
        return self.connected_to[neighbor]
    
class Graph:
    '''
    contains a dictionary that maps vertex names to vertex objects. 
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
        adding vertices to a graph 
        '''
        self.num_vertices = self.num_vertices + 1
        new_vertex = Vertex(key)
        self.vert_list[key] = new_vertex
        return new_vertex

    def get_vertex(self, n):
        '''
        
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
        connecting one vertex to another
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
        enqueue: append left
        dequeue: pop right
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