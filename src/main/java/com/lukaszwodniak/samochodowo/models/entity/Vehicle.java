package com.lukaszwodniak.samochodowo.models.entity;

import com.lukaszwodniak.samochodowo.enums.Fuel;
import com.lukaszwodniak.samochodowo.enums.Gearbox;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Vehicle
 * <br>
 * Created on: 2025-06-24
 *
 * @author Łukasz Wodniak
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @Column(nullable = false)
    private short productionYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;
    @Column(nullable = false)
    private short engineSize;
    @Column(nullable = false)
    private short horsePower;
    @Max(9)
    private String licensePlate;
    @Min(15)
    @Max(20)
    @Column(nullable = false)
    private String vin;
    @Column(nullable = false)
    private boolean isFirstOwner;
    @Column(nullable = false)
    private boolean isFirstOwnerInPoland;
}
