class HashTable:
    '''
    
    '''
    def __init__(self, size=11):
        '''
        
        '''
        self.size = size
        self.slots = [None] * self.size
        
    def put(self, item):
        '''
        Place an item in the hash table.
        Return slot number if successful, -1 otherwise (no available slots, table is full)
        '''
        hashvalue = self.hashfunction(item)
        slot_placed = -1
        if self.slots[hashvalue] is None:
            self.slots[hashvalue] = [item]
            return hashvalue
        elif self.slots[hashvalue].count(item) == 0: # empty slot or slot contains item already
            self.slots[hashvalue].append(item)
            slot_placed = hashvalue
        else:
            index = self.slots[hashvalue].index(item)
            self.slots[hashvalue][index] = item
            slot_placed = hashvalue
        return slot_placed
        
    def get(self, item):
        '''
        returns slot position if item in hashtable, -1 otherwise
        '''
        hashvalue = self.hashfunction(item)
        if self.slots[hashvalue] is None:
            self.slots[hashvalue] = [item]
            return -1
        if self.slots[hashvalue].count(item) == 0: # cannot find item
            return -1
        else:
            return hashvalue
    
    def remove(self, item):
        '''
        Removes item.
        Returns slot position if item in hashtable, -1 otherwise
        '''
        hashvalue = self.hashfunction(item)
        if self.slots[hashvalue].count(item) == 0: # cannot find item
            return -1
        else:
            for i in self.slots[hashvalue]:
                if i == item:
                    self.slots[hashvalue].remove(i)
                    break
            return hashvalue

    def resize(self):
        temp = [[None]] * (self.size * (self.size * 0.25))
        for t in self.slots:
            index = self.slots.index(t)
            temp[index] = t
        self.slots = temp
        self.size = self.size * (self.size * 0.25)

    def hashfunction(self, item):
        '''
        Remainder method
        '''
        if type(item) is int:
            return item % self.size

        key = 0
        for x in item:
            key += ord(x)
        return key % self.size

class Map(HashTable):
    '''
    
    '''
    def __init__(self, size=11):
        '''
        
        '''
        super().__init__(size)
        self.values = [None] * self.size # holds values
        
    def __str__(self):
        '''
        
        '''
        s = ""
        for slot, key in enumerate(self.slots):
            value = self.values[slot]
            s += str(key) + ":" + str(value) + ", "
        return s
    
    def __len__(self):
        '''
        Return the number of key-value pairs stored in the map.
        '''
        count = 0
        for item in self.slots:
            if item is not None:
                count += 1
        return count
    
    def __getitem__(self, key):
        '''
        
        '''
        return self.get(key)

    def __setitem__(self, key, data):
        '''
        
        '''
        self.put(key,data)
        
    def __delitem__(self, key):
        '''
        
        '''
        self.remove(key)
        
    def __contains__(self, key):
        '''
        
        '''
        return self.get(key) != -1

            
    def put(self, key, value):
        '''
        Add a new key-value pair to the map. If the key is already in the map then replace the old value with the new value.
        '''
        slot = super().put(key)
        if slot != -1:
            self.values[slot] = value
        return -1
        
    def get(self, key):
        '''
        
        '''
        slot = super().get(key)
        if slot != -1:
            return self.values[slot]
        return -1
    
    def remove(self, key):
        '''
        Removes key:value pair.
        Returns slot location if item in hashtable, -1 otherwise
        '''
        slot = super().remove(key)
        if slot != -1:
            self.values[slot] = None
        return slot

    def __iter__(self):
        return self.values.__iter__()
    
    def print(self):
        print(self.slots)