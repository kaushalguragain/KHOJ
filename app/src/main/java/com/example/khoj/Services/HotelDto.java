package com.example.khoj.Services;

import com.google.firebase.firestore.PropertyName;

public class HotelDto {

    Long id;
    String name;
    String description;
    String rating;
    String openingTime;
    String closingTime;
    String largeDescription;
    String lunch;
    String dinner;
    String priceRange;
    String minPrice;
    String image;
    String longitude;
    String latitude;
    public HotelDto(){

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

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public HotelDto(String name, String description, String rating,
                    String openingTime, String closingTime, String largeDescription,
                    String lunch, String dinner, String priceRange, String minPrice, String image, String longitude,
                    String latitude, Long id){

        this.name = name;
        this.description = description;
        this.rating = rating;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.largeDescription = largeDescription;
        this.lunch = lunch;
        this.dinner = dinner;
        this.priceRange = priceRange;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.minPrice = minPrice;
        this.id =id;

    }

    public Long getId() {
        return id;
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

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }



    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
