package br.com.infnet.util;

import br.com.infnet.exception.ResourceNotFoundException;
import br.com.infnet.model.Info;
import br.com.infnet.model.Location;
import br.com.infnet.model.PayloadLocationsList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocationUtil {
    public Location getById(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(new URI("https://rickandmortyapi.com/api/location/" + id))
                    .build();


            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 404){
                throw new ResourceNotFoundException("Localizacao nao encontrada");
            }
            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            Location location = mapper.readValue(response.body(), Location.class);
            return location;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public PayloadLocationsList getAllLocations() {
        String url = "https://rickandmortyapi.com/api/location/";
        return getAllLocations(url);
    }
    public PayloadLocationsList getAllLocations(int page) {
        String url = "https://rickandmortyapi.com/api/location/?page=" + page;
        return getAllLocations(url);
    }
    private PayloadLocationsList getAllLocations(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(new URI(url))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            PayloadLocationsList payloadLocationsList = mapper.readValue(response.body(), PayloadLocationsList.class);
            return payloadLocationsList;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
