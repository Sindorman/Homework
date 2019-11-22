import unittest
from HW5 import *
class HW4Sampletests_part2(unittest.TestCase):
    
    def setUp(self):
        opstack[:] = []
        dictstack[:] = []
        self.input1 = """
                /square {dup mul} def 
                [-5 -4 3 -2 1] dup aload length 0 exch -1 1 
                {pop exch square add} for 
                55 eq stack
                """

        self.input2 ="""
                    /n 5 def
                    /fact {
                        0 dict begin
                        /n exch def
                        n 2 lt
                        { 1}
                        {n 1 sub fact n mul }
                        ifelse
                        end 
                    } def
                    n fact stack
                    """

        self.input3 = """
                    /fact{
                        0 dict
                        begin
                            /n exch def
                            1
                            n -1 1 {mul} for
                        end
                    } def
                    6 fact stack
                    """

        self.input4 = """
                    /sumArray { 0 exch aload length -1 1 {pop add} for } def
                    /x 5 def
                    /y 10 def 
                    [1 2 3 4 x] sumArray
                    [x 7 8 9 y] sumArray
                    [y 11 12] sumArray
                    [0 0 0] astore
                    stack 
                """

        self.input5 = """
                    1 2 3 4 5 count copy 15 1 1 5 {pop exch sub} for 0 eq  
                    stack 
                    """

        self.input6 = """
                    /pow2 {1 dict begin 
                            /x exch def 
                            1 x -1 1 {pop 2 mul} for  
                        end } def
                    [1 2 3 4 5 6 7] dup /A exch def
                    0 1 A length 1 sub { /n exch def A n get pow2 /x exch def A n x put } for 
                    A
                    stack
                    """

    def test_parsing1(self):
        output = ['/square', ['dup', 'mul'], 'def', (5, [-5, -4, 3, -2, 1]), 'dup', 'aload', 'length', 0, 'exch', -1, 1, ['pop', 'exch', 'square', 'add'], 'for', 55, 'eq', 'stack']
        self.assertEqual(parse(tokenize(self.input1)), output)
    
    def test_parsing2(self):
        output = ['/n', 5, 'def', '/fact', [0, 'dict', 'begin', '/n', 'exch', 'def', 'n', 2, 'lt', [1], ['n', 1, 'sub', 'fact', 'n', 'mul'], 'ifelse', 'end'], 'def', 'n', 'fact', 'stack']
        self.assertEqual(parse(tokenize(self.input2)), output)
    
    def test_parsing3(self):
        output = ['/fact', [0, 'dict', 'begin', '/n', 'exch', 'def', 1, 'n', -1, 1, ['mul'], 'for', 'end'], 'def', 6, 'fact', 'stack']
        self.assertEqual(parse(tokenize(self.input3)), output)
    
    def test_parsing4(self):
        output = ['/sumArray', [0, 'exch', 'aload', 'length', -1, 1, ['pop', 'add'], 'for'], 'def', '/x', 5, 'def', '/y', 10, 'def', (5, [1, 2, 3, 4, 'x']),
                    'sumArray',(5, ['x', 7, 8, 9, 'y']), 'sumArray', (3, ['y', 11, 12]), 'sumArray', (3, [0, 0, 0]), 'astore', 'stack']
        self.assertEqual(parse(tokenize(self.input4)), output)
    
    def test_parsing5(self):
        output = [1, 2, 3, 4, 5, 'count', 'copy', 15, 1, 1, 5, ['pop', 'exch', 'sub'], 'for', 0, 'eq', 'stack']
        self.assertEqual(parse(tokenize(self.input5)), output)

    def test_parsing6(self):
        output = ['/pow2', [1, 'dict', 'begin', '/x', 'exch', 'def', 1, 'x', -1, 1, ['pop', 2, 'mul'], 'for', 'end'], 'def', (7, [1, 2, 3, 4, 5, 6, 7]), 'dup', '/A',
                    'exch', 'def', 0, 1, 'A', 'length', 1, 'sub', ['/n', 'exch', 'def', 'A', 'n', 'get', 'pow2', '/x', 'exch', 'def', 'A', 'n', 'x', 'put'], 'for',
                    'A', 'stack']
        self.assertEqual(parse(tokenize(self.input6)), output)


    def test_interpretSPS(self):
        opstack[:] = []
        dictstack[:] = []
        interpreter(self.input1)
        self.assertEqual(opstack, [(5, [-5, -4, 3, -2, 1]), True])

    def test_interpretSPS2(self):
        opstack[:] = []
        dictstack[:] = []
        interpreter(self.input2)
        self.assertEqual(opstack, [120])
    '''
    def test_interpretSPS3(self):
        opstack[:] = []
        dictstack[:] = []
        interpreter(self.input3)
        self.assertEqual(opstack, [720])

    def test_interpretSPS4(self):
        opstack[:] = []
        dictstack[:] = []
        interpreter(self.input4)
        self.assertEqual(opstack, [(3, [15, 39, 33])])

    def test_interpretSPS5(self):
        opstack[:] = []
        dictstack[:] = []
        interpreter(self.input5)
        self.assertEqual(opstack, [1, 2, 3, 4, 5, True])
    
    def test_interpretSPS6(self):
        opstack[:] = []
        dictstack[:] = []
        interpreter(self.input6)
        self.assertEqual(opstack, [(7, [2, 4, 8, 16, 32, 64, 128]), (7, [2, 4, 8, 16, 32, 64, 128])])
    '''

if __name__ == '__main__':
    unittest.main()

