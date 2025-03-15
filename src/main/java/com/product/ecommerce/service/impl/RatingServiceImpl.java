package com.product.ecommerce.service.impl;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.product.ecommerce.dao.RatingRepository;
import com.product.ecommerce.entity.Product;
import com.product.ecommerce.entity.Rating;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;
import com.product.ecommerce.service.RatingService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public RatingResponse addRating(RatingRequest ratingRequest) {
       Rating rating=new Rating();
       rating.setRatingDescription(ratingRequest.getRatingDescription());
       rating.setRatingValue(ratingRequest.getRatingValue());
       rating.setUsername(ratingRequest.getUsername());
       Product product=new Product();
       product.setProductId(ratingRequest.getProductId());
       Rating savedRating=ratingRepository.save(rating);
       return convertRatingEntityToResponse(savedRating);
    }

    @Override
    public RatingResponse updateRating(Long ratingId,RatingRequest request) throws RatingNotFoundException,UnauthorizedUpdateException {
       Optional<Rating> rating =ratingRepository.findById(ratingId);
       if(rating.isEmpty()){
          throw new RatingNotFoundException("No rating details found for rating::"+ratingId);
       }else{
         Rating originalRating=rating.get();
         RatingResponse response=new RatingResponse();
         if(request.getRatingDescription()!=null){
            originalRating.setRatingDescription(request.getRatingDescription());
         }
         if(request.getRatingValue()!=null){
            originalRating.setRatingValue(request.getRatingValue());
         }
         if(request.getProductId()!=null && request.getProductId()!=originalRating.getProduct().getProductId()){
            throw new UnauthorizedUpdateException("product id cannot be updated::"+request.getProductId());
         }
         if(request.getUsername()!=null && request.getUsername()!=originalRating.getUsername()){
            throw new UnauthorizedUpdateException("usenrname cannot be modified");
         }
         Rating changedRating=ratingRepository.save(originalRating);

         return convertRatingEntityToResponse(changedRating);
       }

    }

    @Override
    public boolean deleteRating(Long ratingId) throws RatingNotFoundException {
        ratingRepository.deleteById(ratingId);
        return true;
    }

    @Override
    public List<RatingResponse> getRatingsFortheProduct(Long productId)
            throws RatingNotFoundException {
        List<RatingResponse> ratingList=ratingRepository.findRatingsByProductId(productId)
                                                .stream()
                                                .map(this::convertRatingEntityToResponse)
                                                .collect(Collectors.toList());
        if(ratingList==null && ratingList.isEmpty()){
            throw new RatingNotFoundException("No rating details found for productId::"+productId);
        }
        return ratingList;
    }

    @Override
    public List<RatingResponse> getRatingDetailsFortheProductByUser(Long productId, String username)
            throws RatingNotFoundException{
                List<RatingResponse> ratingList=ratingRepository.findRatingByProductIdAndUsernameList(productId, username)
                                                                .stream()
                                                                .map(this::convertRatingEntityToResponse)
                                                                .collect(Collectors.toList());
                if(ratingList==null && ratingList.isEmpty()){
                    throw new RatingNotFoundException("No rating details found for productId::"+productId);
                }
            return ratingList;
    }

    private RatingResponse convertRatingEntityToResponse(Rating rating){
        RatingResponse response=new RatingResponse();
        response.setProductId(rating.getProduct().getProductId());
        response.setRatingDate(rating.getRatingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        response.setRatingDescription(rating.getRatingDescription());
        response.setRatingId(rating.getRatingId());
        response.setRatingValue(rating.getRatingValue());
        response.setUsername(rating.getUsername());

        return response;
    }
    
}
