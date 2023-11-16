package br.com.infnet;

import br.com.infnet.exception.ResourceNotFoundException;
import br.com.infnet.model.Location;
import br.com.infnet.model.PayloadLocationsList;
import br.com.infnet.util.LocationUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LocationsTests {
    @Test
    @DisplayName("Deve retornar uma localizacao pelo ID")
    public void getLocationById(){
        LocationUtil locationUtil = new LocationUtil();
        Location location = locationUtil.getById(100);
        assertEquals("Ricks’s Story", location.getName());
    }
    @Test
    @DisplayName("Deve retornar uma exception quando ID nao existe")
    public void getByLocationInexistente(){
        LocationUtil locationUtil = new LocationUtil();
        assertThrows(ResourceNotFoundException.class , () ->{
            Location location = locationUtil.getById(600);
        });
    }
    @Test
    @DisplayName("Deve retornar todas as Locations")
    public void getAllLocations(){
        LocationUtil locationUtil = new LocationUtil();
        assertEquals(126,locationUtil.getAllLocations().getInfo().getCount());

        PayloadLocationsList page2 = locationUtil.getAllLocations(2);
        assertEquals("Testicle Monster Dimension",page2.getResults().get(0).getName());

        PayloadLocationsList page3 = locationUtil.getAllLocations(3);
        assertEquals("Hamster in Butt World",page3.getResults().get(0).getName());


    }
}
