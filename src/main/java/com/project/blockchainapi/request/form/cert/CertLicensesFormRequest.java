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
public class CertLicensesFormRequest extends CertificateFormRequest {
    @NotBlank
    private String licensesName;
    @NotBlank
    private String publisher;
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "Date must be in yyyy-MM format")
    private String publishFrom;
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "Date must be in yyyy-MM format")
    private String publishTo;
    @NotBlank
    private String licensesCode;
    @NotBlank
    private String licensesUrl;

    @Builder(builderMethodName = "licensesBuilder")
    public CertLicensesFormRequest(FormType type) {
        super(type);
    }

    @Override
    public FormType getFormType() {
        return FormType.CERT_LICENSES;
    }
}
