# BuildingInfo
![Build status](https://travis-ci.org/Hound1997c/bildowanie.svg?branch=master)

Struktura danych:
- Lokacja to budynek, poziom, lub pomieszczenie
- Budynek może składać się z poziomów a te z pomieszczeń
- Każda lokalizacja jest charakteryzowana przez:
    - id – unikalny identyfikator
    - name – opcjonalna nazwa lokalizacji
- Pomieszczenie dodatkowo jest charakteryzowane przez:
    - area = powierzchnia w m^2
    - cube = kubatura pomieszczenia w m^3
    - heating = poziom zużycia energii ogrzewania (float)
    - light – łączna moc oświetlenia


Temp change
