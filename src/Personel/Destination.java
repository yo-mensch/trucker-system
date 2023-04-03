package Personel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Destination{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String start;
    private String destination;
    private int distance;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    @ManyToOne
    private Cargo cargo;
    @ManyToOne
    private Manager responsibleManager;
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<CheckPoint> checkPoint;

    public Destination(String start, String destination, int distance, LocalDate departureDate, LocalDate arrivalDate) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Destination(String start, String destination, int distance, LocalDate departureDate, LocalDate arrivalDate, Cargo cargo, Manager responsibleManager, List<CheckPoint> checkPoint) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.cargo = cargo;
        this.responsibleManager = responsibleManager;
        this.checkPoint = checkPoint;
    }

    @Override
    public String toString() {
        return  "ID = " + id +
                "\nStart = '" + start + '\'' +
                "\nDestination = '" + destination + '\'' +
                "\nDeparture date = '" + departureDate + '\'' +
                "\nArrival date = '" + arrivalDate + '\'';
    }
}
