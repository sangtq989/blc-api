package com.project.blockchainapi.entity;


import com.project.blockchainapi.constant.FormType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "metadata")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userBlockAddress;
    private UUID objectId;
    private String fieldValue;

    @Enumerated(EnumType.STRING)
    private FormType formKey;
    private String formFieldKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;

    public Metadata(String userBlockAddress, UUID objectId, String fieldValue, FormType formKey, String formFieldKey, UserInfo user) {
        this.userBlockAddress = userBlockAddress;
        this.objectId = objectId;
        this.fieldValue = fieldValue;
        this.formKey = formKey;
        this.formFieldKey = formFieldKey;
        this.user = user;
    }
}
