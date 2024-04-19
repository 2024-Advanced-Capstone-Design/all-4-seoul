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

    private List<Result> places;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private String id;
        private Double rating;
        private URI websiteUri;
        private DisplayName displayName;
        private CurrentOpeningHours currentOpeningHours;
        private List<Review> reviews;
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
        private List<Period> periods;
        private List<String> weekdayDescriptions;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Review {
        private String name;
        private String relativePublishTimeDescription;
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
        private URI uri;
        private URI photoUri;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Period {
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
