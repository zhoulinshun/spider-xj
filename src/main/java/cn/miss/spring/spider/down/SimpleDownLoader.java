package cn.miss.spring.spider.down;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Component
public class SimpleDownLoader implements DownLoader {

    @Autowired
    private RestTemplate restTemplate;

    @Getter
    @Setter
    String cookie;

    @Override
    public String download(String url) {
        return get(url);
    }

    @Override
    public String get(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String get(String url, Map<String, Object> urlParams) {
        return restTemplate.getForObject(url, String.class, urlParams);
    }

    @Override
    public String getWithLogin(String url, Map<String, Object> urlParams) {
        return null;
    }

    @Override
    public String post(String url) {
        return restTemplate.postForObject(url, null, String.class);
    }

    public String post(String url, Map<String, Object> params) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.setAll(params);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        final HttpEntity<MultiValueMap> stringHttpEntity = new HttpEntity<>(requestEntity, requestHeaders);
        return restTemplate.postForObject(url, stringHttpEntity, String.class);
    }

    @Override
    public String postWithLogin(String url, Map<String, Object> params) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.setAll(params);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", cookie);
        requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        final HttpEntity<MultiValueMap> stringHttpEntity = new HttpEntity<>(requestEntity, requestHeaders);
        return restTemplate.postForObject(url, stringHttpEntity, String.class);
    }


}
