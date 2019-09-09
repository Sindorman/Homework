
getParent [] = []
getParent ( (p, kids):xs) = if (length kids) >= 2 then p:(getParent xs)
                            else (getParent xs)