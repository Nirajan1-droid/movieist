package dev.farhan.movieist.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")// it like app.get('/api/v1/movies')
//use Movies model to access data.
public class MovieController {

    @Autowired //it 
    private MovieService service;

    @GetMapping//annotation on is applied to the below method here method(){just like function}
    //getMapping get called when RequestMapping is hitted by the client.
    //moviecontroller is using movieservice.java
    public ResponseEntity<List<Movie>> getMovies() {
        //this is init
        return new ResponseEntity<List<Movie>>(service.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(service.findMovieByImdbId(imdbId), HttpStatus.OK);
    }
}
