package com.lucas.webclientrickandmorty.client;

import com.lucas.webclientrickandmorty.response.CharacterResponse;
import com.lucas.webclientrickandmorty.response.EpisodeResponse;
import com.lucas.webclientrickandmorty.response.ListOfEpisodeResponse;
import com.lucas.webclientrickandmorty.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

    @Autowired
    private WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> getCharacterById(String id){
        log.info("buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException("Error na requisição")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> getLocationById(String id){
        log.info("buscando a localização com o id [{}]", id);
        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException("Error na requisição")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> getEpisodeById(String id){
        log.info("buscando o episodio com o id [{}]", id);
        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException("Error na requisição")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodeResponse> getAllEpisodes(){
        log.info("Listando episodios");
        return webClient
                .get()
                .uri("/episode/" )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException("Error na requisição")))
                .bodyToFlux(ListOfEpisodeResponse.class);
    }





}
