package com.example.quizapp.api;

public class ApiUrlManager {

    public static int getCategoryForTheme(String theme) {
        // Ici, vous pouvez implémenter la logique pour associer chaque thème à une catégorie spécifique de votre API
        // Par exemple, utilisez une instruction switch ou une table de hachage pour mapper les thèmes aux catégories

        switch (theme) {
            case "Theme 1":
                return 21; // Remplacez par la catégorie correcte associée à "Theme 1"
            case "Theme 2":
                return 11; // Remplacez par la catégorie correcte associée à "Theme 2"
            case "Theme 3":
                return 22; // Remplacez par la catégorie correcte associée à "Theme 3"
            default:
                // Catégorie par défaut ou gestion des erreurs
                return 0; // Remplacez par la catégorie par défaut ou une gestion d'erreur appropriée
        }
    }
}
