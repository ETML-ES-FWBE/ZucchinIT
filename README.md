## Introduction
Ce projet à pour but de créer une API avec le framework Spring et de la déployer avec Docker.
Cette API est conçue sur le thème du football et plus précisément sur la gestion des membres d'équipes et de leurs rôles.

Le projet respecte les points suivants :
* Intégration de l'authentification
* Opérations CRUD sur les ressources
* Bonnes pratiques et architecture Spring
* Git flow et conventional commits
* Déploiement Docker (API exposée uniquement)

## Continuer le développement
Pour continuer le développement de ce projet, suivez les étapes suivantes :

### 1. Se placer sur la branche `develop`
```sh
git checkout develop
```
Assurez-vous d'avoir les derniers changements :
```sh
git pull origin develop
```

### 2. Vérifier la version de Java et JDK (minimum version 17 pour java)
Le projet nécessite une version spécifique de Java. Vérifiez votre version avec :
```sh
java -version
```

Si besoin, installez la version correcte de JDK et configurez-la dans IntelliJ IDEA.

### 3. Installer les dépendances Maven (version 4.0.0)
Utilisez Maven pour télécharger toutes les dépendances nécessaires :
```sh
mvn clean install
```

### 4. Ouvrir et exécuter le projet avec IntelliJ IDEA
- Ouvrez le projet dans IntelliJ IDEA.
- Configurez le SDK Java et Maven dans les paramètres du projet.
- Exécutez l'application depuis la classe principale ou utilisez la commande Maven :
```sh
mvn spring-boot:run
```

## Déployer avec Docker
Deux solutions de containerisation sont intégrées pour ce projet :
* Container spring seul avec la BD h2
* Container spring avec la BD MySQL et le reverse proxy Nginx.

### Container Spring + H2
1. Accéder à `deploy`
2. Lancer la commande `docker compose -f ./spring-h2-compose.yaml up`.\
   *Utiliser `--build` pour forcer un nouveau build de l'image.*\
   *Utiliser `-d` pour ne pas attacher la sortie à la console*

### Container Spring + MySQL + Nginx
**⚠️ Le reverse proxy accepte les requêtes sur l'url `http://{host}/api`**
1. Accéder à `deploy`
2. Configurer les variables d'environnements suivantes selon la config do container MySQL :
   `SPRING.DATASOURCE.URL=jdbc:mysql://mysql:3306/<db-name>`
   `SPRING.DATASOURCE.USERNAME=<user>`
   `SPRING.DATASOURCE.PASSWORD=<mot de passe>`
3. Lancer la commande `docker compose -f ./spring-nginx-mysql-compose.yaml up`.\
   *Utiliser `--build` pour forcer un nouveau build de l'image.*\
   *Utiliser `-d` pour ne pas attacher la sortie à la console*