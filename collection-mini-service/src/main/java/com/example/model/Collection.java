package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "collection")
@Schema(description = "collection bilgileri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Integer collectionId;

    @Column(name = "policy_id")
    private Long policyId;

    @Column(name = "tutar")
    private Long tutar;

    @Column(name = "odenme_durumu")
    private boolean odenmeDurumu;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "collection_date")
    private Date collectionDate;


}
