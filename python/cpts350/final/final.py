alphabet = [(1,1,1), (1,1,0), (1,0,1), (1,0,0), (0,1,1), (0,1,0), (0,0,1), (0,0,0)]

def DFS(FA: dict, start: tuple, end: tuple, path=[]):
    '''
    Recursive DFS that is implemented from https://www.python.org/doc/essays/graphs/
    Parameter list: FA1 as dict, start, end, and path.
    returns: path as list
    '''

    path += [start]

    # We are at the end
    if start[0] == end:
        return path

    if start[0] not in FA:
        return None
    
    for state in FA[start[0]]:
        if state not in path:
            new_path = DFS(FA, state, end, path)

            if new_path:
                return new_path

    return None

def get_C_max(constraint: list) -> int:
    '''
    Method that will return Cmax for a given constraints.
    Parameter list: list of variables.
    returns: Cmax
    '''

    # trying positives
    res1 = 0
    for x in constraint:
        c = x
        d = 1
        if c < 0:
            d = 0

        res1 = res1 + c  * d

    res1 += 1

    # trying negatives
    res2 = 0
    for x in constraint:
        c = x
        d = 1

        if c > 0:
            d = 0

        res2 = res2 + c  * d

    res = max(res1, abs(res2))

    return res

def convert_C_to_binary(c: int) -> str:
    '''
    Method that will convert int to binary. removes 0's on left
    Parameter list: integer.
    returns: string representing binaries.
    '''
    binary = f'{c:08b}'

    for n in range(len(binary)):
        if binary[n] == '1':
            return binary[n:]

    return ""

def get_K(num: int) -> int:
    '''
    Method that will return Kc of a given int.
    Parameter list: integer.
    returns: Kc
    '''
    c = convert_C_to_binary(num)
    return len(c)

def get_bi(c: int, i: int) -> int:
    '''
    Method that will return i-th b.
    Parameter list: integer, i.
    returns: i-th b
    '''
    conv = convert_C_to_binary(c)
    if i - 1 >= len(conv) or i < 1:
        return 0

    return int(conv[i - 1])


def create_FA(description: str) -> dict:
    '''
    Method that will construct FA M from given description.
    Parameter list: description as a list.
    returns: dictionary that represents FA
    '''
    k = get_K(description[3])
    Cmax = get_C_max(description[:-1])

    print(f"K={k}, Cmax={Cmax}")

    res_FA = {}

    # Since it is from -Cmax <= carry <= Cmax. And Range needs +1
    for carr in range(-1 * Cmax, Cmax + 1):
        # Since it is from 1 <= i <= Kc. Range needs + 1
        for i in range(1, k+2):
            # Essentially nested looping since we have carry and carry' and i and i'
            for carr_prime in range(-1 * Cmax, Cmax + 1):
                for i_prime in range(1, k+2):
                    for x in alphabet:
                        # Setup our states
                        m_state = (carr, i)
                        m_state_prime = (carr_prime, i_prime)

                        # R = C1a1 + C2a2 + C3a3 + bi + carry.
                        R = 0
                        for y in range(0, 3):
                            R += description[y] * x[y]

                        R += get_bi(description[3], i) + carr

                        if (R % 2 == 0):
                            if(i_prime == i or i_prime == i + 1):
                                if(carr_prime == R / 2):
                                    if m_state in res_FA:
                                        res_FA[m_state].append((m_state_prime, x))
                                    else:
                                        res_FA[m_state] = [(m_state_prime, x)]

    return res_FA

def get_cartesian_product(FA1: dict, FA2: dict) -> dict:
    '''
    Method that will make Cartesian product of two FA's.
    Parameter list: FA1 as dict, FA2 as dict.
    returns: dictionary that represents Cartesian Product FA1 x FA2
    '''
    ret_FA = {}
    # Nested loop of iterating over each node from both FA's
    for key1, value1 in FA1.iteritems():
        for key2, value2 in FA2.iteritems():
            
            current_state = (key1[0] * key2[0], key1[1] * key2[1])

            for x in value1:
                for y in value2:

                    # Make sure we connect same states
                    if x[1] == y[1]:
                        carr = x[0][0] * y[0][0]

                        temp = x[0][1] * y[0][1]

                        new_state = (carr, temp)

                        value = (new_state, x[1])

                        # Check if key is already in dict
                        if current_state in ret_FA:

                            # Check if the node with correct triplet is in already
                            if value not in ret_FA[current_state]:
                                ret_FA[current_state].append(value)
                        else:
                            ret_FA[current_state] = [value]

    return ret_FA

def main():
    # 3x1 − 2x2 − x3 + 3 = 0
    example_expression1 = [3, -2, 1, 5]

    # 6x1 − 4x2 + x3 + 3 = 0
    example_expression2 = [6, -4, 2, 9]

    FA1 = create_FA(example_expression1)
    FA2 = create_FA(example_expression2)

    # The accepting state is [carry = 0, i = KC + 1].

    end = (0, (get_K(example_expression1[3]) + 1) * (get_K(example_expression2[3]) + 1))
    print(f"end={end}")

    cartesian_product = get_cartesian_product(FA1, FA2)

    # Use DFS to get path
    result = DFS(cartesian_product, ((0, 1), (0, 0, 0)), end)

    print(f"path:\n{result}")

    # Extract actual result and convert it back

    temp = []
    for x in result:
        temp.append(x[1])

    temp.reverse()
    int1 = int2 = int3 = ""

    for x in temp:
        int1 += str(x[0])
        int2 += str(x[1])
        int3 += str(x[2])

    int1, int2, int3 = int(int1, 2), int(int2, 2), int(int3, 2)

    print("converted result = ({}, {}, {})".format(int1, int2, int3))

    print(3 * int1 - 2 * int2 + int3 + 5)

if __name__ == "__main__":
    main()
