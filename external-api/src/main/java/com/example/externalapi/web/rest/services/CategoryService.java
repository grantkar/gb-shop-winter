package com.example.externalapi.web.rest.services;

import com.example.externalapi.web.rest.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "localhost:8080/api/v1/category", name = "categoryServiceFeignClient")
public interface CategoryService extends CategoryApi {
}
