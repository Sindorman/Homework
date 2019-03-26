'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019 03/24
Programming Project #4
03/24/19

Description: Map class that is an associative map.
'''

class Node:
    '''
    A class representing a node. 
    Node has key, and value.
    '''
    def __init__(self, key, value):
        self.key = key
        self.value = value

    def __eq__(self, other):
        '''
        Method used to compare nodes.
        Parameter list: Node as node class.
        return: boolean.
        '''
        if type(other) is int:
            return False
        if other is None:
            return False
        return self.key == other.key

    def __str__(self):
        '''
        getting string representation of a Node.
        returns: node as string.
        '''
        return "{}:{}".format(self.key, self.value)

    def __repr__(self):
        '''
        used for print()
        returns: node as string.
        '''
        return self.__str__()

class Map:
    '''
    A class representing a Map. 
    map stores slots as two dimentional array and size as an integer.
    '''
    def __init__(self, size=20011):
        '''
        Creates new Map instance
        parameter list: (optional) size of the map to create
        '''
        self.size = size
        self.slots = [None] * self.size
        
    def put(self, key, item):
        '''
        Place an item in the hash table.
        parameters list: key as anything and item as anything.
        '''
        hashvalue = self.hashfunction(key)
        n = Node(key, item)
        if self.slots[hashvalue] is None:
            self.slots[hashvalue] = [n]
        elif self.slots[hashvalue].count(n) == 0:
            self.slots[hashvalue].append(n)
        else:
            index = self.slots[hashvalue].index(n)
            self.slots[hashvalue][index] = n
        
    def get(self, key):
        '''
        Method of getting value for given key.
        parameters list: key as anything.
        returns: item that is stored, -1 if it is not present.
        '''
        hashvalue = self.hashfunction(key)
        n = Node(key, None)
        if self.slots[hashvalue] is None:
            return -1
        if self.slots[hashvalue].count(n) == 0: # cannot find item
            return -1
        else:
            for t in self.slots[hashvalue]:
                if t is None:
                    continue
                if t == n:
                    return t.value
            return -1
    
    def remove(self, key):
        '''
        Removes item.
        parameter list: key as anything.
        return: item removed or -1 otherwise if not present.
        '''
        hashvalue = self.hashfunction(key)
        n = Node(key, None)
        if self.slots[hashvalue].count(n) == 0: # cannot find item
            return -1
        else:
            for i in self.slots[hashvalue]:
                if i == n:
                    self.slots[hashvalue].remove(i)
                    return n
        return -1
    
    def __len__(self):
        '''
        accesing length we use.
        returns: length as integer.
        '''
        return self.size

    def __contains__(self, key):
        '''
        Method to check if a given thing is in the Map.
        parameters: key as anything.
        returns: boolean if present.
        '''
        n = self.get(key)
        if n == -1:
            return False
        else:
            return True

    def __getitem__(self, key):
        '''
        used for accessing items via brackets.
        parameters list: key as anything.
        returns: item if present, -1 otherwise.
        '''
        self.get(key)

    def __setitem__(self, key, value):
        '''
        Place an item in the hash table. Used for brackets.
        parameters list: key as anything and item as anything.
        '''
        self.put(key, value)

    def __delitem__(self, key):
        '''
        Delete an item. Used for brackets.
        parameters list: key as anything.
        '''
        self.remove(key)

    def hashfunction(self, item):
        '''
        Function used for hashing.
        parameters list: key as integer or string
        returns: hash value as an integer
        '''
        if type(item) is int:
            return item % self.size

        key = 0
        for x in item:
            key += ord(x)
        return key % self.size
    
    def print(self):
        '''
        Printing method.
        '''
        print(self.slots)
