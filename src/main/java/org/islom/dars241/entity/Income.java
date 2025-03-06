package org.islom.dars241.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Income {
    @Id
    private Long id;

    private Long fromCardId;
    private Long toCardId;
    private double amount;
    private String date;
}
