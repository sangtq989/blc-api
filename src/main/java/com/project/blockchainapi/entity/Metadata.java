package com.project.blockchainapi.entity;


import com.project.blockchainapi.constant.FormType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "form_field_key")
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
