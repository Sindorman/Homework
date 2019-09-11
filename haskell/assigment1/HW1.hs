-- CptS 355 - Fall 2019 Assignment 1
-- Please include your name and the names of the students with whom you discussed any of the problems in this homework

-- Mykhailo Bykhovtsev

module HW1
     where

import Data.Char (toUpper)

-- 1. exists
-- Exists function that checks if an element is in the list
exists :: Eq t => t -> [t] -> Bool
exists item list = if list == []
                   then False
                   else if head list == item
                        then True
                        else exists item (tail list)
-- 1b 
-- Because we restrict the typeclass of t to Eq, so any t's can be evaluated as equal or not equal.

-- 2. listUnion
-- ListUnion function that gives you a union of two lists works same as ++, but probably less efficient(because if they are lists with pointers 
-- then connecting tail of list one to head of list two is most effiecient)
listUnion :: Eq a => [a] -> [a] -> [a]
listUnion list1 list2 = if list2 == []
                        then list1
                        else if exists (head list2) list1
                             then listUnion list1 (tail list2)
                             else listUnion ((head list2):list1) (tail list2)


-- 3. replace
-- I had to add Eq t2 because the check required list to have typeclass.
-- I tried using "if index > length list" but it required t1 to be Int and not t1. So this function is not efficient for index out of bounds with large list.
replace :: (Eq t1, Num t1, Eq t2) => t1 -> t2 -> [t2] -> [t2]
replace index value list = if list == []
                           then list
                           else if index /= 0
                                then (head list):replace (index - 1) value (tail list)
                                else value:(tail list)


prereqsList = [("Cpts122" , ["CptS121"]), ("CptS132" , ["CptS131"]), ("CptS223" , ["CptS122", "MATH216"]), ("CptS233" , ["CptS132", "MATH216"]), 
               ("CptS260" , ["CptS223", "CptS233"]), ("CptS315" , ["CptS223", "CptS233"]), ("CptS317" , ["CptS122", "CptS132", "MATH216"]), 
               ("CptS321" , ["CptS223", "CptS233"]), ("CptS322" , ["CptS223","CptS233"]), ("CptS350" , ["CptS223","CptS233", "CptS317"]), 
               ("CptS355" , ["CptS223"]), ("CptS360" , ["CptS223","CptS260"]),("CptS370" , ["CptS233","CptS260"]),
               ("CptS427" , ["CptS223","CptS360", "CptS370", "MATH216", "EE234"])
              ]

-- 4. prereqFor
prereqFor :: (Eq a, Eq t) => [(a, [t])] -> t -> [a]
prereqFor list course = let prereq = []
                        in if list == []
                           then prereq
                           else if exists course (snd (head list)) 
                                then listUnion (prereqFor (tail list) course) ((fst (head list)):prereq)
                                else prereqFor (tail list) course

-- 5. isPalindrome




-- 6. groupSumtoN



