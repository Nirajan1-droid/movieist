package dev.farhan.movieist.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = repository.insert(new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now()));

        mongoTemplate.update(Movie.class)
            .matching(Criteria.where("imdbId").is(imdbId))
            .apply(new Update().push("reviews").value(review))
            .first();

        return review;
    }
}
// reviewBody and imdbId is passed here into the method from the controller.
// Review is to be returned. but review contains some thing and after that it will be passed to Review .
// repository.insert()//calling insert method of the mongorepository.
// new Review // inserting every single new review
// new Review(reviewBody , LocalDateTime.now(), LocalDateTime.now())// these LocalDateTime places are already specified in the db through Review model. they follow Date Logic
// mongoTemplate.update(Movie.class)// telling another model to update their class
// matching(Criteria.where().is())//previously posted value on specific key is need to be updated. criteria is the key passed from the request and update that one whose 
// passed criteria and another model matching value if equals.
// here Criteria.where("imdbId") is what passed from the request to the method, and being inserted into the database.
// and, imdbId[suppose secretId could one that should be matched with that passed key. for that use secretkey] is the one that only to be updated .  
// Cretieria.where("Review property").is("Movie Property not in inverted comma")
// then
// .apply() what to be applied
// .apply(new Update())// apply the new update that is push 
// new Update().push("")//where need to be pushed and what to be pushed?
// reviews array is the one where to  be pushed  
// new Update().push("reviews").value(review)//review is the what need to be pushed.
//@DocumentReference
//private List<Review> reviews; reviews is the seperate collection made in the Movie model , that is where each time the review are being pushed to.
// in Movie model we have told that, for each id, their list of reviews are to be stored in seperate reviews collection.

//.first() to update only that matched imdbId object's reviews array. which is the child of the Movie class also.
// return review; finally after updating to the array of the review successfully, that review is returned to upper method of the service class,
//then after the controller.
