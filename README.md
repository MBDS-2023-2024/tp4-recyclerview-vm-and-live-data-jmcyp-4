# Prise en main des tests sur Android

Dans ce TP vous allez apprendre à manipuler les principaux éléments d'architecture d'une application
constuite selon le pattern MVVM. Particulièrement, vous allez observer l'utilisation des composants
tels que :

- ViewModel
- LiveData
- Repository
- RecyclerView
- Adapter

## Travaux à réaliser

### Configurez l'application

1. Cloner le projet sur Android Studio
2. Modifier la section Contributeurs du fichier README.md pour y ajouter votre nom
3. Exécuter l'application et observer le comportement
4. Commit et push sur Github; que remarquez-vous ? Vous devriez avoir reçu un mail vous indiquant
   que les tests ont échoué

### Exécutez et fixer les tests

1. Exécuter les tests unitairs et corrigez les erreurs
2. Commit et push vos changements. Tout devrait être OK pour les tests.

### Finaliser l'application

1. Exécutez l'application et testez-la
2. Les images de profiles des utilisateurs ne sont pas affichées, pouvez-vous corriger cela?
3. Commit et push les derniers changements.

## Améliorer l'application

1. Repérez les derniers TODO dans le code et essayez de les implémenter
    2. On va modifier le comportement du bouton supprimer de l'application pour qu'en cliquant sur
       le
       bouton delete :

    - On ne supprime plus l'utilisateur, mais on change son statut (activé <-> desactivé)
    - On met un background 'rouge' sur la cellule quand l'utilisateur est désactivé et blanc quand
      il
      est activé
    - On change le visuel du bouton delete : `poubelle` si l'utilisateur est activé et `restore` si
      l'utilisateur est désactivé
    - Finalement, quand on clique sur le bouton delete ou restore, on switch le statut de
      l'utilisateur

2. Assurez-vous que les tests fonctionnent toujours et que l'application fonctionne correctement

3. Ajouter un menu dans la toolbar qui permet :
    - De trier les utilisateurs par ordre alphabétique (croissant ou décroissant)
    - par date de création (croissant ou décroissant)
    - Par statut (activé ou désactivé)

## Bonus

1. Ajouter un champ de recherche dans la toolbar qui permet de filtrer les utilisateurs par leur nom

# Contributeurs
Jean Maxo Cyprien
