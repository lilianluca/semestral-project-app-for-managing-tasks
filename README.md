# Aplikace pro správu úloh (v konzoli)

## Motivace

Správa úkolů je klíčovou součástí produktivity a organizace práce. Aplikace pro správu úkolů by mohla poskytnout uživatelům efektivní nástroj pro **sledování**, **plánování** a **organizaci** jejich úkolů na jednom místě, což by jim pomohlo zvýšit **produktivitu** a **efektivitu** jejich práce.

## Popis problému

Mnoho lidí (mezi kterými patřím i já) se potýká s problémem, že správa jejich úkolů je nesystematická a nepřehledná. Různé úkoly jsou zaznamenávány na různých místech, jako jsou poznámky na papíře, e-maily nebo různé aplikace, což může vést k jejich ztrátě, neefektivnímu plánování a neorganizované práci. Některém úlohám je nutné se věnovat více, některém zase méně, a každá úloha má určitý termín odevzdání/splňování. Proto, cílem je vytvořit vlastní systém pro správu úloh v programovacím jazyce **Java**.

## Seznam funkcí z pohledu uživatele

1. Vytváření úkolu ve vybranné kategorii

    Každý úkol bude obsahovat:
    1. Pořadí (automaticky)
    2. Název úkolu
    3. Popis úkolu
    4. Datum přidání úkolu (automaticky)
    5. Termín splnění úkolu (datum, čas)
    6. Obtížnost úkolu

2. Výpis úkolů z vybranné kategorie
    
    - Třídění úkolů podle kritérií:
        - Podle názvu
        - Podle data přidání
        - Podle data splnění
        - Podle obtížnosti

3. Výpis kategorií
4. Upravit úkol

    1. Změnit název
    2. Změnit popis
    3. Změnit datum splnění
    4. Změnit obtížnost

5. Odstranění úkol
6. Odstranit kategorii

## Popis struktury vstupních a výstupních souborů

Každá kategorie je reprezentována jako soubor dat (ve formátu .csv - comma separated values), který bude obsahovat:

1. Pořadí (automaticky)
2. Název úkolu (String)
3. Popis úkolu (String)
5. Datum přidání úkolu (LocalDateTime)
5. Termín splnění úkolu (LocalDateTime)
6. Obtížnost úkolu (String)

