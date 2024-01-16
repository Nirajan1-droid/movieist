//movie ko model ho. schema define garxu, 
//for fast processing relationships are coordinated into another collection for fast access.

package dev.farhan.movieist.movies;

import lombok.AllArgsConstructor;//constructors properties
import lombok.Data;
import lombok.NoArgsConstructor;//constructors properties
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;//list type List<String>

@Document(collection = "movies")
@Data
@AllArgsConstructor//constructors properties
@NoArgsConstructor//constructors properties
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> backdrops;
    private List<String> genres;
    @DocumentReference//relationships are stored in the seperate collection
   // @DocumentReference: This annotation suggests that there is a reference to another collection for storing reviews related to the movie. 
    //It indicates a relationship between the Movie collection and another collection, likely a Review collection. 
    //This annotation is specific to the framework you're using, and it is used to establish a connection between documents in different collections.
    private List<Review> reviews;


    //mapping vaerako xa, function ma k k auxa, and that is mapped to the db objects properties sanga.
    public Movie(String imdbId, String title, String releaseDate, String trailerLink, String poster, List<String> backdrops, List<String> genres) {
        this.imdbId = imdbId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        this.poster = poster;
        this.backdrops = backdrops;
        this.genres = genres;
    }
}
