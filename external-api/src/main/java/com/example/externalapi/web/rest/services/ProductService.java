package com.example.externalapi.web.rest.services;

import org.springframework.cloud.openfeign.FeignClient;
import com.example.externalapi.web.rest.ProductApi;

@FeignClient(url = "localhost:8080/api/v1/product", value = "productServiceFeignClient")
public interface ProductService extends ProductApi {
}
