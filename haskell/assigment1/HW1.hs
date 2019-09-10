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


-- 4. prereqFor



-- 5. isPalindrome




-- 6. groupSumtoN



