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
public class CertAwardFormRequest extends CertificateFormRequest {

    @NotBlank
    private String awardName;
    private String awardOrg;
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "Date must be in yyyy-MM format")
    private String publishDate;

    @Builder(builderMethodName = "awardBuilder")
    public CertAwardFormRequest(FormType type) {
        super(type);
    }

    @Override
    public FormType getFormType() {
        return FormType.CERT_AWARD;
    }
}
