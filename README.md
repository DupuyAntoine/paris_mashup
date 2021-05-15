# Découvrir Paris

## But de l'application

Le but de notre application va être d’orienter une visite de Paris en fonction du genre de film préféré de l’utilisateur.

Une fois le genre renseigné sur l’application, celle-ci va faire une liste des films de ce genre tournés dans les divers arrondissements de Paris et afficher l’arrondissement ayant le plus de films de ce genre.

L’application propose ensuite à l’utilisateur quelques lieux à visiter dans cet arrondissement.

## Sources

Nous retrouverons dans nos sources la liste des films/séries tournés à Paris (ne contient pas le genre du film) que nous croiserons avec une base de données du type IMDb afin de récupérer le genre du film en fonction du titre, réalisateur et année de tournage.

Nous croiserons les données du code postal de tournage des films avec les données des arrondissements de Paris pour récupérer des données intéressantes comme le nom de l’arrondissement. 

Si nous avons le temps, nous croiserons également les données avec la liste des activités à faire à Paris grâce au code postal.

Lieux de tournage à Paris :
https://opendata.paris.fr/explore/dataset/lieux-de-tournage-a-paris/export/?disjunctive.type_tournage&disjunctive.nom_tournage&disjunctive.nom_realisateur&disjunctive.nom_producteur&disjunctive.ardt_lieu

Arrondissements de Paris (dans lequel nous ajouterons les codes postaux des arrondissements) : https://opendata.paris.fr/explore/dataset/arrondissements/table/?disjunctive.c_ar&disjunctive.c_arinsee&disjunctive.l_ar

Que faire à Paris :
https://opendata.paris.fr/explore/dataset/que-faire-a-paris-/information/?disjunctive.category&disjunctive.tags&disjunctive.address_name&disjunctive.address_zipcode&disjunctive.address_city&disjunctive.access_type&disjunctive.price_type

IMDb (données sur les films) : https://www.imdb.com/

## Ontologie

Pour notre ontologie, nous avons modifié l’ontologie film que nous avions créée au TP1.

Nous utiliserons donc comme classes : 
- Activité (comprenant des événements, spectacles, concerts, expositions et animations)
- Lieu (comprenant des villes et des arrondissements)
- Film (comprenant divers genres dont heroic fantasy, catastrophe, horreur, science fiction, criminel, western, fantastique, aventure, historique, thriller, action, comique, dramatique et romantique)

Au niveau des object properties, nous avons :
- a lieu dans : pour relier à une activité à un lieu
- a été tourné dans : pour relier un film à un lieu
- se situe dans : pour relier un arrondissement à un ville

Au niveau des data properties, nous avons :
- a pour genre : qui associe un genre (string) à un film
- a pour code postal : qui associe un code postal (integer) à une ville ou un arrondissement

Au niveau des inférences, si par exemple un film a pour genre “aventure”, celui-ci sera catégorisé comme classe film d’aventure. Cette inférence aura lieu pour tous les genres que nous aurons renseignés sous formes de classes.
Si une activité a lieu dans un arrondissement, elle a aussi lieu dans la ville où se situe cet arrondissement.
