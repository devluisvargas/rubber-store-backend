package com.devluis.rubberstore.controllers;

import com.devluis.rubberstore.models.Product;
import com.devluis.rubberstore.services.ProductService;
import com.devluis.rubberstore.utils.NotFoundProductException;
import com.devluis.rubberstore.utils.ProductException;
import com.devluis.rubberstore.utils.ProductImgException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws NotFoundProductException {
        log.info("Starting to transaction get by id... {}", id);
        try {
            return ResponseEntity.ok(productService.getOne(id));
        } finally {
            log.info("Finish transaction by id");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Product product) throws ProductException {
        log.info("Starting to transaction save... {}", product);
        try {
            productService.create(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } finally {
            log.info("Finish transaction save ...");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Product product) throws NotFoundProductException, ProductException {
        log.info("Starting to transaction update... {}", product);
        try {
            productService.update(id, product);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } finally {
            log.info("Finish transaction update ...");
        }
    }

    @PutMapping("updateImg/{id}")
    public ResponseEntity<?> addImg(@PathVariable Long id, @RequestParam(name = "img") MultipartFile multipartFile) throws ProductImgException, NotFoundProductException, IOException, ProductException {
        log.info("Starting to transaction addImg... {}", id);
        try {
            productService.addImg(id, multipartFile);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } finally {
            log.info("Finish transaction addImg ...");
        }
    }
}
