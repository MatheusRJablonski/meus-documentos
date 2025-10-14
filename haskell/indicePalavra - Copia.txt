import Data.List (sortBy)
-- a) separa todo o texto em linhas
 --lines
-- b) numerar as linhas
funcao1 xs = zip [1..] (lines xs)  

-- c) associar o numero da linha que uma palavra ocorre na frase
funcao2 [] = []
funcao2 ((n,x):xs) = zip (repetir n (length (words x))) (words x) ++ funcao2 xs
repetir x 0 = []
repetir x n = x:repetir x (n-1) 
 -- d) ordenar alfabeticamente as tuplas da função 3 
 -- recebe lista de tuplas e compara os segundos elementos
sortLs x = sortBy (\(_,a) (_,b) -> compare a b) x

--  e) juntar as multiplas ocorrencias das palavras
almalgamate [] = []
almalgamate ((x,y):xs) = (x:almalgamate' y xs, y):almalgamate (filter (\(_,a) -> y/=a) xs) 
--o retorno de almalgamate' é numero
almalgamate' _ [] = []
almalgamate' y ((a,b):xs) = if y == b then a:almalgamate' y xs else almalgamate' y xs
 -- f) eliminar o numero que se repete na lista obtida exemplo ([1,2,2],"algo") -> ([1,2],"algo")
shorten = map (\(ns, w) -> (unique ns, w))
unique = foldr (\x seen -> if x `elem` seen then seen else x : seen) []

--juntando
makeindex txt = shorten . almalgamate. sortLs . funcao2 . funcao1 $ txt

main :: IO ()
main = do
    putStr "Arquivos: "
    arq <- getLine
    txt <- readFile arq
    putStr $ makeindex txt
