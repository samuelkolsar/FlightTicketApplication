# EstAviation
#### Lennupileti broneerimisrakendus

Rakenduse käivtamiseks saab kasutada sisestades käsud:
```
git clone https://github.com/samuelkolsar/FlightTicketApplication.git
cd FlightTicketApplication
docker-compose up
```
Peale käskude sisestamist peaks rakendus avanema [siin](http://localhost)
Esmasel avamisel genereerib rakendus avamise päevast järgmise nädala sisse 1000 erinevat lendu, seega kulub esmakäivitamisel veidi rohkem aega.
Kriitiline on lasta lennud ära genereerida ja mitte rakendust vahepeal sulgeda.

### Lendude otsing ja filtreerimine

- Esmalt tuleb sisestada Lähte-ja sihtkoha riik või linn (Näidisandmetes kasutuses ainult pealinnad)
- Siis valida reisijate arv
- Lennu istkoha klass (esimene klass/äriklass/turistiklass)
- Ning lennu väljumiskuupäev

Kui peale otsingut on lennud kuvatud, siis on võimalik lende omakorda hinna abil filtreerida kasutades liugurriba.


## Töö kirjeldus

Projektile kulus kokku ca 25-30 tundi.

Peamisi raskusi pakkus projekti struktuuri koostamine sellisel moel, et seda oleks võimalik hiljem heade ideede puhul jooksvalt edasi arendada.
Hetkel jäi lahendamata istekohtade soovitamine ning mind ennast jäi häirima esmakasutusel lendude sisselaadimine ja lennukuupäevade valimisel võiks olla valikus ka vahemik.

Minu ideed antud puudustele:
- Istekohtade soovitamise implementeerimiseks tuleks teha frontendis funktsioon, mis leiab vastavalt istme atribuutidele ning reisijate arvule sobivaid kombiatsioone ja näiteks valib nendest kõige esimese mis antud nõuded täidab.
- Andmed oleks saanud sisse lugeda CSV failist, mis oleks ilmselt kiirem moodus kui jooksutada koodi, mis lende ise kohapeal genereerib.
- Vahemike valimisel tuleb leida/leiutada frontendi parem kalendri kasutajaliides, mis võimaldab valida vahemikke ja hiljem backendis luua vastav andmebaasi päring.


Kasutatud materjal:

Spring Booti raamistiku kasutamisel sain ideid Mosh Hamedani materjalidest.

Vue.js aluseks kasutasin Tartu Ülikooli aines Veebirakenduse Loomine (LTAT.05.004) loodud rakendusi.


Kokkuvõte:

Õppisin töö käigus väga palju uut ja huvitavat, näiteks kuidas paigutada rakendus Dockeri konteinerisse ning kuidas frontendis erinevaid komponente omavahel üheskoos töötama panna.
