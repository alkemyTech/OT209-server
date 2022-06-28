package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Table
@NoArgsConstructor
@Data
@Entity
@ApiModel("Model Role")
public class RoleEntity {

    public RoleEntity(String name, String description, Timestamp timestamp) {
        this.setName(name);
        this.setDescription(description);
        this.setTimestamp(timestamp);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;
    @ApiModelProperty("Descrition the role")
    private String Description;
    @Column(name = "timestamp")
    private Timestamp timestamp;

}
