package dev.farhan.movieist.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//contoller when called calls MovieServices which contains MovieRepository{1} and methods{2}
//controller clone the class of MovieServices to service.
//controller maps to call the below call when requested in requestmapping.
//controller asks method of MovieSerices through cloned class [service].
//service.method() returns the response where returned thing=Link<Movie> and it is captured through ResponseEntity<returnedthing>{clonedclassofmovieservice.methodavailbaleinthatservice(),HttpStatus.OK};



@RestController
@RequestMapping("/api/v1/movies")// it like app.get('/api/v1/movies')
//use Movies model to access data.
public class MovieController {

    @Autowired //it instanciate the MovieService to connect to the MongoRepository. 
    private MovieService service;

    @GetMapping//annotation on is applied to the below method here method(){just like function}
    //getMapping get called when RequestMapping is hitted by the client.
    //moviecontroller is using movieservice.java
    public ResponseEntity<List<Movie>> getMovies() {
        //this is init
        return new ResponseEntity<List<Movie>>(service.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")//optional refers to it could also return null from the database.
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){//pathvariable just like params.keypassed
        return new ResponseEntity<Optional<Movie>>(service.findMovieByImdbId(imdbId), HttpStatus.OK);
    }
}