Jednotlivé atributy úkolu v souboru jsou oddělené čárkou. Každý úkol bude na novém řádku v souboru.
## Class diagram
[![](https://mermaid.ink/img/pako:eNp9VE1vGjEQ_SuWT6DCJUcSIZEuUpGSVGroIRKXqT1LrOzayPa2XUXkt2fMfk1gE072e28-_dhXqZxGuZCqgBAyA3sP5c4K-n2HiHvnazGfL8UWwksDn4Sn-2sDCDF_jN7YvbBQ4jmmMShvDtE421N3TkGRUfqtKVGA1qjT7RNeI-jCWPwgyUyeG1UVsRa6P_assVE4r9E_VOUf9B3-bY-xaWsIn0x7VrnyAB63bhJpuquWOPKxu518PfrKe6jvTIhvaU1vIqULfRmaN8GssMfS_cUzMLomNYOC8_G2fqBSk-n1Gbrqtnihz9j-LsmxRaA2MXVzqsT2OBMW_3XEpTwbnno0ivFjwazL8-gENmnMaOFhiIvInppyF6So2_rnIOaBzBHPqF42eVKv_9N7hnFZmzBMxiwzdNCa5uYGbVWih7SI5bIB16vHp1lzvF9nm9_37eXH6lc2kjSV29jc9SnJ8ehzUNglpJ4-GIXu_H0YzJzDtdw0DGd-ue4bkzNJA5VgNH1JTj3tZHxG-kvIBR015kAhO7mzR5JCRd6urZKL6CucyeqgqUr77ZGLHIqAx3cTAnwa?type=png)](https://mermaid.live/edit#pako:eNp9VE1vGjEQ_SuWT6DCJUcSIZEuUpGSVGroIRKXqT1LrOzayPa2XUXkt2fMfk1gE072e28-_dhXqZxGuZCqgBAyA3sP5c4K-n2HiHvnazGfL8UWwksDn4Sn-2sDCDF_jN7YvbBQ4jmmMShvDtE421N3TkGRUfqtKVGA1qjT7RNeI-jCWPwgyUyeG1UVsRa6P_assVE4r9E_VOUf9B3-bY-xaWsIn0x7VrnyAB63bhJpuquWOPKxu518PfrKe6jvTIhvaU1vIqULfRmaN8GssMfS_cUzMLomNYOC8_G2fqBSk-n1Gbrqtnihz9j-LsmxRaA2MXVzqsT2OBMW_3XEpTwbnno0ivFjwazL8-gENmnMaOFhiIvInppyF6So2_rnIOaBzBHPqF42eVKv_9N7hnFZmzBMxiwzdNCa5uYGbVWih7SI5bIB16vHp1lzvF9nm9_37eXH6lc2kjSV29jc9SnJ8ehzUNglpJ4-GIXu_H0YzJzDtdw0DGd-ue4bkzNJA5VgNH1JTj3tZHxG-kvIBR015kAhO7mzR5JCRd6urZKL6CucyeqgqUr77ZGLHIqAx3cTAnwa)

## Testování
1. Test vstupu **a/n**
    - Input: "a" | Output: "program pokračuje"
    - Input: "n" | Output: "program skončí"
    - Input: "f" | Output: "Špatně zadaná volba. Zadejte prosím a pokud ano n pokud ne: "
2. Test vstupu **čísla**
    - Input: "1" | Output "program pokračuje"
    - Input: "a" | Output "Vámi zadaný vstup není číslo! Zadejte prosím číslo: "
3. Test vstupu **čísla v požadovaném rozsahu, př.: 1-6**
    - Input: "1" | Output "program pokračuje"
    - Input: "7" | Output "Špatně zadaná volba. Zadejte prosím " + 1 + " až " + 6 + ": "
4. Test vstupu **existující kategorie, př.: studium**
    - Input "studium" | Output "program pokračuje"
    - Input "prace" | Output "Zadaná kategorie neexistuje. Zadejte existující kategorii: "
5. Test vstupu **existujícího úkolu, př.: study Math**
    - Input "study Math" | Output "program pokračuje"
    - Input "study History" | Output: "Úkol se zadaným číslem neexistuje. Zadejte číslo existujícího úkolu: "
6. Test vstupu **Data ve správném formátu: (yyyy-MM-dd)**
    - Input "2023-06-01" | Output: "program pokračuje"
    - Input "2023-13-01" | Output: "Datum není ve formátu: (yyyy-MM-dd), zadejte prosím ještě jednou: "
7. Test vstupu **Času ve správném formátu: (HH:mm)**
    - Input "14:59" | Output: "program pokračuje"
    - Input "25:00" | Output: "Čas není ve formátu: (HH:mm), zadejte prosím ještě jednou: "

## Popis fungování externí knihovny - JUnit

JUnit je jedna z nejpopulárnějších knihoven pro testování v Javě. Umožňuje vývojářům psát a spouštět automatické testy, které ověřují správnost chování jejich Java kódu. JUnit je založen na konceptu jednotkových testů, což jsou testy, které kontrolují funkčnost jednotlivých komponent (jednotek) kódu, například metod nebo tříd.

Fungování knihovny JUnit se skládá z několika klíčových prvků:
1. Anotace: JUnit využívá anotace (označení) k identifikaci testovacích metod. Testovací metody jsou označeny anotací @Test a mohou být jednoduše rozpoznány JUnit frameworkem.
2. Testovací třídy: Testy v JUnit jsou organizovány do testovacích tříd, které obsahují jednotlivé testovací metody. Testovací třída je běžná třída Javy, která obsahuje jednu nebo více testovacích metod.
3. Assertion (tvrzení): Tvrzení jsou používána k ověření očekávaných výsledků testů. JUnit poskytuje různé metody tvrzení, které porovnávají očekávanou hodnotu s aktuální hodnotou získanou z testovaného kódu. Například metoda assertEquals(expected, actual) porovnává, zda očekávaná hodnota (expected) je rovna aktuální hodnotě (actual).
4. Spouštěč testů: JUnit poskytuje spouštěč testů, který umožňuje spustit všechny testovací metody v testovací třídě nebo v testovacím balíčku. Spouštěč testů může být spuštěn buď pomocí příkazové řádky, nebo pomocí IDE s podporou JUnit.