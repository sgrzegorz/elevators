# System obsługi wind

### Przyjęte założenia

- Wielokrotne naciskanie przycisku przywołania kabiny dźwigu osobowego daje taki sam wynik jak jednokrotne

- Nowa osoba może się pojawić w dowolnym stepie symulacji i nacisnąć przycisk
- Nie może dochodzić do syutuacji, że jeden z pasażerów będzie czekał na windę w nieskończoność (dojdzie do zagłodzenia)
- Ochrona przed nadużyciem polegającym na naciskaniu obu przycisków przywołania (strzałka w dół i w górę). Winda jedzie w kierunku
wybranym przez użytkownika, aż zrealizuje wszystkie przydzielone jej piętra na trasie. Naciśnięcie obu przycisków naraz może sprawić,
że winda przyjedzie szybciej, ale spowodowałoby to jeszcze większą stratę czasu.
- Obciążenie pracą jest rozkładane na windy równomiernie
- Jeśli windy są podobnie obciążone pracą to zlecenie przyjmuje winda znajdująca się najbliżej


### Rozwiązanie

- System dynamicznie przydziela zadanie-piętro windzie:
  - spośród najmniej zarzuconych pracą wind wybiera tę windę która jest najbliżej piętra, które trzeba obsłużyć.


- Każda winda szereguje przydzielone jej piętra według następującego algorytmu:
  1. poruszaj się w ustalonym kierunku, aż do ostatniego zleconego piętra 
  2. zatrzymuj się po drodze na piętrach, które spełniają warunek (*)
     3. Na piętrze na którym się zatrzymałeś: 
        4. wypuść pasażerów
        4. wpuść nowych pasażerów
  5. Jeśli winda dotarła do ostatniego zlecenia w linii to zawraca i postępuje jak w (1)
  6. Jeśli nie ma więcej zleceń winda nie wykonuje ruchu.
     
(*) Piętro zostało przydzielone tej windzie/w windzie jest pasażer dla którego to piętro docelowe

### Cechy szczególne

- System wypisuje na terminalu co step swój stan
- Do pliku logs.txt są zrzucane logi