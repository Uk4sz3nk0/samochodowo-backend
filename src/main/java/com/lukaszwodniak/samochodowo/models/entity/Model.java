package com.lukaszwodniak.samochodowo.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Model - Entity class that represents vehicle model of manufacturer
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
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 1, max = 50)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    
    @Size(min = 1, max = 50)
    private String generation;
}
