package com.product.ecommerce.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.controller.RatingController;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;
import com.product.ecommerce.service.RatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@Tag(name="Rating API",description="Rating add/update/delete")
@RequestMapping("/ratings")
public class RatingControllerImpl implements RatingController {

    @Autowired
    private RatingService ratingService;

    @Override
    @PostMapping("/insert")
    @Operation(summary = "add ratings",description = "add a new rating")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "New rating created"),
        @ApiResponse(responseCode = "500", description = "Error occured while creating rating")
    }
    )
    public RatingResponse addRating(RatingRequest ratingRequest) {

        return ratingService.addRating(ratingRequest);

    }

    @Override
    @PutMapping("/update")
    @Operation(summary = "update ratings",description = "update existing rating")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Rating updated"),
        @ApiResponse(responseCode = "404", description = "No ratings found for the given ratingId"),
        @ApiResponse(responseCode = "500", description = "Error occured while creating rating ")
    }
    )
    public RatingResponse updateRating(Long id,RatingRequest ratingRequest)
            throws RatingNotFoundException,UnauthorizedUpdateException {
        return ratingService.updateRating(id,ratingRequest);
    }

    @Override
    @PutMapping("/delete")
    @Operation(summary = "delete ratings",description = "delete existing rating")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Rating deleted"),
        @ApiResponse(responseCode = "404", description = "No ratings found for the given ratingId"),
        @ApiResponse(responseCode = "500", description = "Error occured while creating rating ")
    }
    )
    public ResponseEntity<String> deleteRating(Long ratingId) throws RatingNotFoundException {
        ratingService.deleteRating(ratingId);
        return new ResponseEntity<String>("rating delete successfully",HttpStatus.ACCEPTED);
    }

    @Override
    @GetMapping("/ratings_by_productId")
    @Operation(summary = "delete ratings",description = "delete existing rating")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Rating deleted"),
        @ApiResponse(responseCode = "404", description = "No ratings found for the given ratingId"),
        @ApiResponse(responseCode = "500", description = "Error occured while creating rating ")
    }
    )
    public List<RatingResponse> getRatingsFortheProduct(Long productId)
            throws RatingNotFoundException {
        return ratingService.getRatingsFortheProduct(productId);
    }

    @Override
    @GetMapping("/ratings_by_productId_and_username")
    @Operation(summary = "delete ratings",description = "delete existing rating")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Rating deleted"),
        @ApiResponse(responseCode = "404", description = "No ratings found for the given ratingId"),
        @ApiResponse(responseCode = "500", description = "Error occured while creating rating ")
    }
    )
    public List<RatingResponse> getRatingDetailsFortheProductByUser(Long productId, String username)
            throws RatingNotFoundException {
        return ratingService.getRatingDetailsFortheProductByUser(productId, username);
    }
    
}
