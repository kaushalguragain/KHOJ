package com.example.khoj.Services;
import com.google.firebase.firestore.PropertyName;
public class LandmarksDto {
        Long id;
        String name;
        String description;
        String rating;
        String location;
        String historicalImportance;
        String largeDescription;
        String image;
        String longitude;
        String latitude;
        public LandmarksDto(){

        }



        public String getLargeDescription() {
            return largeDescription;
        }

        public void setLargeDescription(String largeDescription) {
            this.largeDescription = largeDescription;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }



        public LandmarksDto(String name, String description, String rating,
                           String location, String historicalImportance, String largeDescription,
                            String image, String longitude,
                           String latitude, Long id){

            this.name = name;
            this.description = description;
            this.rating = rating;
            this.location = location;
            this.historicalImportance = historicalImportance;
            this.largeDescription = largeDescription;
            this.image = image;
            this.longitude = longitude;
            this.latitude = latitude;
            this.id =id;

        }

        public Long getId() {
            return id;
        }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHistoricalImportance() {
        return historicalImportance;
    }

    public void setHistoricalImportance(String historicalImportance) {
        this.historicalImportance = historicalImportance;
    }

    public Long setId(long id) {
            this.id = id;

        return null;
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }




}
