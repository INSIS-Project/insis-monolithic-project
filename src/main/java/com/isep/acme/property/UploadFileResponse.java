package com.isep.acme.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UploadFileResponse {

    private String fileName;
    public String fileDownloadUri;
    private String fileType;
    private long size;
}
