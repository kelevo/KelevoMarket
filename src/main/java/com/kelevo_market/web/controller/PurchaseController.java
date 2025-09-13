package com.kelevo_market.web.controller;

import com.kelevo_market.domain.Purchase;
import com.kelevo_market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "Kelevo Market API purchases operations")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all purchases",
            description = "Returns all existing purchases",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Purchases found"),
                    @ApiResponse(responseCode = "404", description = "Purchases not found", content = @Content)
            },
            tags = {"Purchases"}
    )
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @Operation(
            summary = "Get all purchases by client id",
            description = "Returns all existing purchases by client id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Purchases found"),
                    @ApiResponse(responseCode = "404", description = "Purchases not found", content = @Content)
            },
            tags = {"Purchases"}
    )
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save purchase",
            description = "Save the sent purchase",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Purchase saved"),
                    @ApiResponse(responseCode = "500", description = "Purchase don't save", content = @Content)
            },
            tags = {"Purchases"}
    )
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
