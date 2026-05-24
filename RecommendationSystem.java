import java.util.*;

class Movie {
    String name;
    String genre;

    Movie(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
}

public class RecommendationSystem {

    public static List<Movie> recommendMovies(String preferredGenre, List<Movie> movies) {
        List<Movie> recommendations = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.genre.equalsIgnoreCase(preferredGenre)) {
                recommendations.add(movie);
            }
        }
        return recommendations;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", "Sci-Fi"));
        movies.add(new Movie("Interstellar", "Sci-Fi"));
        movies.add(new Movie("Titanic", "Romance"));
        movies.add(new Movie("The Notebook", "Romance"));
        movies.add(new Movie("Avengers", "Action"));
        movies.add(new Movie("John Wick", "Action"));
        movies.add(new Movie("The Conjuring", "Horror"));
        movies.add(new Movie("Insidious", "Horror"));

        System.out.println("=== Movie Recommendation System ===");
        System.out.print("Enter your preferred genre: ");
        String userGenre = sc.nextLine();

        List<Movie> recommendations = recommendMovies(userGenre, movies);

        if (recommendations.isEmpty()) {
            System.out.println("No recommendations found for genre: " + userGenre);
        } else {
            System.out.println("\nRecommended Movies:");
            for (Movie movie : recommendations) {
                System.out.println("- " + movie.name + " (" + movie.genre + ")");
            }
        }

        sc.close();
    }
}