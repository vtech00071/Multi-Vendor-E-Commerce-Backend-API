package com.example.multi_vendor_ecommerce_api.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public  class Response {
    private String message;
    private int statusCode;
    private LocalDateTime timeStamp;
}
