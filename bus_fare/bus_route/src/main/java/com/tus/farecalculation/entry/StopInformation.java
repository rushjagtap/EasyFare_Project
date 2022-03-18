package com.mall.entry;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "stop_information")
@Component
public class StopInformation  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id ;

    @Column(name = "route_information_id")
    private Integer routeInformationId;

    @Column(name = "stop_number")
    private Integer stopNumber;

    @Column(name = "stop_name")
    private String stopName;


    private Double distance;

    private BigDecimal price;

    private double latitude;

    private double longitude;




}
