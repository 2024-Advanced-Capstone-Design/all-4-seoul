package com.capstone.all4seoul.place.service;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.place.domain.Category; // 필요에 따라 적절한 패키지를 import
import com.capstone.all4seoul.place.repository.PlaceRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        this.initService.saveExtractedParkingLotData();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {
        private static final Logger log = LoggerFactory.getLogger(InitDb.InitService.class);
        private final PlaceRepository placeRepository;

        private final String[] categories = new String[]{
                "관광명소", "맛집", "문화시설", "주유소", "주차장", "카페"
        };

        // csvFile = "지도_크롤링_데이터/카페

        /**
         * 각 카테고리에 해당하는 CSV 파일을 로드하고 데이터를 처리하는 메서드
         */
        public void saveExtractedParkingLotData() {
            for (String category : categories) {
                String csvFile = "지도_크롤링_데이터/" + category + ".csv";
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
                        String degree = csvRecord.get(1); // 필요시 사용
                        String address = csvRecord.get(2);
                        String tel = csvRecord.get(3);

                        // Placeholder for x and y coordinates
                        double x = 0.0; // 실제 데이터로 대체 필요
                        double y = 0.0; // 실제 데이터로 대체 필요

                        Place place = Place.createPlace(
                                new ArrayList<>(), // 초기 이벤트 리스트
                                name,
                                new ArrayList<>(), // 초기 리뷰 리스트
                                tel,
                                address,
                                x,
                                y,
                                category
                        );

                        places.add(place);
                        log.info("Loaded place: {}", place);
                    }

                    placeRepository.saveAll(places);
                    log.info("All places saved successfully from CSV: {}", csvFile);
                }
            } catch (Exception e) {
                log.error("Error reading CSV file: {}", csvFile, e);
            }
        }


    }
}
