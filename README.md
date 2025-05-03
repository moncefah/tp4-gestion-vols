# TP4 - Implémentation du schéma UML

Ce projet implémente le modèle UML de gestion de vols et réservations défini dans le TP4.

## Structure du projet

- `src/main/java/com/example/vol` : classes métier (`Compagnie`, `Vol`, `Aeroport`, `Ville`, `Escale`, `Client`, `Reservation`, `Passager`, `Main`).
- `src/test/java/com/example/vol` : tests unitaires JUnit 5.
- `build.gradle`, `settings.gradle` : configuration Gradle.

## Prérequis

- Java 8 ou supérieur
- Gradle

## Compilation et exécution

```bash
./gradlew build
./gradlew test
```

## Exemple d'exécution

```bash
./gradlew run --args=""
```

Le `Main` crée et relie des entités pour démonstration :
- Création d'une compagnie, d'un vol, d'un client et de sa réservation.
- Ouverture du vol, calcul de durée, confirmation de réservation.
