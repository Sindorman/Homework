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
-- ListUnion function that gives you a union of two lists works same as ++, but does not include duplicates.
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
-- Function that given list of all classes and their prerequisites and course will return list of courses to which this course is a prereq.
-- I had to add Eq t because the check required list to have typeclass.
prereqFor :: (Eq a, Eq t) => [(a, [t])] -> t -> [a]
prereqFor list course = let prereq = []
                        in if list == []
                           then prereq
                           else if exists course (snd (head list)) 
                                then listUnion (prereqFor (tail list) course) ((fst (head list)):prereq)
                                else prereqFor (tail list) course

-- 5. isPalindrome
-- Function that checks if a given string is a palindrome. Ignores spaces, case-sensitivity.
isPalindrome :: [Char] -> Bool
isPalindrome word = checkPalindrome word (reverse word)
                    where checkPalindrome w r = if w /= [] && r /= []
                                                then if head w == ' '
                                                          then checkPalindrome (tail w) r
                                                     else if head r == ' '
                                                          then checkPalindrome w (tail r)
                                                     else if (toUpper (head w)) /= (toUpper (head r))
                                                          then False
                                                     else checkPalindrome (tail w) (tail r)
                                                else True
-- 6. groupSumtoN

