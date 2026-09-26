package com.expense.service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "external_id")
    @JsonProperty(value = "external_id")
    private String externalId;

    @Column(name = "user_id")
    @JsonProperty(value = "user_id")
    private String userId;

    @Column(name = "amount")
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @Column(name = "merchant")
    @JsonProperty(value = "merchant")
    private String merchant;

    @Column(name = "currency")
    @JsonProperty(value = "currency")
    private String currency;

    @Column(name = "created_at")
    @JsonProperty(value = "created_at")
    private Timestamp createdAt;

    @PrePersist
    @PreUpdate
    private void generatreExternalId() {
        if (externalId == null) {
            externalId = UUID.randomUUID().toString();
        }
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }

}
