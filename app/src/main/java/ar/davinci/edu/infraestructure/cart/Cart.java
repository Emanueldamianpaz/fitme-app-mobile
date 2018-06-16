package ar.davinci.edu.infraestructure.cart;

import ar.davinci.edu.infraestructure.models.Flight;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Flight> flightList;
    private static Cart instance = null;

    protected Cart() {
        flightList = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }


    public List<Flight> getFlightList() {
        return flightList;
    }

    public void addFlight(Flight flight) {
        flightList.add(flight);
    }

}
