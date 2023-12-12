package com.example.deliveryecommercebackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="history_delivery")
public class HistoryDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String delivery_history_id;
    private String order_id;
    private String branch_id;
    private LocalDateTime data_time;
    private String input_by;
    private String state;
    @Column(columnDefinition = "TEXT")
    private String image;
    private String shipper_code;
    private long money_collect;
    private String reason_reject;
}
