package com.tus.farecalculation.entry;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "user_travel_history")
@Component
public class RouteHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id ;
    @Column(name = "user_id")
    private Integer userId ;
    @Column(name = "trip_start_time")
    private Date tripStartTime;

    @Column(name = "trip_end_time")
    private Date tripEndTime;

    @Column(name = "route_number")
    private String routeNumber;

    @Column(name = "boarding_point")
    private String boardingPoint;

    @Column(name = "drop_off_point")
    private String dropOffPoint;
    @Column(name = "cost")
    private Double cost;

    @Column(name = "create_time")
    private Date createTime;



    private String busNum;

    private Integer driverId;

    @Column(name = "if_peak_time")
    private Integer ifPeakTime;

}
