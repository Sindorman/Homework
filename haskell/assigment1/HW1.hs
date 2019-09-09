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
                   else 
                       if head list == item
                       then True
                       else exists item (tail list)
-- 2. listUnion



-- 3. replace



-- 4. prereqFor



-- 5. isPalindrome




-- 6. groupSumtoN



