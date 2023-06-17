package com.project.blockchainapi.repo.projection;

public interface MetadataProjection {

    String getObjectId();

    String getFormFieldKey();

    String getFieldValue();

    String getFieldDataType();
}
