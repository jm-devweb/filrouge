# Projet Fil Rouge : OutToGether

### Objet 
Ce site a pour objectif de mettre en relation des personnes pour des sorties (par thème)

### Installation 

#### BackEnd

- Cloner le repository en local
- Démarrer l'application FilrougeApplication

##### Prérequis
- Avoir une base postgresql installée sur son poste local configurée avec les paramètres suivants dans le fichier application.yml :
    - url: 
    - username: 
    - password: 

##### Au démarrage de l'application, les opérations suivantes sont effectuées :
- les tables sont générées  
- les données sont chargées
- l'age des membres inscrits est mis à jour en fonction de la date courante 

#### FrontEnd
- Cloner le repository https://github.com/vincent78-devweb/fil-rouge-front.git en local
- Cliquez sur Run 'npm install'.
- Puis lancer le frontend en cliquant dans le menu Run puis Run Angular CLI Server ou utilisez le raccourci MAJ + F10
- Cliquez sur le lien suivant pour accéder au frontend de l'application: http://localhost:4200/

