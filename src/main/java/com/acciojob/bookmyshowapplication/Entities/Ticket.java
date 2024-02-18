package com.acciojob.bookmyshowapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String seatNosBooked;

    private Integer totalAmountPaid;

    @JoinColumn
    @JsonIgnore
    @ManyToOne
    private Show show;



    @JoinColumn
    @JsonIgnore
    @ManyToOne
    private User user;
}
