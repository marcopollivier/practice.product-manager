package com.github.marcopollivier.avenuecode.productmanager.app.controller.rest;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter.ProductAdapter;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Product", description = "Product operations.")
@RestController
@RequestMapping(value = "/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Create Product", notes = "Create a product.")
    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody ProductDTO dto) {
        Product product = new ProductAdapter(dto).convertToEntity();

        productService.saveOrUpdate(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Product", notes = "Update product.")
    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("productId") long productId,
                                       @Valid @RequestBody ProductDTO dto) {
        Product product = new ProductAdapter(dto).convertToEntity();

        productService.saveOrUpdate(productId, product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Product", notes = "Retrieve all products.")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("productId") long productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Gets
    @ApiOperation(value = "Retrieve All Single Products", notes = "Get all products excluding relationships ")
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> requestAllComplete() {

        List<Product> products = productService.retrieveAllSingleProducts();

        ProductAdapter adapter = new ProductAdapter(products);
        List<ProductDTO> productDTOS = adapter.fromEntityList();

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a specific Single Products", notes = "Get a specific product excluding relationships ")
    @RequestMapping(method = RequestMethod.GET, value = "{productId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> requestSpecificComplete(@PathVariable("productId") long productId) {

        Product product = productService.retrieveASingleProduct(productId);

        ProductAdapter adapter = new ProductAdapter(product);
        ProductDTO productDTO = adapter.convertToDTO();

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

}
