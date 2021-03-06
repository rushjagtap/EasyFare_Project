package com.tus.farecalculation.entry;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity(name = "route_information")
@Component
public class RouteInformation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id ;

    @Column(name = "route_number")
    private String routeNumber;

    @Column(name = "bus_number")
    private String busNumber;



}
