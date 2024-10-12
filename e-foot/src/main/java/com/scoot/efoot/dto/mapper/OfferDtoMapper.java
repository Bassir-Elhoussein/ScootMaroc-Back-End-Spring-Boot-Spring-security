package com.scoot.efoot.dto.mapper;

import com.scoot.efoot.dto.OfferDto;
import com.scoot.efoot.dto.UserDto;
import com.scoot.efoot.model.Offer;
import com.scoot.efoot.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class OfferDtoMapper {

    public static OfferDto toDto(Offer offer) {
        if (offer == null) {
            return null;
        }
        UserDto user = UserDtoMapper.toUserDto(offer.getSender());
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setSender(user);
        dto.setUserType(offer.getUserType());
        dto.setHeight(offer.getHeight());
        dto.setWeight(offer.getWeight());
        dto.setPosition(offer.getPosition());
        dto.setPreferredFoot(offer.getPreferredFoot());
        dto.setEducation(offer.getEducation());
        dto.setDetails(offer.getDetails());
        dto.setLocation(offer.getLocation());
        dto.setBackgroundImage(offer.getBackgroundImage());
        List<UserDto> applicants = new ArrayList<>();
        for (User user1 : offer.getApplicants()) {
            UserDto user2 = UserDtoMapper.toUserDto(user1);
            applicants.add(user2);
        }
        dto.setApplicants(applicants);

        return dto;
    }

    public static List<OfferDto> toDtos(List<Offer> offers) {
        List<OfferDto> dtos = new ArrayList<>();
        for (Offer offer : offers) {

            UserDto user = UserDtoMapper.toUserDto(offer.getSender());
            OfferDto dto = new OfferDto();
            dto.setId(offer.getId());
            dto.setSender(user);
            dto.setUserType(offer.getUserType());
            dto.setHeight(offer.getHeight());
            dto.setWeight(offer.getWeight());
            dto.setPosition(offer.getPosition());
            dto.setPreferredFoot(offer.getPreferredFoot());
            dto.setEducation(offer.getEducation());
            dto.setDetails(offer.getDetails());
            dto.setLocation(offer.getLocation());
            dto.setBackgroundImage(offer.getBackgroundImage());

            List<UserDto> applicants = new ArrayList<>();
            for (User user1 : offer.getApplicants()) {
                UserDto user2 = UserDtoMapper.toUserDto(user1);
                applicants.add(user2);
            }
            dto.setApplicants(applicants);

            dtos.add(dto);
        }
        return dtos;
    }
}