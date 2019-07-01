package com.yzd.influxdb.service.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ups on 17/12/17.
 */

@Service
public class RestClient implements IRestClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    RestTemplate restTemplate;

    public RestClient() {
        restTemplate = new RestTemplate();
    }

    @Override
    public String get(String uri) {
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info("Successful response code: " + responseEntity.getStatusCode() + " on " + uri);
            return responseEntity.getBody();
        }
        throw new IllegalStateException("信息读取失败！");
    }


}
