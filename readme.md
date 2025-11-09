# Mon suivi

Cé projet est un WS exposant des end-points permettants la gestion de ventes des produits.
- Le module core contient la logique métier du projet
- Le module infra contient les dépendances technique du projet

## Description du module infa
- Le package adapter contient les implementations des ports du module core
- Le package controller contient les differents end-point exposés
- Le package DTO permet de transférer les donées en entrée et sortie de l'infra
- Le package model contient les class nécessaire à l'infra
- Le package repository contient les entités et DAO (Hibernate JPA)

## Descrption du module core
Le module Core n'a pas de dépendance au module infra
- Le package entity contient la logique métier du projet
- Le package port contient les interfaces d'entrée et sortie du modul infra. On distingue:
  - les inputBoundary (Contrat d'entrée associé a un usecase)
  - OutputBoundary (Contrat de sortie d'un usecase)
  - Gateway (Contrat pour l’accès aux données (base, WS, etc.)
- Le package usecase contient les cas d'utilisation du projet. C'est le point d'entrée du module core avec les inputBoundaries
- Le package factory instancie les differente class du module Core

## stack

- Java 21
- SpringBoot 3.3.1
- Maven
- Jpa Hibernate
- Postgresql
- Amazon S3

## Build du projet
Ce projet contient 3 profiles Maven:
local, moe, prod
- **local** : exécution et tests en environnement local
- **moe** : environnement d'intégration (préproduction)
- **prod** : déploiement en production


