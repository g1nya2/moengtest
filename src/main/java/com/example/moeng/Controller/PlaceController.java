package com.example.moeng.Controller;

import com.example.moeng.DTO.PlaceDTO;
import com.example.moeng.Entity.Member;
import com.example.moeng.Entity.Place;
import com.example.moeng.Repository.MemberRepository;
import com.example.moeng.Repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/moeng/place")
public class PlaceController {
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    MemberRepository memberRepository;
    @PostMapping("")
    public String placeRegister(PlaceDTO placeDTO) {
        Place place = placeDTO.toEntity();
        log.info("place" + place.toString());
        // 이미 동일한 이름이 있으면 redirect
        log.info(place.getName());
        log.info(placeRepository.findById(place.getName()).toString());

        Member owner = memberRepository.findById(place.getOwnerName()).orElse(null);
        log.info("owner" + owner.toString());
        owner.setRegisteredPlaceId(place.getName());
        memberRepository.save(owner);
        placeRepository.save(place);

        return "/place/createAfter";
    }

    @ResponseBody
    @GetMapping
    public List<Place> getAllPlaces() {
        // 여기서 실제 데이터베이스에서 데이터를 가져오거나 하여 Place 엔터티 리스트를 생성
        List<Place> places = new ArrayList<>();
        places.add(new Place("Place1", "Address1", 100, "Timezone1", 10, "Owner1", 12.34, 56.78));
        places.add(new Place("Place2", "Address2", 150, "Timezone2", 15, "Owner2", 23.45, 67.89));
        // 나머지 데이터 추가...

        placeRepository.findAll();

        return places;
    }
}
