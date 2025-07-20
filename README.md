# Projekt Bazy Danych w MySQL do zarządzania pieczarkarnią

## Opis projektu
Celem projektu jest zaprojektowanie i utworzenie bazy danych w środowisku MySQL, która wspiera funkcjonalność systemu obsługującego procesy biznesowe (np. zarządzanie klientami, zamówieniami, produktami). Projekt obejmuje modelowanie struktury bazy danych, definiowanie relacji między tabelami oraz implementację odpowiednich mechanizmów w celu zapewnienia integralności danych.

Bazy Danych 2

Semestr V 

Informatyka Techniczna '22

## Dokumentacja i raport z projektu
[Raport w Latex]([https://www.overleaf.com/project/670420f1d05a1e187de2e993](https://www.overleaf.com/read/djkgyyxzbjht#27a677))

## Struktura bazy danych

![Diagram ERD](https://github.com/IwoStaykov/BD_Projekt/blob/main/Kompletny%20Diagram%20ERD.jpg)

## Wymagania:
Utwórz kontener MySQL w Dockerze
```
docker run --name BDProj -e MYSQL_ROOT_PASSWORD=toor -p 5001:3306 -d mysql:8.0.39
```
Dodaj schemat do bazy danych o nazwie "Grzyby".
```
create schema Grzyby;
```
## Uruchomienie
Aplikacja posiada interfejs działający na serwerze Tomcat na porcie 8080 z ścieżką kontekstu '/'.
Aby ją otworzyć, wystarczy użyć adresu URL http://localhost:8080/ w przeglądarce internetowej.


