lucky :: (Integral a) => a -> String  
lucky 7 = "LUCKY NUMBER SEVEN!"  
lucky x = "Sorry, you're out of luck, pal!"

factorial :: (Integral a) => a -> a 
factorial 0 = 1
factorial n = n * factorial (n - 1)

createStr :: [Int] -> String
createStr [] = ""
createStr (x:xs) = show x ++ createStr xs

createPhoneNumber :: [Int] -> String
createPhoneNumber xs = "(" ++ createStr(beg) ++ ") "
                      ++ createStr(mid) ++ "-" ++ createStr(end)
                      where beg = take 3 xs
                            mid = take 3 (drop 3 xs)
                            end = drop 6 xs

{-createPhoneNumber :: [Int] -> String
createPhoneNumber xs = "(" ++ fst ++ ") " ++ snd ++ "-" ++ thd
  where (fst, rest) = splitAt 3 $ concat $ map show xs
        (snd, thd)  = splitAt 3 rest -}

factors :: Integer -> [Integer]
factors n = [x | x <- [1..n], mod n x == 0]

isPrime :: Integer -> Bool
isPrime 0 = False
isPrime 1 = False
isPrime n = if n < 0 then False else factors n == [1,n]

{-isPrime2 :: Integer -> Integer -> Bool
isPrime2 x y
        | y == 1 = True
        | mod x 2 == 0 = False
        | mod x y == 0 = False
        | otherwise = isPrime2 x (y - 1)

isPrime :: Integer -> Bool
isPrime n
        | n < 2 = False
        | n == 2 = True
        | otherwise = isPrime2 n (n - 1)

isPrime :: Integer -> Bool
isPrime x = null [d | d <- [2..iSqrt x], x `mod` d == 0]
  where iSqrt = floor . sqrt . fromInteger -}


zipWith1 :: (a -> b -> c) -> a -> [b] -> [c]
zipWith1 _ _ [] = []
zipWith1 f x (y:ys) = f x y : zipWith1 f x ys

zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]  
zipWith' _ [] _ = []
zipWith' f (x:xs) ys = zipWith1 f x ys ++ zipWith' f xs ys 

zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]  
zipWith' _ [] _ = []
zipWith' f (x:xs) ys = map (f x) ys ++ zipWith' f xs ys 

scatterGather :: a -> [Int] -> [a] -> [a]
scatterGather a [] _ = []
scatterGather a (x:xs) ys 
            | x < len   = (ys!!x) : fun
            | otherwise = a : fun
            where
              fun = scatterGather a xs ys
              len = length ys

main = do 
  let list' = [5, 5, 5, 6, 4, 7, 6, 4, 2, 7]
  putStrLn "Multiplication value is:"
  print(lucky 7)
  print(factorial 10)
  print(createPhoneNumber list')
  print(isPrime 23423527)
  print(zipWith' (-) [100,200,300] [4,3,2,1])
  print(zipWith' (:) "123" ["abc","def"])
print(scatterGather '_' [0,1,4,1,1,7,2] "abcde")
  print(scatterGather 0 [0,1,4,1,1,7,2] [100,101,102])