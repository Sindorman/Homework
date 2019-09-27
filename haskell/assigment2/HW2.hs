-- CptS 355 - Fall 2019 Assignment 2
-- Please include your name and the names of the students with whom you discussed any of the problems in this homework

module HW2
     where


{- 1-  merge2 & merge2Tail & mergeN - 22% -}
--merge2
merge2 :: Ord a => [a] -> [a] -> [a]
merge2 [] list2 = list2
merge2 list1 [] = list1
merge2 (x:xs) (y:ys) | x < y  = (x) : (merge2 xs (y:ys))
                     | otherwise = y : (merge2 (x:xs) ys)

--merge2Tail
merge2Tail :: Ord a => [a] -> [a] -> [a]
merge2Tail [] list2 = list2
merge2Tail list1 [] = list1
merge2Tail list1 list2 = mergeNew list1 list2 []
                           where 
                            mergeNew :: Ord a => [a] -> [a] -> [a] -> [a]
                            mergeNew [] [] l = revAppend l []
                            mergeNew [] (y:ys) l = revAppend ((y:ys) ++ l) []
                            mergeNew (x:xs) [] l = revAppend ((x:xs) ++ l) []
                            mergeNew (x:xs) (y:ys) l | x < y = mergeNew xs (y:ys) (x:l)
                                                     | otherwise = mergeNew (x:xs) ys (y:l)
                            revAppend :: [a] -> [a] -> [a]
                            revAppend [] acc = acc
                            revAppend (x:xs) acc = revAppend xs (x:acc)

--mergeN
mergeN:: Ord a => [[a]] -> [a]
mergeN [] = []
mergeN (x:xs) = foldl merge2 x xs

{- 2 - getInRange & countInRange - 18% -}

--getInRange
getInRange :: Ord a => a -> a -> [a] -> [a]
getInRange v1 v2 [] = []
getInRange v1 v2 list = filter inRange list
                        where inRange a = a > v1 && a < v2

--countInRange 

countInRange :: Ord a => a -> a -> [[a]] -> Int
countInRange v1 v2 [] = 0
countInRange v1 v2 list = foldl (addStuff) 0 (mergeN (map callInRange list))
                          where callInRange l = getInRange v1 v2 l
                                addStuff x y = x + 1


{- 3 -  addLengths & addAllLengths - 18% -}

data LengthUnit =  INCH  Int | FOOT  Int | YARD  Int
                   deriving (Show, Read, Eq)

-- addLengths 
addLengths :: LengthUnit -> LengthUnit -> LengthUnit
addLengths (INCH l1) (INCH l2) = INCH(l1 + l2)
addLengths (FOOT l1) (FOOT l2) = INCH(l1 * 12 + l2 * 12)
addLengths (YARD l1) (YARD l2) = INCH(l1 * 36 + l2 * 36)
addLengths (FOOT l1) (INCH l2) = INCH(l1 * 12 + l2)
addLengths (INCH l1) (FOOT l2) = INCH(l1 + 12 * l2)
addLengths (FOOT l1) (YARD l2) = INCH(l1 * 12 + l2 * 36)
addLengths (YARD l1) (FOOT l2) = INCH(l1 * 36 + l2 * 12)
addLengths (INCH l1) (YARD l2) = INCH(l1 + l2 * 36)
addLengths (YARD l1) (INCH l2) = INCH(l1 * 36 + l2)

-- addAllLengths
addAllLengths :: [[LengthUnit]] -> LengthUnit
addAllLengths list = foldl (addLengths) (INCH 0) (concat list)

{-4 - sumTree and createSumTree - 22%-}

data Tree a = LEAF a | NODE a  (Tree a)  (Tree a) 
              deriving (Show, Read, Eq)

--sumTree
sumTree :: Num p => Tree p -> p
sumTree (LEAF a) = a
sumTree (NODE v t1 t2) = (sumTree t1) + (sumTree t2)

--createSumTree
createSumTree :: Num a => Tree a -> Tree a
createSumTree (LEAF a) = LEAF a
createSumTree (NODE a t1 t2) = NODE ((sumTree t1) + (sumTree t2)) (createSumTree t1) (createSumTree t2)


{-5 - foldListTree - 20%-}
data ListTree a = ListLEAF [a] | ListNODE [(ListTree a)]
                  deriving (Show, Read, Eq)
 
foldListTree :: Num a => (a -> a -> a) -> a -> ListTree a -> a 
foldListTree f b (ListLEAF a) = foldr f b a
foldListTree f b (ListNODE y) = foldr (flip (foldListTree f)) b y

{- 6- Create two tree values :  Tree Integer  and  listTree a ;  Both trees should have at least 3 levels. -}

t6 = NODE 0 (NODE 0 (LEAF 2) (LEAF 3))
                (NODE 0 (NODE 0 (LEAF 5) (LEAF 6)) (LEAF 4))

t7 = ListNODE 
 [ ListNODE [ListLEAF [], ListLEAF []],    
   ListLEAF [], 
   ListNODE [ListLEAF [-9,4,7],ListLEAF [9,8], ListNODE([ListLEAF [4], ListLEAF [3]])], 
   ListNODE [ListLEAF[-1], ListLEAF[]] ]
