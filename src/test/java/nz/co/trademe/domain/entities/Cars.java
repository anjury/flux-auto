package nz.co.trademe.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Cars {

//    @JsonProperty("TotalCount")
//    public int totalCount;
//    public boolean totalCountTruncated;
//    public int page;
//    public int pageSize;

    @JsonProperty("List")
    public List<Car> carList;

    public Cars() {};

    public String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    static public Cars fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, Cars.class);
    }

}
