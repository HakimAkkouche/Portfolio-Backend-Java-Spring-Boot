# Backend Portfolio - Java Spring Boot

## Description
Ce projet contient le backend de mon portfolio personnel, développé avec Java Spring Boot. Il sert à fournir une API RESTful pour gérer les informations de mon portfolio, telles que les projets, les informations personnelles, et les détails de contact. Il utilise une base de données MySQL pour stocker ces informations.

## Fonctionnalités
- API RESTful pour gérer les données de mon portfolio.
- Authentification et autorisation via Spring Security.
- Gestion des utilisateurs (création, modification, suppression).
- Gestion des projets (ajout, modification, suppression).
- Accès sécurisé aux informations sensibles via JWT (JSON Web Tokens).

## Prérequis
Avant de commencer, assurez-vous que les outils suivants sont installés sur votre machine :

- JDK 17 ou supérieur
- Maven ou Gradle (selon la configuration du projet)
- MySQL
- Docker (facultatif, pour utiliser Docker Compose)

## Installation

### Cloner le dépôt

Clonez le projet depuis GitHub :

git clone https://github.com/votre-utilisateur/portfolio-backend.git
cd portfolio-backend

## Configuration de la base de données

1. Créez une base de données MySQL appelée portfolio_db.
2. Mettez à jour les informations de connexion à la base de données dans le fichier src/main/resources/application.properties ou application.yml :

    spring.datasource.url=jdbc:mysql://localhost:3306/portfolio_db
    spring.datasource.username=root
    spring.datasource.password=motdepasse
    spring.jpa.hibernate.ddl-auto=update

## Construire et démarrer le projet

Si vous utilisez Maven, exécutez la commande suivante :

    mvn clean install
    mvn spring-boot:run

Ou si vous utilisez Gradle :

    ./gradlew bootRun

L'application démarrera sur le port 8080 par défaut.

## Utilisation de Docker (facultatif)

Si vous préférez utiliser Docker, vous pouvez construire et démarrer le projet avec Docker Compose.

1. Assurez-vous d'avoir un fichier docker-compose.yml configuré comme suit :

    version: '3.8'
    services:
      mysql:
        image: mysql:8.0
        environment:
          MYSQL_ROOT_PASSWORD: motdepasse
          MYSQL_DATABASE: portfolio_db
        ports:
          - "3306:3306"
        volumes:
          - portfolio-mysql-data:/var/lib/mysql
      backend:
        image: votre-image-backend
        ports:
          - "8080:8080"
        depends_on:
          - mysql
    volumes:
      portfolio-mysql-data:

2. Exécutez Docker Compose :

    docker-compose up --build

# Endpoints de l'API

Voici une liste des principaux endpoints de l'API :

## Authentification

    POST /api/public/login : Authentification des utilisateurs (avec JWT).


## Projets (PUBLIC)

   - GET /api/public/projects : Récupère tous les projets.
   - GET /api/public/projects/{id} : Récupère un projet grace à son ID.

## Projets (ADMIN)

   - GET /api/admin/projects : Récupère tous les projets.
   - GET /api/admin/projects/{id} : Récupère un projet grace à son ID.
   - POST /api/admin/projects : Crée un nouveau projet.
   - PUT /api/admin/projects/{id} : Modifie un projet existant.
   - DELETE /api/admin/projects/{id} : Supprime un projet.

## Informations pesonnelles (PUBLIC)

   - GET /api/public/personal-info : Récupère tous les informations personnelles (Nom Prénom, email, lien GitHub, ...).

## Informations pesonnelles (ADMIN)

   - GET /api/admin/personal-info : Récupère tous les informations personnelles (Nom Prénom, email, lien GitHub, ...).
   - PUT /api/admin/personal-info : Modifie les informations personnelles.

## A propos (PUBLIC)

   - GET /api/public/about-me : Récupère l'image et le texte de la page A propos.

## A propos (ADMIN)

   - GET /api/admin/about-me : Récupère l'image et le texte de la page A propos.
   - PUT /api/admin/about-me : Modifie l'image et le texte de la page A propos.

## Mentions légales (PUBLIC)

   - GET /api/public/personal-info : Récupère le texte des mentions légales.

## Mentions légales (ADMIN)

   - GET /api/admin/personal-info : Récupère le texte des mentions légales.
   - PUT /api/admin/projects/{id} : Modifie le texte des mentions légales.

## Contribution

Les contributions sont les bienvenues ! Si vous avez des suggestions ou des améliorations, n'hésitez pas à ouvrir un issue ou à soumettre une pull request.

## License

Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.

## Auteurs

    Nom de l'auteur : Hakim AKKOUCHE.
    Contact : [LinkedIn](https://www.linkedin.com/in/hakim-akkouche/)