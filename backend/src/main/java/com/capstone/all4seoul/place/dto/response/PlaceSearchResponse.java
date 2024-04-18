package com.capstone.all4seoul.place.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchResponse {

    private List<Result> results;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private DisplayName displayName;
        private String placeId;
        private CurrentOpeningHours currentOpeningHours;
        private String nationalPhoneNumber;
        private Reviews reviews;
        //총 별점 평균
        private Double rating;
        private URI websiteUri;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class DisplayName {
        private String text;
        private String languageCode;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class CurrentOpeningHours {
        private Boolean openNow;
        private Periods periods;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Reviews {
        //리뷰 고유 url
        private String name;
        //몇달전
        private String relativePublishTimeDescription;
        //별점
        private Double rating;
        private Text text;
        private Text originalText;
        private AuthorAttribution authorAttribution;
        private String publishTime;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Text {
        private String text;
        private String languageCode;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class AuthorAttribution {
        private String displayName;
        private String uri;
        private String photoUri;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Periods{
        private Open open;
        private Close close;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Open{
        private int day;
        private int hour;
        private int minute;
        private Date date;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Close{
        private int day;
        private int hour;
        private int minute;
        private Date date;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Date {
        private String year;
        private String month;
        private String day;
    }
}
