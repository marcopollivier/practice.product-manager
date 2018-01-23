package com.github.marcopollivier.avenuecode.productmanager.app.controller.rest;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter.ImageAdapter;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter.ProductAdapter;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ImageDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Image;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.service.ImageService;
import com.github.marcopollivier.avenuecode.productmanager.app.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.github.marcopollivier.avenuecode.productmanager.app.controller.ProductRetrieveType.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @RestController
    @RequestMapping(value = "/product")
    @Api(tags = "Product Manager API - Product", description = "Product operations.")
    class ProductRest {

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

        @ApiOperation(value = "Retrieve All Single Products", notes = "Get all products excluding relationships ")
        @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProductDTO>> requestAllSingleProduct() {

            List<Product> products = productService.retrieveProducts(NO_RELATIONSHIP);

            ProductAdapter adapter = new ProductAdapter(products);
            List<ProductDTO> productDTOS = adapter.fromEntityList();

            return new ResponseEntity<>(productDTOS, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve a specific Single Products", notes = "Get a specific product excluding relationships ")
        @RequestMapping(method = RequestMethod.GET, value = "{productId}", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<ProductDTO> requestSpecificSingleProduct(@PathVariable("productId") long productId) {

            Product product = productService.retrieveProduct(NO_RELATIONSHIP, productId);

            ProductAdapter adapter = new ProductAdapter(product);
            ProductDTO productDTO = adapter.convertToDTO();

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve All Complete Products", notes = "Get all products including all relationships.")
        @RequestMapping(method = RequestMethod.GET, value = "/complete", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProductDTO>> requestAllCompleteProduct() {

            List<Product> products = productService.retrieveProducts(FULL_RELATIONSHIP);

            ProductAdapter adapter = new ProductAdapter(products);
            List<ProductDTO> productDTOS = adapter.fromEntityList();

            return new ResponseEntity<>(productDTOS, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve a specific Single Products", notes = "Get a specific product including all relationships ")
        @RequestMapping(method = RequestMethod.GET, value = "{productId}/complete", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<ProductDTO> requestSpecificComplete(@PathVariable("productId") long productId) {

            Product product = productService.retrieveProduct(FULL_RELATIONSHIP, productId);

            ProductAdapter adapter = new ProductAdapter(product);
            ProductDTO productDTO = adapter.convertToDTO();

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve All Products with Images", notes = "Get all products including Images.")
        @RequestMapping(method = RequestMethod.GET, value = "/images", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProductDTO>> requestAllProductsWithImage() {

            List<Product> products = productService.retrieveProducts(ONLY_IMAGES);

            ProductAdapter adapter = new ProductAdapter(products);
            List<ProductDTO> productDTOS = adapter.fromEntityList();

            return new ResponseEntity<>(productDTOS, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve a specific Product with Images", notes = "Get a specific product including images")
        @RequestMapping(method = RequestMethod.GET, value = "{productId}/images", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<ProductDTO> requestSpecificProductWithImages(@PathVariable("productId") long productId) {

            Product product = productService.retrieveProduct(ONLY_IMAGES, productId);

            ProductAdapter adapter = new ProductAdapter(product);
            ProductDTO productDTO = adapter.convertToDTO();

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve All Products with Child Product", notes = "Get all products including Child Products.")
        @RequestMapping(method = RequestMethod.GET, value = "/child-products", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProductDTO>> requestAllProductsWithChildren() {

            List<Product> products = productService.retrieveProducts(ONLY_CHILD_PRODUCT);

            ProductAdapter adapter = new ProductAdapter(products);
            List<ProductDTO> productDTOS = adapter.fromEntityList();

            return new ResponseEntity<>(productDTOS, HttpStatus.OK);
        }

        @ApiOperation(value = "Retrieve a specific Product with Child Product", notes = "Get a specific product including Child Product")
        @RequestMapping(method = RequestMethod.GET, value = "{productId}/child-products", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<ProductDTO> requestSpecificProductWithChildProduct(@PathVariable("productId") long productId) {

            Product product = productService.retrieveProduct(ONLY_CHILD_PRODUCT, productId);

            ProductAdapter adapter = new ProductAdapter(product);
            ProductDTO productDTO = adapter.convertToDTO();

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
    }


    @RestController
    @RequestMapping(value = "/child-products")
    @Api(tags = "Product Manager API - Child Products", description = "Child Products operations.")
    class ChildProductRest {

        @ApiOperation(value = "Retrieve Child Products", notes = "Get set of images for specific product")
        @RequestMapping(method = RequestMethod.GET, value = "/product/{productId}", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProductDTO>> requestSetImages(@PathVariable("productId") long productId) {

            List<Product> products = productService.retrieveChildProducts(productId);

            ProductAdapter adapter = new ProductAdapter(products);
            List<ProductDTO> productDTOS = adapter.fromEntityList();

            return new ResponseEntity<>(productDTOS, HttpStatus.OK);
        }

    }

    @RestController
    @RequestMapping(value = "/image")
    @Api(tags = "Product Manager API - Image", description = "Image operations.")
    class ImageRest {

        @ApiOperation(value = "Retrieve images", notes = "Get set of child products for specific product")
        @RequestMapping(method = RequestMethod.GET, value = "/product/{productId}", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ImageDTO>> requestSpecificProductWithChildren(@PathVariable("productId") long productId) {

            List<Image> images = imageService.retrieveImages(productId);

            ImageAdapter adapter = new ImageAdapter(images);
            List<ImageDTO> imageDTOS = adapter.fromEntityList();

            return new ResponseEntity<>(imageDTOS, HttpStatus.OK);
        }

    }

}