package com.github.marcopollivier.avenuecode.productmanager.app.controller;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Product", description = "Product operations.")
@RestController
@RequestMapping(value = "/product")
public class ProductRestController {

    //TODO refatorar para utilizar uma implementação jax-rs

    @ApiOperation(value = "Consultar Pagamento", notes = "Consultar os dados do pagamento solicitado.")
    @RequestMapping(value = "{productId}")
    @GetMapping
    public ResponseEntity<Void> request(@PathVariable("productId") String productId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
