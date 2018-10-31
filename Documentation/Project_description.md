## Terminarz wydarzeń (Aplikacja Klient-Serwer)

**Autor:** Damian Nowak

#### Opis aplikacji

Aplikacja Klient-Serwer służąca do przechowywania oraz wyświetlania listy wydarzeń. Aplikacja po stronie Klienta posiada interfejs okienkowy pozwalający wykonywać podstawowe operacje na wydarzeniach:

- dodawanie nowego wydarzenia,
- wyświetlanie informacji o wydarzeniu,
- edytowanie istniejącego wydarzenia,
- usuwanie wydarzenia.

Wszystkie operacje wykonywane po stronie Klienta na bieżąco przesyłane są do Serwera, którego zadaniem jest przechowywanie (zapis oraz odczyt) wydarzeń w dedykowanym do tego pliku pliku.



#### Wymagania aplikacji

Aby móc uruchomić aplikację po stronie Klienta, wymagane jest wcześniejsze uruchomienie Serwera.

> ⚠️ Aplikacja została stworzona w programie **IntelliJ IDEA** na urząceniu z systemem **macOS Hight Sierra**, w przypadku problemów z odczytem pliku `Files/events.data` należy sprawdzić ścieżkę dostępu w stałej `RELATIVE_PATH` w pliku `Helpers/FileInputOutput.class`



#### Opis funkcjonalności użytych w aplikacji

**Klient** - aplikacja po stronie Klienta posiada rozdzieloną logikę komunikacji z serwerem oraz interfejs okienkowy oparty o `SWING`. Komunikacja z serwerem opiera się na pobraniu listy wydarzeń z serwera tuż po połączeniu oraz wysyłaniu listy do serwera po wykonaniu którejkolwiek z operacji `CRUD` na liście wydarzeń. 

![](/Users/damian/WSB/PWSI/src/Project/Documentation/Client.png)

**Serwer** - aplikacja po stronie Serwera posiada logikę odpowiedzialną za komunikację z Klientami oraz operacje na plikach. Dane przesyłane pomiędzy Klientem a Serwerem są serializowaną listą obiektów reprezentujacych pojedyńcze wydarzenie.

Wykorzystane w projekcie możliwości języka `JAVA`:

- interfejs okienkowy w `SWING` wykorzystany po stronie Klienta aplikacji,
- obsługa zdarzeń (`ActionListener` oraz `MouseListener`) wykoszystane w interfejsie okienkowym - obsługa buttonów oraz dwukliku na pojedyńczym elementem listy.
- wykorzystanie konteneru`ArrayList` w celu przechowywania obiektów reprezentujących wydarzenia zarówno po stronie Klienta jak i Serwera
- operacje na plikach (zapis oraz odczyt) zawierających reprezentację listy wydarzeń,
- wykorzystanie wątków w interfejsie aplikacji, reprezentowane poprzed zegar wyświetlany w lewym dolnym roku interfejsu Klienta,
- komunikacja Klient-Serwer w oparciu o sockety pozwalająca na przesyłanie danych o wydarzeniach pomiędzy interfejsem okienkowym a obsługiwanym przez serwer plikiem z danymi.



#### Dokumentacja JavaDoc

Dokumentacja JavaDoc dostępna jest w katalogu `Documentation/JavaDoc/`. Aby otworzyć dokumentację należy uruchomić plik `index.html`