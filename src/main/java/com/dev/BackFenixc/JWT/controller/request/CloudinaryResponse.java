package com.dev.BackFenixc.JWT.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudinaryResponse {
    private String publicId;
    private String url;
}
