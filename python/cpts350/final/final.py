
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
        return None

    return int(conv[i - 1])


def main():
    pass


if __name__ == "__main__":
    main()
