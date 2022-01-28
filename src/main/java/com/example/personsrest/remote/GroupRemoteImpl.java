package com.example.personsrest.remote;
import com.example.personsrest.GroupTest;
import com.example.personsrest.KeyCloakToken;
import lombok.Value;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.net.Authenticator;

public class GroupRemoteImpl implements GroupRemote{
    KeyCloakToken token;
    WebClient webClient = WebClient.create("http://localhost:8080/api/");
   ;

    @Override
    public String getNameById(String groupId) {
        return webClient.get()
                .uri("groups/"+groupId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+generateToken().getAccessToken())
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(GroupTest.class)
                .block().getName();
    }
    @Override

    public String createGroup(String name) {

        CreateGroup body = new CreateGroup(name);
        return webClient.post()
                .uri("groups/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+generateToken().getAccessToken())
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(GroupTest.class)
                .block().getId();

    }

    @Override
    public String removeGroup(String name) {
        return null;
    }

    @Value
    static class CreateGroup {
        String name;
    }




    public KeyCloakToken generateToken (){
        return KeyCloakToken.acquire("https://iam.sensera.se/", "test", "group-api", "user", "djnJnPf7VCQvp3Fc").block();
    }



}
