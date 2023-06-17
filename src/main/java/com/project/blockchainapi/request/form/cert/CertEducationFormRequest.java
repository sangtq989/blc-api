package com.project.blockchainapi.request.form.cert;

import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.request.form.CertificateFormRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "Date must be in yyyy-MM format")
    private String startTime;
    @NotNull
    private String endTime;
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
