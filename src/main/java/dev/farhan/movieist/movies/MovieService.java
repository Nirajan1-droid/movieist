package dev.farhan.movieist.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    //instanciate the obj
    //const myCar = new Car('Toyota', 'Camry'); new Car object is created in js. similarly repository is cloned through the @Autowired 
    @Autowired
    private MovieRepository repository;//mongoose is in MovieRepository and now repository is the copy that.
    //it is through which the database operations are possible
    //function List of movie is being retured when called this operation.. in controller
    public List<Movie> findAllMovies() {
        return repository.findAll();//that created single instance is used.
        //but what that repository contains? it contains MongoRepository
    }
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return repository.findMovieByImdbId(imdbId);
    }
}
