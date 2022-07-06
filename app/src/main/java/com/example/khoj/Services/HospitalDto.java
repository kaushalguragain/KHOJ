package com.example.khoj.Services;
import com.google.firebase.firestore.PropertyName;
public class HospitalDto {
        Long id;
        String name;
        String slogan;
        String rating;
        String servicenumber;
        String covidhotline;
        String description;
        String clinic_availability;
        String specialization;
        String image;
        String longitude;
        String latitude;
        public HospitalDto(){

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


        public HospitalDto(String name, String slogan, String rating,
                           String servicenumber, String covidhotline,  String description,
                         String clinic_availability, String specialization, String image, String longitude,
                        String latitude, Long id){

            this.name = name;
            this.description = description;
            this.rating = rating;
            this.slogan = slogan;
            this.image = image;
            this.longitude = longitude;
            this.latitude = latitude;
            this.servicenumber = servicenumber;
            this.id =id;
            this.covidhotline=covidhotline;
            this.clinic_availability=clinic_availability;
            this.specialization=specialization;

        }

        public Long getId() {
            return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getServicenumber() {
        return servicenumber;
    }

    public void setServicenumber(String servicenumber) {
        this.servicenumber = servicenumber;
    }

    public String getCovidhotline() {
        return covidhotline;
    }

    public void setCovidhotline(String covidhotline) {
        this.covidhotline = covidhotline;
    }

    public String getClinic_availability() {
        return clinic_availability;
    }

    public void setClinic_availability(String clinic_availability) {
        this.clinic_availability = clinic_availability;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
}


