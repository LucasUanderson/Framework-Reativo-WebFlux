package com.lucas.webclientrickandmorty.controller;


import com.lucas.webclientrickandmorty.client.RickAndMortyClient;
import com.lucas.webclientrickandmorty.response.CharacterResponse;
import com.lucas.webclientrickandmorty.response.EpisodeResponse;
import com.lucas.webclientrickandmorty.response.ListOfEpisodeResponse;
import com.lucas.webclientrickandmorty.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    @Autowired
    private RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id ){
        return rickAndMortyClient.getCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id ){
        return rickAndMortyClient.getLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id ){
        return rickAndMortyClient.getEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodeResponse> getAllEpisode(){
        return rickAndMortyClient.getAllEpisodes();
    }

}
