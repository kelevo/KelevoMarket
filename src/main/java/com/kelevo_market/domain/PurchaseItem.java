package com.kelevo_market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItem {

    private int productId;
    private int quantity;
    private double total;
    private boolean active;

}
