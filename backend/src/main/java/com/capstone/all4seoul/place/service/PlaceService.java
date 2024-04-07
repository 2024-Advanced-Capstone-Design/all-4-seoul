package com.capstone.all4seoul.place.service;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.place.dto.response.DetailPlaceResponse;
import com.capstone.all4seoul.place.repository.PlaceRepository;
import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    //전체 장소 조회
    public List<DetailPlaceResponse> findAll() {
        return placeRepository.findAll()
                .stream()
                .map(DetailPlaceResponse::of)
                .toList();
    }
    //유저 한 명에 대해 북마크 조회
    public List<DetailPlaceResponse> findPlacesByUserId(Long userId) {
        return placeRepository.findListByUserId(userId)
                .stream()
                .map(DetailPlaceResponse::of)
                .toList();
    }
    //장소 이름으로 장소 리스트 조회
    public List<DetailPlaceResponse> findPlacesByName(String name) {
        return placeRepository.findByName(name)
                .stream()
                .map(DetailPlaceResponse::of)
                .toList();
    }
}
