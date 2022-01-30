package com.example.externalapi.web.rest.services;

import com.example.externalapi.web.rest.ManufacturerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "localhost:8080/api/v1/manufacturer")
public interface ManufacturerService extends ManufacturerApi {
}
