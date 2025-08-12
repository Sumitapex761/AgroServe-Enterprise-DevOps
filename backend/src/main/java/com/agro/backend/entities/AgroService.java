package com.agro.backend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "AgroService")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AgroService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 50, unique = true)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private AgroServiceProvider provider;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<AgroBooking> bookings = new ArrayList<>();

    public void addBooking(AgroBooking booking) {
        bookings.add(booking);
        booking.setService(this);
    }

    public void removeBooking(AgroBooking booking) {
        bookings.remove(booking);
        booking.setService(null);
    }
}
