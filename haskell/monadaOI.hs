main = do putStr "Arquivo: "
          arq <- getLine
          txt <- readFile arq
          imprimir (lines txt)  


imprimir [] = putStr "\n\n\n"
imprimir (l:ls) = do putStr(l++"\n\n")
                     imprimir ls

