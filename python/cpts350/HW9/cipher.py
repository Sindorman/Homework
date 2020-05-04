import binascii

from math import *

babyascii = ['A', 'E', 'G', 'I', 'O', 'R', 'T', 'X', '!']

def inverse(x, m):
    a, b, u = 0, m, 1
    while x > 0:
        q = b
        x, a, b, u = b % x, u, x, q - q * u
    if b == 1:
        return a % m
    print("must be coprime")
    exit(1)


if __name__ == "__main__":
    n = 10539750919    # p*q = modulus
    e = 49

    ciphered = "ITG!AAEXEX IRRG!IGRXI OIXGEREAGO".split()
    for s in ciphered:
        asciiciph = ""
        for c in s:
            asciiciph += str(babyascii.index(c) + 1)
        asciiciph = int(asciiciph)
        print(asciiciph)

        res = floor(sqrt(n))
        previous = m = n % res
        res = res - 2
        while(m != 0):
            previous = m
            m = n % res
            res = res - 2
        p = res + 2
        q = round(n / p)
        print(f"p={p} q={q}")
        d = inverse(e, ((p - 1) * (q - 1)))
        print(d)

        print(f"ascii={asciiciph} d={d} n={n}")
        #print(pow(int(asciiciph), d) % n)

        descioph = "98483"
        message = ""
        for c in descioph:
            print(int(c) - 1)
            message += str(babyascii[int(c)])
        print(message)