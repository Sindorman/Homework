-- CptS 355 - Fall 2019 Assignment 1
-- Please include your name and the names of the students with whom you discussed any of the problems in this homework

-- Mykhailo Bykhovtsev

module HW1
     where

import Data.Char (toUpper)

-- 1. exists

exists :: Eq t => t -> [t] -> Bool
exists item list = if list == []
                   then False
                   else if head list == item
                        then True
                        else exists item (tail list)
-- 2. listUnion

listUnion :: Eq a => [a] -> [a] -> [a]
listUnion list1 list2 = if list2 == []
                        then list1
                        else if exists (head list2) list1
                             then listUnion list1 (tail list2)
                             else listUnion ((head list2):list1) (tail list2)


-- 3. replace



-- 4. prereqFor



-- 5. isPalindrome




-- 6. groupSumtoN



