package org.tudai.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO {
    private String name;
    private String email;
    private Double pricePerKm;
    private Double extraPricePause;

    public AdminDTO() {}

    public AdminDTO(String name, String email, Double pricePerKm, Double extraPricePause) {
        this.name = name;
        this.email = email;
        this.pricePerKm = pricePerKm;
        this.extraPricePause = extraPricePause;
    }
}
