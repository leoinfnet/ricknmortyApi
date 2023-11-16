package br.com.infnet.model;

import lombok.Data;

import java.util.List;

@Data
public class PayloadLocationsList {
    private Info info;
    private List<Location> results;

}
