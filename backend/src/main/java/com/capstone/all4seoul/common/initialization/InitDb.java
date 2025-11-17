package com.capstone.all4seoul.common.initialization;

import com.capstone.all4seoul.place.domain.Category;
import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.place.repository.PlaceRepository;
import com.capstone.all4seoul.seoulCityData.domain.MajorPlace;
import com.capstone.all4seoul.seoulCityData.repository.MajorPlaceRepository;
import com.capstone.all4seoul.user.domain.Gender;
import com.capstone.all4seoul.user.domain.Mbti;
import com.capstone.all4seoul.user.domain.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
//        this.initService.saveCrawledPlaces();
//        this.initService.saveMajorPlaces();
//        this.initService.createUsers();️
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {
        private static final Logger log = LoggerFactory.getLogger(InitDb.InitService.class);
        private final PlaceRepository placeRepository;
        private final MajorPlaceRepository majorPlaceRepository;
        private final EntityManager em;

        @Transactional
        public void createUsers() {
            User user = User.createUser(
                    "admin",
                    "admin",
                    "pochetino",
                    LocalDate.now(),
                    Mbti.ESFJ,
                    Gender.Male,
                    "pochetino"
            );
            User user2 = User.createUser(
                    "admin2",
                    "admin2",
                    "pochetino2",
                    LocalDate.now(),
                    Mbti.ISTP,
                    Gender.Male,
                    "pochetino2"
            );

            em.persist(user);
            em.persist(user2);

            em.flush();
        }

        private final String[] categories = new String[]{
                "관광명소", "맛집", "문화시설", "주유소", "주차장", "카페"
        };

        // csvFile = "지도_크롤링_데이터/카페

        /**
         * 각 카테고리에 해당하는 CSV 파일을 로드하고 데이터를 처리하는 메서드
         */
        public void saveCrawledPlaces() {
            for (String category : categories) {
                String csvFile = "crawledPlaces/" + category + ".csv";
                loadPlacesFromCsv(csvFile);
            }
        }

        /**
         * 주어진 CSV 파일에서 데이터를 로드하고 처리하는 메서드
         */
        private void loadPlacesFromCsv(String csvFile) {
            try {
                ClassPathResource resource = new ClassPathResource(csvFile);
                if (!resource.exists()) {
                    log.warn("CSV file not found: {}", csvFile);
                    return;
                }

                // 파일 이름에서 카테고리 추출
                String categoryName = String.valueOf(getCategoryFromFileName(csvFile));

                // 파일 이름에 해당하는 Kakao 카테고리로 변환
                Category category = Category.valueOf(categoryName.toUpperCase());

                try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                     CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                    List<Place> places = new ArrayList<>();
                    for (CSVRecord csvRecord : csvParser) {
                        String name = csvRecord.get(0);
//                        double degree = Double.parseDouble(csvRecord.get(1)); // 필요시 사용
                        String address = csvRecord.get(2);
                        String tel = csvRecord.get(3);

                        // Placeholder for x and y coordinates
                        double x = 0.0; // 실제 데이터로 대체 필요
                        double y = 0.0; // 실제 데이터로 대체 필요

                        MajorPlace majorPlace = majorPlaceRepository.findFirstByAreaNameContains(name)
                                .orElse(null); // 만약 주요장소라면 추가 정보 삽입, 아니라면 null 삽입

                        Place place = Place.createPlace(
                                new ArrayList<>(), // 초기 이벤트 리스트
                                name,
                                new ArrayList<>(), // 초기 리뷰 리스트
                                tel,
                                address,
                                x,
                                y,
                                category,
//                                degree,
                                majorPlace
                        );

                        places.add(place);
                    }

                    placeRepository.saveAll(places);
                    log.info("All places saved successfully from CSV: {}", csvFile);
                }
            } catch (Exception e) {
                log.error("Error reading CSV file: {}", csvFile, e);
            }
        }

        /**
         * 파일 이름에서 카테고리를 추출하여 해당하는 Category enum 값으로 반환하는 메서드
         */
        private Category getCategoryFromFileName(String fileName) {
            // 파일 이름을 "/"로 분리하여 마지막 부분을 추출합니다.
            String[] parts = fileName.split("/");
            String lastPart = parts[parts.length - 1];

            // 추출된 부분에서 확장자를 제거합니다.
            String[] nameParts = lastPart.split("\\.");
            String categoryName = nameParts[0];

            // 파일 이름으로부터 카테고리를 Category enum 값으로 변환하여 반환합니다.
            switch (categoryName) {
                case "관광명소":
                    return Category.TOURIST_ATTRACTION;
                case "맛집":
                    return Category.RESTAURANT;
                case "문화시설":
                    return Category.CULTURE_FACILITY;
                case "주유소":
                    return Category.GAS_STATION;
                case "주차장":
                    return Category.PARKING_LOT;
                case "카페":
                    return Category.CAFE;
                default:
                    throw new IllegalArgumentException("Invalid category name: " + categoryName);
            }
        }

        /**
         * 서울시 115개 주요 장소 초기화
         */
        private void saveMajorPlaces() {
            String filePath = "majorPlaces/seoulCityMajorPlaces.csv";
            try {
                ClassPathResource resource = new ClassPathResource(filePath);
                if (!resource.exists()) {
                    log.warn("CSV file not found: {}", filePath);
                    return;
                }

                try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                     CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                    for (CSVRecord csvRecord : csvParser) {
                        String category = csvRecord.get(0);
                        String areaCode = csvRecord.get(2);
                        String areaName = csvRecord.get(3);
                        String areaEnglishName = csvRecord.get(4);

                        MajorPlace majorPlace = new MajorPlace(
                                category,
                                areaCode,
                                null,
                                null,
                                null,
                                null,
                                null
                        );

                        log.info("Loaded majorPlace: {}", majorPlace);
                        majorPlaceRepository.save(majorPlace);
                    }
                    log.info("All places saved successfully from CSV: {}", filePath);
                }
            } catch (Exception e) {
                log.error("Error reading CSV file: {}", filePath, e);
            }
        }
    }
}