package br.com.infnet.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Location {
    private int id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;
    private LocalDateTime created;
}
