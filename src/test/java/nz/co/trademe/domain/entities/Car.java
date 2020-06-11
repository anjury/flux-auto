package nz.co.trademe.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Car {

//    @JsonProperty("ListingId")
//    private long listingId;
    @JsonProperty("Make")
    private String make;

    public Car() {}

    public String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    static public Car fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Car.class);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

//    public long getListingId() {
//        return listingId;
//    }
//
//    public void setListingId(long listingId) {
//        this.listingId = listingId;
//    }

}
