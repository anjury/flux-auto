package nz.co.trademe.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

    @JsonProperty("Make")
    private String make;

    public String getMake() {
        return make;
    }

}
