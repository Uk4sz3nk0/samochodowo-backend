package com.lukaszwodniak.samochodowo.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;
import java.util.UUID;

/**
 * Manufacturer
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
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Min(3)
    @Max(60)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Model> models;
}
