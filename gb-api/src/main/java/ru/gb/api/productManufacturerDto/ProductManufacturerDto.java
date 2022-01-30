package ru.gb.api.productManufacturerDto;

import lombok.*;
import ru.gb.api.manufacturer.dto.ManufacturerDto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductManufacturerDto {
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate manufactureDate;
    private ManufacturerDto manufacturerDto;
}
