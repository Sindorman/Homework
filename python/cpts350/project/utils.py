from pyeda.inter import expr
from pyeda.inter import expr2bdd
from pyeda.inter import bddvars

def initialize_edges_as_binary(edges: list):
    '''
    Method to initialize edges from 0 to 32 given conditions (y % 32 == (x + 8) % 32) or (y % 32 == (x + 3) % 32) and converts them to binary.
    Parameter list: list of edges.
    '''

    for x in range(0, 32):
        for y in range(0, 32):
            if (y % 32 == (x + 8) % 32) or (y % 32 == (x + 3) % 32):
                edge = convert_edge_to_binary(x, y) # Might as well convert them into binary here instead of looping again.
                edges.append(edge)

def generate_bdd(comparison: list, expressions: list) -> list:
    '''
    Method that converts given expressions into BDD for a given R.
    Parameter list: comparison list and expression list.
    returns: BDD as list for a given R.
    '''

    R = comparison
    for expr in expressions:
        R = R or expr2bdd(expr(expr))

    return R


def convert_edge_to_binary(x, y) -> list:
    '''
    Method that converts edge into 5 bit unsigned.
    Parameter list: x and y of an edge.
    returns: 10 bit list of unsigned representation of an edge.
    '''

    ret_list = []
    for z in (format(x, "05b") + format(y, "05b")):
        ret_list.append(int(z))

    return ret_list

def binary_to_boolean(list_of_binaries: list) -> list:
    '''
    Method that takes list of 10 bit arrays and converts them into boolean expressions.
    Parameter list: list of 10 bit arrays.
    returns: list of boolean expressions.
    '''

    boolean_list = []
    for bit_array in list_of_binaries:
        created_expression = ""
        counter = 0
        i = 0
        j = 0

        for bit in bit_array:
            if counter < 5:
                created_expression += "x" + str([i]) if bit == 1 else "~x" + str([i])
                if i < 5:
                    created_expression += " & "
            else:
                created_expression += "y" + str([j]) if bit == 1 else "~y" + str([j])
                if j < 4:
                    created_expression += " & "
                j += 1

            i += 1
            counter += 1

        boolean_list.append(created_expression)

    return boolean_list

def create_expression_from_list(numbers: list) -> list:
    '''
    Method that creates expresion from a given list.
    Parameter list: list of numbers to create from bit arrays.
    returns: list of boolean expressions.
    '''

    return_list = []

    # Preprocess the list converting to binary
    for number in numbers:
        created_expression = ""
        i = 0
        for bit in format(number, "05b"):
            bit = int(bit)
            created_expression += "x" + str([i]) if bit == 1 else "~x" + str([i])
            if i < 4:
                created_expression += " & "
            i += 1
        return_list.append(created_expression)
    
    return return_list