package com.project.blockchainapi.request.form;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.request.form.cert.CertAwardFormRequest;
import com.project.blockchainapi.request.form.cert.CertCourseFormRequest;
import com.project.blockchainapi.request.form.cert.CertEducationFormRequest;
import com.project.blockchainapi.request.form.cert.CertLicensesFormRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CertEducationFormRequest.class, name = "CERT_EDU"),
        @JsonSubTypes.Type(value = CertAwardFormRequest.class, name = "CERT_AWARD"),
        @JsonSubTypes.Type(value = CertLicensesFormRequest.class, name = "CERT_LICENSES"),
        @JsonSubTypes.Type(value = CertCourseFormRequest.class, name = "CERT_COURSE")
})
public class CertificateFormRequest implements BaseFormRequest{
    private FormType type;

    @Override
    public FormType getFormType() {
        return this.type;
    }
}

