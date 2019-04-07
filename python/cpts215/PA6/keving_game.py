from graph import Graph

def main():        
    # test out the Graph class by making the example graph we have been using (vertices 1,...,6)
    # weights are set to dummy values
    g = Graph()
    for i in range(1, 7):
        g.add_vertex(i)
        
    # each edge is two-way
    g.add_edge(1, 2, 1 * 2)
    g.add_edge(2, 1, 1 * 2)

    g.add_edge(1, 5, 1 * 5)
    g.add_edge(5, 1, 1 * 5)

    g.add_edge(2, 5, 2 * 5)
    g.add_edge(5, 2, 2 * 5)

    g.add_edge(2, 3, 2 * 3)
    g.add_edge(3, 2, 2 * 3)

    g.add_edge(3, 4, 3 * 4)
    g.add_edge(4, 3, 3 * 4)

    g.add_edge(4, 5, 4 * 5)
    g.add_edge(5, 4, 4 * 5)

    g.add_edge(4, 6, 4 * 6)
    g.add_edge(6, 4, 4 * 6)
    print(g)
    g.bfs(g, g.get_vertex(5))

if __name__ == "__main__":
    main()