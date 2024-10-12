package com.scoot.efoot.dto;




import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {


    private Long id;


    private UserDto sender;


    private UserDto recipient;


    private String userType;

    private Double height;

    private Double weight;

    private String position;

    private String preferredFoot;

    private String education;

    private String details;
    private String location;
    private String backgroundImage;

    private List<UserDto> applicants = new ArrayList<>();
}
