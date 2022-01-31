package com.dians.navigation.web.helper;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import java.util.Map;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHelper {

    public static RestTemplate restTemplate;

    @Autowired
    private RequestHelper(RestTemplate restTemplate) {
        RequestHelper.restTemplate = restTemplate;
    }

    public static String createRequestUrl(String endpoint, Map<String, String> queryParams) {
        String query = "";
        if (!queryParams.isEmpty()) {
            query = queryParams.entrySet()
                .stream()
                .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                .collect(
                    Collectors.joining("&"));
        }
        String placesUrl = "http://places-service:8081/api/places";
        return String.format("%s%s?%s", placesUrl, endpoint, query);
    }

    public static ResponseEntity<FastFood[]> sendGetRequestForFastFoods(String url) {
        return restTemplate.getForEntity(
            url,
            FastFood[].class
        );
    }

    public static ResponseEntity<Pub[]> sendGetRequestForPubs(String url) {
        return restTemplate.getForEntity(
            url,
            Pub[].class
        );
    }

    public static ResponseEntity<?> sendPostRequestForPlace(String url, Class<?> responseType) {
        return restTemplate.postForEntity(
            url,
            void.class,
            responseType
        );
    }

    public static void sendDeleteRequest(String url) {
        restTemplate.delete(
            url
        );
    }

    public static HttpEntity<MultiValueMap<String, Object>> buildPostRequestParams(Object... objects) {
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<>();
        for (int i = 0; i < objects.length; i += 2) {
            requestParams.add(objects[i].toString(), objects[i + 1]);
        }
        return new HttpEntity<>(requestParams, new HttpHeaders());
    }

    public static Integer getHeaderIntValue(String key, ResponseEntity<?> response) {
        return Integer.valueOf(response.getHeaders().get(key).get(0));
    }
}
