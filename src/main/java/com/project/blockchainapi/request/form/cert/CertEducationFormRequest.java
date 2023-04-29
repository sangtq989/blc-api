package com.project.blockchainapi.request.form.cert;

import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.request.form.CertificateFormRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CertEducationFormRequest extends CertificateFormRequest {
    @NotBlank
    private String uniName;
    @NotBlank
    private String degree;
    @NotBlank
    private String major;
    @NotNull
    private LocalDate startTime;
    private LocalDate endTime;
    private String gpa;
    private String description;

    @Builder(builderMethodName = "eduBuilder")
    public CertEducationFormRequest(FormType type) {
        super(type);
    }
    @Override
    public FormType getFormType() {
        return FormType.CERT_EDU;
    }
}
