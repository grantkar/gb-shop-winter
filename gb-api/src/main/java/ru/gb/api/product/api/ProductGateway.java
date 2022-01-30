package ru.gb.api.product.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.dto.ProductDto;
import ru.gb.api.productManufacturerDto.ProductManufacturerDto;

import java.util.List;

public interface ProductGateway {


    @GetMapping
    List<ProductDto> getProductList();

    @GetMapping("/info")
    List<ProductManufacturerDto> getInfoProductList();

    @GetMapping("/{productId}")
    ResponseEntity<?> getProduct(@PathVariable("productId") Long id);

    @PostMapping
    ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto);

    @PutMapping("/{productId}")
    ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto);

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("productId") Long id);
}
