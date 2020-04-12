import utils

X = utils.bddvars('x', 5)
Y = utils.bddvars('y', 5)
Z = utils.bddvars('z', 5)

if __name__ == "__main__":
    # Initialize our edges
    edges = []
    utils.initialize_edges_as_binary(edges)
    print(f"Step 1. Edges initialized as binary:\n {edges}")

    #Convert created edges into boolean expressions.
    print("Step 2. Converted edges to boolean expression.")
    boolean_expr = utils.binary_to_boolean(edges)

    even_nums = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30]
    prime_nums = [3, 5, 7, 11, 13, 17, 19, 23, 29, 31]

    # Create bool expressions for prime and even numbers.
    print("Step 2(a). Create boolean expression for prime and even numbers.")
    even_booleans = utils.create_expression_from_list(even_nums)
    print(f"Even:\n{even_booleans}")
    prime_booleans = utils.create_expression_from_list(prime_nums)
    print(f"Prime:\n{prime_booleans}")

    # Create boolean formulas R with 10 boolean variables.
    print("Step 3. Create Boolean formulas R with 10 boolean variables.")
    expr_R = utils.expr2bdd(utils.expr(boolean_expr[0]))
    prime_R = utils.expr2bdd(utils.expr(prime_booleans[0]))
    even_R = utils.expr2bdd(utils.expr(even_booleans[0]))

    # Convert boolean formulas into BDD.
    print("Step 4. Convert expressions into BDDs")
    expr_RR = utils.generate_bdd(expr_R, boolean_expr[2:])
    prime_RR = utils.generate_bdd(prime_R, prime_booleans[2:])
    even_RR = utils.generate_bdd(even_R, even_booleans[2:])

    # Computer transitive colsure RR2star for RR2.
    print("Step 5. Compute the transitive closure RR2star of RR2. Herein, RR2star encodes the set of all node pairs such that one can reach the other in even number of steps.")

    closure1 = []
    closure2 = []

    while True:
        closure1 = expr_RR
        closure2 = closure1 or utils.compare_bdd(closure1, closure2)

        if closure2.equivalent(closure1):
            break

    # Check if our statement is true
    print("Step 6. Check if statement \" for each node u in [prime], there is a node v in [even] such that u can reach v in even number of steps.\" is true")
    result = (closure2 and even_RR).smoothing(Y)
    result = not (not (result or not prime_RR).smoothing(X))
    print(f"Statement is: {result}")