package ru.gb.external.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.api.ProductGateway;
import ru.gb.api.product.dto.ProductDto;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductGateway productGateway;

    @GetMapping
    public String getProductList(Model model) {
        model.addAttribute("products", productGateway.getProductList());
        return "product-list";
    }

//    @GetMapping("/info")
//    public List<ProductManufacturerDto> getInfoProductList() {
//        return productService.findAllInfo();
//    }

    @GetMapping("/{productId}")
    public String info(Model model, @PathVariable(name = "productId") Long id) {
        model.addAttribute("product", productGateway.getProduct(id));
        return "product-info";
    }
//    @GetMapping("/{productId}")
//    public ResponseEntity<?> getProduct(@PathVariable("productId") Long id) {
//        return productGateway.getProduct(id);
//    }

//    @PostMapping
//    public ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto) {
//        return productGateway.handlePost(productDto);
//    }
    @PostMapping
    public String saveProduct(ProductDto product) {
        product.setManufactureDate(LocalDate.now());
        productGateway.handlePost(product);
        return "redirect:/product/all";
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto) {
        return productGateway.handleUpdate(id, productDto);
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        productGateway.deleteById(id);
        return "redirect:/api/v1/product";
    }

//    @DeleteMapping("/{productId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable("productId") Long id) {
//        productGateway.deleteById(id);
//    }
}
