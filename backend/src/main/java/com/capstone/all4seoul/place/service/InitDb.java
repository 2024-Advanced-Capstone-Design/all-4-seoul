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

    }
}
