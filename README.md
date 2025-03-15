# Project Test-Unitaire_Esiee

---

![License](https://img.shields.io/github/license/Redstoneur/Test-Unitaire_Esiee)
![Top Language](https://img.shields.io/github/languages/top/Redstoneur/Test-Unitaire_Esiee)
![Size](https://img.shields.io/github/repo-size/Redstoneur/Test-Unitaire_Esiee)
![Contributors](https://img.shields.io/github/contributors/Redstoneur/Test-Unitaire_Esiee)
![Last Commit](https://img.shields.io/github/last-commit/Redstoneur/Test-Unitaire_Esiee)
![Issues](https://img.shields.io/github/issues/Redstoneur/Test-Unitaire_Esiee)
![Pull Requests](https://img.shields.io/github/issues-pr/Redstoneur/Test-Unitaire_Esiee)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/74796f194bb24bdcb67e05ee91a74b5d)](https://app.codacy.com/gh/Redstoneur/Test-Unitaire_Esiee/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

---

![Java Version](https://img.shields.io/badge/java-17-blue)
![Maven Version](https://img.shields.io/badge/maven-3.8.3-blue)

![Node.js Version](https://img.shields.io/badge/node.js-22-blue)
![npm Version](https://img.shields.io/badge/npm-8.1.0-blue)
![TypeScript Version](https://img.shields.io/badge/typescript-4.4.4-blue)
![Vue.js Version](https://img.shields.io/badge/vue.js-3.2.20-blue)

![Docker Version](https://img.shields.io/badge/docker-20.10.8-blue)
![Docker Compose Version](https://img.shields.io/badge/DockerCompose-1.29.2-blue)

---

![Forks](https://img.shields.io/github/forks/Redstoneur/Test-Unitaire_Esiee)
![Stars](https://img.shields.io/github/stars/Redstoneur/Test-Unitaire_Esiee)
![Watchers](https://img.shields.io/github/watchers/Redstoneur/Test-Unitaire_Esiee)

---

![Latest Release](https://img.shields.io/github/v/release/Redstoneur/Test-Unitaire_Esiee)
![Release Date](https://img.shields.io/github/release-date/Redstoneur/Test-Unitaire_Esiee)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/74796f194bb24bdcb67e05ee91a74b5d)](https://app.codacy.com/gh/Redstoneur/Test-Unitaire_Esiee/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

---

![Backend Tests](https://img.shields.io/github/actions/workflow/status/Redstoneur/Test-Unitaire_Esiee/backend-tests.yml?label=Backend%20Tests)
![Frontend Tests](https://img.shields.io/github/actions/workflow/status/Redstoneur/Test-Unitaire_Esiee/cypress.yml?label=Frontend%20Tests)
![JavaDocs](https://img.shields.io/github/actions/workflow/status/Redstoneur/Test-Unitaire_Esiee/generate-javadoc.yml?label=JavaDocs)

---

## Sommaire

<!-- TOC -->
* [Project Test-Unitaire_Esiee](#project-test-unitaire_esiee)
  * [Sommaire](#sommaire)
  * [Description du projet](#description-du-projet)
  * [Prérequis](#prérequis)
  * [Lancement du projet](#lancement-du-projet)
    * [Sans Docker](#sans-docker)
      * [1. Backend](#1-backend)
      * [2. Frontend](#2-frontend)
    * [Avec Docker](#avec-docker)
      * [1. Backend](#1-backend-1)
      * [2. Frontend](#2-frontend-1)
      * [3. Docker Compose (Optionnel)](#3-docker-compose-optionnel)
  * [Tests](#tests)
  * [Remarques](#remarques)
<!-- TOC -->


## Description du projet

Ce projet est une application web composée d’un backend en Java (Spring Boot) et d’un frontend en Vue.js, conçue pour
gérer des échanges d’objets entre utilisateurs. Le backend expose diverses API REST pour créer, mettre à jour et
supprimer des objets, ainsi que pour gérer les échanges et la gestion des utilisateurs. Le frontend permet d’interagir
avec ces API et inclut des tests end-to-end automatisés avec Cypress.

## Prérequis

* Java 17 et Maven
* Node.js 22 et npm
* Docker (pour la version Dockerisée)

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

* Pour le backend, les tests unitaires se trouvent sous le répertoire `Backend/src/test` et peuvent être exécutés avec :
  ```bash
  mvn test
  ```
* Le frontend inclut des tests end-to-end avec Cypress. Pour les exécuter, ouvrez un terminal dans le répertoire
  `Frontend` et lancez :
  ```bash
  npm run cy:open
  ```

## Remarques

* Assurez-vous que les ports utilisés ne sont pas bloqués par d’autres services.
* Les configurations Docker (Dockerfile, docker-compose.yml) doivent être ajustées selon l’environnement de déploiement.