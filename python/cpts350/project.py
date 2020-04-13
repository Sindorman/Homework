import utils

def compare_bdd(closure1: list, closure2: list) -> list:
    '''
    Method that will compare two colsures.
    Parameter list: two closures to compare.
    returns: resulting list of composing.
    '''

    X = utils.bddvars('x', 5)
    Y = utils.bddvars('y', 5)
    Z = utils.bddvars('z', 5)

    for x in range(0, 5):
        closure1 = closure1.compose(X[x], Z[x])
        closure2 = closure2.compose(Z[x], Y[x])

    return_list = closure1 and closure2

    return return_list.smoothing(Z)

def computer_transitives(expr_RR: list) -> list:
    '''
    Method that will compute transitive closure gives expression RR.
    Parameter list: RR of the expression.
    returns: transitive closure.
    '''

    closure1 = []
    closure2 = []

    i = 1

    while True:
        closure1 = expr_RR

        # closure1 V R
        closure2 = closure1 or compare_bdd(closure1, closure2)
        if closure2.equivalent(closure1):
            break

        i += 1

    print(i)
    return (closure2, i)

if __name__ == "__main__":
    X = utils.bddvars('x', 5)
    Y = utils.bddvars('y', 5)

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
    #expr_RR = utils.generate_bdd(expr_R, boolean_expr[2:])
    #prime_RR = utils.generate_bdd(prime_R, prime_booleans[2:])
    #even_RR = utils.generate_bdd(even_R, even_booleans[2:])

    # Computer transitive colsure RR2star for RR2.
    print("Step 5. Compute the transitive closure RR2star of RR2. Herein, RR2star encodes the set of all node pairs such that one can reach the other in even number of steps.")

    closure, i = computer_transitives(expr_R)

    # Check if our statement is true
    print("Step 6. Check if statement \" for each node u in [prime], there is a node v in [even] such that u can reach v in even number of steps.\" is true")
    result = (closure and expr_R).smoothing(Y) # Same as there exists
    result_bool = not (not (result or not prime_R).smoothing(X))
    print(f"Statement is: {result_bool}")
    print(f"v={i}")