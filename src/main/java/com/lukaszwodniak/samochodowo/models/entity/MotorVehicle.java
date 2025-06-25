package com.lukaszwodniak.samochodowo.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MotorVehicle - Parent class for Car, Truck or DeliveryVehicle
 * <br>
 * Created on: 2025-06-25
 *
 * @author Łukasz Wodniak
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "motor_vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MotorVehicle extends Vehicle {

    private long distance;
}
