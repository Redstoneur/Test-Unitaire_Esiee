# Project Test-Unitaire_Esiee

## Description du projet

Ce projet est une application web composée d’un backend en Java (Spring Boot) et d’un frontend en Vue.js, conçue pour
gérer des échanges d’objets entre utilisateurs. Le backend expose diverses API REST pour créer, mettre à jour et
supprimer des objets, ainsi que pour gérer les échanges et la gestion des utilisateurs. Le frontend permet d’interagir
avec ces API et inclut des tests end-to-end automatisés avec Cypress.

## Prérequis

- Java 17 et Maven
- Node.js 22 et npm
- Docker (pour la version Dockerisée)

## Lancement du projet

### Sans Docker

#### 1. Backend

1. Ouvrir un terminal dans le répertoire `Backend`.
2. Compiler et lancer le projet avec Maven :
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

Le serveur devrait démarrer sur le port par défaut (souvent 3000).

#### 2. Frontend

1. Ouvrir un terminal dans le répertoire `Frontend`.
2. Installer les dépendances :
   ```bash
   npm install
   ```
3. Démarrer le serveur de développement :
   ```bash
   npm start
   ```
   L’application frontend sera accessible via l’adresse locale indiquée dans la console (par
   exemple, http://localhost:5173/).

### Avec Docker

#### 1. Backend

1. Dans le répertoire `Backend`, construire l’image Docker :
   ```bash
   docker build -t testunitaire-esiee-backend .
   ```
2. Lancer le conteneur :
   ```bash
   docker run -p 8080:8080 testunitaire-esiee-backend
   ```

#### 2. Frontend

1. Dans le répertoire `Frontend`, construire l’image Docker :
   ```bash
   docker build -t testunitaire-esiee-frontend .
   ```
2. Lancer le conteneur :
   ```bash
   docker run -p 80:80 testunitaire-esiee-frontend
   ```

#### 3. Docker Compose (Optionnel)

Si un fichier `docker-compose.yml` est mis en place dans le projet, il est possible de lancer l’ensemble des services :

1. Dans le répertoire racine du projet, exécuter :
   ```bash
   docker-compose up --build
   ```

Cela va construire et démarrer automatiquement le backend et le frontend dans les conteneurs configurés.

## Tests

- Pour le backend, les tests unitaires se trouvent sous le répertoire `Backend/src/test` et peuvent être exécutés avec :
  ```bash
  mvn test
  ```
- Le frontend inclut des tests end-to-end avec Cypress. Pour les exécuter, ouvrez un terminal dans le répertoire
  `Frontend` et lancez :
  ```bash
  npm run cy:open
  ```

## Remarques

- Assurez-vous que les ports utilisés ne sont pas bloqués par d’autres services.
- Les configurations Docker (Dockerfile, docker-compose.yml) doivent être ajustées selon l’environnement de déploiement.