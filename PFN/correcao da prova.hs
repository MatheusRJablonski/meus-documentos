--acho q minha nota foi 6, por que se eu acertar eu fico com 7

nprimeiros 0 xs = []
nprimeiros n (x:xs) = x:nprimeiros (n-1) xs
--nem lembro dessa nota 1 


ultimo [x] = x
ultimo (x:xs) = ultimo xs
--fiz diferente mas acho que funcionou nota 1.8 talvez menos pelo meu metodo de antes


replicar n xs = replicar' n 1 xs
replicar' n m [] = []
replicar' n m (x:xs) = if n==m then x:replicar' n 1 xs else x:replicar' n (m+1) (x:xs) 
--que eu saiba ta igual ou quase, nao lembro nota 2


fatiar n m xs = fatiar' n m 0 xs 
fatiar' n m j (x:xs) = if  n==j then if n==m then x:[] else x:fatiar' (n+1) m (j+1) xs else fatiar' n m (j+1) xs
--que eu saiba ta quase igual, eu me lembro de comecar com j=1 nota 1.5


menores [] = []
menores ((x,y):xs) = if y > x then (x,y):menores xs else menores xs 
--errei na parte do xs, certeza 100% do meu erro nota 0 se fosse eu considerava 1 ponto meu erro foi sintaxe, pensei certo fiz errado








fatiar n m xs = fatiar' n m 0 xs 


fatiar' min max count (x:xs) = if  min==count then if min==max then x:[] else x:fatiar' (min+1) max (count+1) xs else fatiar' min max (count+1) xs
