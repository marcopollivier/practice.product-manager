package com.github.marcopollivier.avenuecode.productmanager.app.controller.rest;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter.ProductAdapter;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Product", description = "Product operations.")
@RestController
@RequestMapping(value = "/product",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductRestController {

    //TODO refatorar para utilizar uma implementação jax-rs

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Create Product", notes = "Create a product.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody ProductDTO dto) {
        Product product = new ProductAdapter(dto).convertToEntity();

        productService.saveOrUpdate(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Consultar Pagamento", notes = "Retrieve all products.")
    @RequestMapping(method = RequestMethod.GET, value = "{productId}")
    public ResponseEntity<Void> request(@PathVariable("productId") String productId) {



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
