package com.example.oauth2clientgithub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 *  使用 RestTemplate 携带的 access_token 请求资源服务器，获取信息
 */

@RestController
public class ResourceEndpointController {

        private static  final String URL_GET_USER_PHONE = "http://auth-server:9999/phone";

        @Autowired
        OAuth2AuthorizedClientService auth2AuthorizedClientService;


        private RestTemplate restTemplate;

        private RestTemplate getRestTemplate(){
            if(restTemplate ==null){
                restTemplate = new RestTemplate();

            }
            return restTemplate;
        }


        @GetMapping("/phone")
        public String userinfo(OAuth2AuthenticationToken authentication){


            OAuth2AuthorizedClient  auth2AuthorizedClient  = auth2AuthorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(),authentication.getName());

            HttpHeaders  headers =  new HttpHeaders();
            headers.set("Authorization","Bearer "+auth2AuthorizedClient.getAccessToken().getTokenValue());

            HttpEntity<String> requestEntity = new HttpEntity<String>(null,headers);

            ResponseEntity<String> response = getRestTemplate().exchange(URL_GET_USER_PHONE, HttpMethod.GET,requestEntity,String.class);

            return response.getBody();
        }



}
