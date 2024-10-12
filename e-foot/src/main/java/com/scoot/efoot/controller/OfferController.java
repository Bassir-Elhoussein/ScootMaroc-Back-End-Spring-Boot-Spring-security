package com.scoot.efoot.controller;

import com.scoot.efoot.dto.OfferDto;
import com.scoot.efoot.dto.UserDto;
import com.scoot.efoot.dto.mapper.OfferDtoMapper;
import com.scoot.efoot.dto.mapper.UserDtoMapper;
import com.scoot.efoot.exception.OfferException;
import com.scoot.efoot.exception.UserException;
import com.scoot.efoot.model.Offer;
import com.scoot.efoot.model.User;

import com.scoot.efoot.response.ApiResponse;
import com.scoot.efoot.service.OfferService;
import com.scoot.efoot.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/offers")
@Tag(name="Twit Management", description = "Endpoints for managing twits")
public class OfferController {
    private OfferService OfferService;
    private UserService userService;

    public OfferController(OfferService OfferService,UserService userService) {
        this.OfferService=OfferService;
        this.userService=userService;
    }

    @PostMapping("/create")
    public ResponseEntity<OfferDto> createOffer(@RequestBody Offer req,
                                              @RequestHeader("Authorization") String jwt) throws UserException, OfferException {
        System.out.println("Received create offer request");

        System.out.println("content + "+req.getDetails());
        User user=userService.findUserProfileByJwt(jwt);

        UserDto userDto= UserDtoMapper.toUserDto(user);
        Offer Offer=OfferService.createOffer(req, user);

        OfferDto OfferDto= OfferDtoMapper.toDto(Offer);

        return new ResponseEntity<>(OfferDto, HttpStatus.CREATED);
    }

    @PostMapping("/apply")
    public ResponseEntity<OfferDto> applyOffer(@RequestBody Offer req,
                                               @RequestHeader("Authorization") String jwt) {
        try {
            User user = userService.findUserProfileByJwt(jwt);
            Offer offer = OfferService.createApplication(req, user);
            OfferDto offerDto = OfferDtoMapper.toDto(offer);

            return new ResponseEntity<>(offerDto, HttpStatus.CREATED);
        } catch (UserException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (OfferException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping("/{OfferId}")
    public ResponseEntity<OfferDto> findOfferById( @PathVariable Long OfferId,
                                                 @RequestHeader("Authorization") String jwt) throws OfferException, UserException{
        User user=userService.findUserProfileByJwt(jwt);
        Offer Offer=OfferService.findById(OfferId);

        OfferDto OfferDto=OfferDtoMapper.toDto(Offer);

        return new ResponseEntity<>(OfferDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{OfferId}")
    public ResponseEntity<ApiResponse> deleteOfferById(@PathVariable Long OfferId,
                                                      @RequestHeader("Authorization") String jwt) throws UserException, OfferException{

        User user=userService.findUserProfileByJwt(jwt);

        OfferService.deleteOfferById(OfferId, user.getId());

        ApiResponse res=new ApiResponse();
        res.setMessage("Offer deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<OfferDto>> findAllOffers(@RequestHeader("Authorization") String jwt) throws UserException{
        System.out.println("Received findAllOffers  request");
       // User user=userService.findUserProfileByJwt(jwt);
        List<Offer> Offers=OfferService.findAllOffer();
        List<OfferDto> OfferDtos=OfferDtoMapper.toDtos(Offers);
        return new ResponseEntity<List<OfferDto>>(OfferDtos,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OfferDto>> getUsersOffers(@PathVariable Long userId,
                                                       @RequestHeader("Authorization") String jwt)
            throws UserException{
        User reqUser=userService.findUserProfileByJwt(jwt);
        User user=userService.findUserById(userId);
        List<Offer> Offers=OfferService.getUsersOffer(user);
        List<OfferDto> OfferDtos=OfferDtoMapper.toDtos(Offers);
        return new ResponseEntity<List<OfferDto>>(OfferDtos,HttpStatus.OK);
    }
    @GetMapping("/userapplied")
    public ResponseEntity<Boolean> UserisApplied(
            @RequestParam("offerId") Long offerId,

            @RequestHeader("Authorization") String jwt
    ) throws UserException, OfferException {
        System.out.println("Received isapplied  request");

        User user=userService.findUserProfileByJwt(jwt);
        System.out.println("Received user  request"+user.getId());
        Offer offer= OfferService.findById(offerId);
        System.out.println("Received offer request"+offer.getId());
        Boolean isApplied=OfferService.isApplied(offer,user);
        System.out.println("Received isApplied boolean"+isApplied);

        return new ResponseEntity<>(isApplied, HttpStatus.OK);
    }

}
