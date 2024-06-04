package com.capstone.all4seoul.place.dto.response.externalApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceTextSearchResponseByGoogle {
    private List<Place> places;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
        private String websiteUri;
        private List<String> types;
        private String formattedAddress;
        private List<Review> reviews;
        private DisplayName displayName;
        private Double rating;
        private Long userRatingCount;
        private Location location;
        private String nationalPhoneNumber;
        private List<Photo> photos;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Review {
            private DisplayName originalText;
            private OffsetDateTime publishTime;
            private String name;
            private Long rating;
            private String relativePublishTimeDescription;
            private DisplayName text;
            private AuthorAttribution authorAttribution;
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class DisplayName {
            private String text;
            private String languageCode;
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Location {
            private Double latitude;
            private Double Longitude;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Photo {
            private List<AuthorAttribution> authorAttributions;
            private Long widthPx;
            private Long heightPx;
            private String name;
            private String photoUri;
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class AuthorAttribution {
            private String displayName;
            private String photoUri;
            private String uri;
        }
    }
}
