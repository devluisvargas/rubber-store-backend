package com.devluis.rubberstore.services;

import com.devluis.rubberstore.models.Product;
import com.devluis.rubberstore.utils.NotFoundProductException;
import com.devluis.rubberstore.utils.ProductException;
import com.devluis.rubberstore.utils.ProductImgException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getOne(Long id) throws NotFoundProductException;

    void deleteById(Long id) throws ProductException, NotFoundProductException;

    void create(Product product) throws ProductException;

    void update(Long idProduct, Product product) throws ProductException, NotFoundProductException;

    void addImg(Long id, MultipartFile multipartFile) throws IOException, ProductException, NotFoundProductException, ProductImgException;
}
