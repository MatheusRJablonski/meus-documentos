data Booleano = Ver | Falso deriving Show
nao Ver = Falso
nao Falso = Ver
Ver &&& Ver = Ver
_ &&& _ = Falso

Falso ||| Falso = Falso
_ ||| _ = Ver

data Semana = Dom|Seg|Ter|Qua|Qui|Sex|Sab deriving (Show,Eq,Ord)

proximoDia Seg = Ter
proximoDia Ter = Qua
proximoDia Qua = Qui
proximoDia Qui = Sex
proximoDia Sex = Sab
proximoDia Sab = Dom
proximoDia _ = Seg

util d = d > Dom && d < Sab 

data DuplaInt a b = Par a b deriving Show
primeiro (Par x y) = x
segundo (Par x y) = y

data Lista a = a Ad (Lista a)| Nil deriving Show


tam Nil = 0
tam (x Ad xs) = 1 + tam xs 
