class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value

    def __eq__(self, other):
        if type(other) is int:
            return False
        if other is None:
            return False
        return self.key == other.key
    def __str__(self):
        return "{}:{}".format(self.key, self.value)
    def __repr__(self):
        return self.__str__()
class Map:
    '''
    
    '''
    def __init__(self, size=11):
        '''
        
        '''
        self.size = size
        self.slots = [None] * self.size
        
    def put(self, key, item):
        '''
        Place an item in the hash table.
        Return slot number if successful, -1 otherwise (no available slots, table is full)
        '''
        hashvalue = self.hashfunction(key)
        n = Node(key, item)
        if self.slots[hashvalue] is None:
            self.slots[hashvalue] = [n]
            #print("putting")
        elif self.slots[hashvalue].count(n) == 0: # empty slot or slot contains item already
            self.slots[hashvalue].append(n)
            #print("putting")
        else:
            index = self.slots[hashvalue].index(n)
            self.slots[hashvalue][index] = n
        
    def get(self, key):
        '''
        returns slot position if item in hashtable, -1 otherwise
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
                    return t
            return -1
    
    def remove(self, key):
        '''
        Removes item.
        Returns slot position if item in hashtable, -1 otherwise
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

    def resize(self):
        temp = [[None]] * (self.size * (self.size * 0.25))
        for t in self.slots:
            index = self.slots.index(t)
            temp[index] = t
        self.slots = temp
        self.size = self.size * (self.size * 0.25)

    
    def __len__(self):
        return self.size

    def __contains__(self, key):
        n = self.get(key)
        if n == -1:
            return False
        else:
            return True

    def __getitem__(self, key):
        self.get(key)

    def __setitem__(self, key, value):
        self.put(key, value)

    def __delitem__(self, key):
        self.remove(key)

    def hashfunction(self, item):
        '''
        Remainder method
        '''
        if type(item) is int:
            return item % self.size

        key = 0
        for x in item:
            key += ord(x)
        #print("hashing: {}".format(key % self.size))
        return key % self.size
    
    def print(self):
        print(self.slots)
        '''
        text = "["
        for k in self.slots:
            if k is None:
                continue
            for t in k:
                if t is None:
                    continue
                text += str(t) + ", "
        text += "]"
        print(text)
        '''
