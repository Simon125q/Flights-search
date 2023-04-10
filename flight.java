import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        FlightDatabase database = new FlightDatabase();
        database.checkIfFlightExists("Warsaw", "Barcelona");
        database.displayFlightsFromCity("Berlin");
        database.displayFlightsToCity("Roma");
        System.out.println("Cities: " + database.getCities());
        Flight cheapest = database.getCheapestFlight();
        System.out.println("Cheapest Flight: ");
        cheapest.Direction();
        ArrayList<Journey> flights = database.getFlights("Tokyo", "Warsaw");
        System.out.println(flights.toString());

    }
}
class Flight {
    String departure;
    String arrival;
    int price;
    
    
    public Flight(String departure, String arrival, int price) {
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }
    public void Direction() {
        System.out.println("Flight from " + this.departure + " to " + this.arrival +
         " costs " + this.price);
    }
}

class FlightDatabase {
    ArrayList<Flight> flights = new ArrayList<Flight>();

    public FlightDatabase() {
        this.flights.add(new Flight("Berlin", "Warsaw", 200));
        this.flights.add(new Flight("Tokyo", "Berlin", 580));
        this.flights.add(new Flight("Paris", "Barcelona", 300));
        this.flights.add(new Flight("Madrid", "New York", 640));
        this.flights.add(new Flight("Berlin", "Barcelona", 200));
        this.flights.add(new Flight("Barcelona", "Warsaw", 250));
        this.flights.add(new Flight("Porto", "Madrid", 130));
        this.flights.add(new Flight("Berlin", "Roma", 190));
        this.flights.add(new Flight("Chicago", "Berlin", 710));
        this.flights.add(new Flight("Roma", "Paris", 120));
    }

    public void checkIfFlightExists(String start, String end) {
        boolean exist = false;
        for(int i = 0; i < flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(flight.departure.equals(start) && flight.arrival.equals(end))
            {
                System.out.println("Flight exists");
                exist = true;
                break;
            }
        }
        if(exist == false)
        {
            System.out.println("Flight doesnt exists");
        }
    }

    public ArrayList<Flight> getFlightsFromCity(String city) {
        ArrayList<Flight> resoults = new ArrayList<Flight>();
        for(int i = 0; i < flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(flight.departure.equals(city))
            {
                resoults.add(flight);
            }
        }
        return resoults;
    }

    public ArrayList<Flight> getFlightsToCity(String city) {
        ArrayList<Flight> resoults = new ArrayList<Flight>();
        for(int i = 0; i < flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(flight.arrival.equals(city))
            {
                resoults.add(flight);
            }
        }
        return resoults;
    }

    public void displayFlights(ArrayList<Flight> results) {
        if (results.isEmpty()) {
            System.out.println("Flight not found");
        }
        for(int i=0; i<results.size(); i++)
        {
            Flight flight = results.get(i);
            flight.Direction();;
        }
    }

    public void displayFlightsFromCity(String city) {
        ArrayList<Flight> results = getFlightsFromCity(city);
        displayFlights(results);
    }

    public void displayFlightsToCity(String city) {
        ArrayList<Flight> results = getFlightsToCity(city);
        displayFlights(results);
    }

    public ArrayList<String> getCities() {
        ArrayList<String> cities = new ArrayList<String>();
        for(int i = 0; i < this.flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            
            if(!cities.contains(flight.arrival))
            {
                cities.add(flight.arrival);
            }
            else if(!cities.contains(flight.departure))
            {
                cities.add(flight.departure);
            }
        }
        return cities;
    }

    public Flight getCheapestFlight() {
        Flight cheapestFlight = null;
        for(Flight flight : this.flights)
        {
            if(cheapestFlight == null || flight.price < cheapestFlight.price)
            {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }

    public Flight getCheapestFlightFromCity(String City) {
        ArrayList<Flight> fromCity = getFlightsFromCity(City);
        Flight cheapestFlight = null;
        for(Flight flight : fromCity)
        {
            if(cheapestFlight == null || flight.price < cheapestFlight.price)
            {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }

    public ArrayList<Journey> getFlights(String start, String end) {
        ArrayList<Flight> fromCity = getFlightsFromCity(start);
        ArrayList<Flight> toCity = getFlightsToCity(end);
        ArrayList<Journey> results = new ArrayList<>();
        for(Flight flightFrom : fromCity) {
            for(Flight flightTo : toCity) {
                if(flightFrom.arrival.equals(flightTo.departure)) {
                    results.add(new Journey(flightFrom, flightTo));
                }
            }
        }
        return results;
    }
}

class Journey {
    Flight first;
    Flight secound;

    public Journey(Flight first, Flight secound) {
        this.first = first;
        this.secound = secound;
    }

    public String toString() {
        return "Flight from " + first.departure + " to " + secound.arrival + 
        " with stop at " + first.arrival + " costs " + (first.price + secound.price);
    }
}
