somar [] = []
somar (x:xs) = x+1:somar xs

dobrar [] =[]
dobrar (x:xs) = 2*x:somar xs

map' f [] = []
map' f (x:xs) = f x:map' f xs 
--map'' f xs = [f x|x<-xs]



--trocando f por +1


