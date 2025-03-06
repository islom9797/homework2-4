package org.islom.dars241.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Transfer entity
@Data
@Entity
@Setter
@Getter
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private double fee;
    private String timestamp;

    @ManyToOne
    private Card sender;

    @ManyToOne
    private Card receiver;
}
