package com.example.deliveryecommercebackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blacklist")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String blacklist_id;
    private String code;
    private String des;
    private boolean is_active;
    @OneToOne
    @JoinColumn(name = "user_id" )
    private User user;




}
