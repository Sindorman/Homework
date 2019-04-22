from graph import Graph

def main():        
    # test out the Graph class by making the example graph we have been using (vertices 1,...,6)
    # weights are set to dummy values
    g = Graph()
    ''' 
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
    '''
    g.add_edge("Kevin Bacon", "actor1", "movie1")
    g.add_edge("actor1", "Kevin Bacon", "movie1")
    g.add_edge("Kevin Bacon", "Jerry Smith", "movie2")
    g.add_edge("Jerry Smith", "Kevin Bacon", "movie2")
    g.add_edge("Jerry Smith", "actor1", "movie3")
    g.add_edge("actor1", "Jerry Smith", "movie3")
    g.add_edge("actor4", "Jerry Smith", "movie4")
    g.add_edge("Jerry Smith", "actor4", "movie4")
    g.add_edge("actor5", "actor4", "movie5")
    g.add_edge("actor4", "actor5", "movie5")

    print(g)
    new_g = g.bfs(g, g.get_vertex("Kevin Bacon"))
    print(new_g)
    print(new_g.get_vertex("Jerry Smith"))

if __name__ == "__main__":
    main()