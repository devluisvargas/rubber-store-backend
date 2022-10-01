package com.devluis.rubberstore.services;

import com.devluis.rubberstore.models.Product;
import com.devluis.rubberstore.repository.ProductRepository;
import com.devluis.rubberstore.utils.NotFoundProductException;
import com.devluis.rubberstore.utils.ProductException;
import com.devluis.rubberstore.utils.ProductImgException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getOne(Long id) throws NotFoundProductException {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundProductException("Not found product", "Not found product with id " + id));
    }

    @Override
    public void deleteById(Long id) throws NotFoundProductException {
        Product product = this.getOne(id);
        this.productRepository.delete(product);
    }

    @Override
    public void create(Product product) throws ProductException {
        if (product.getId() != null) {
            Optional<Product> productOptional = this.productRepository.findById(product.getId());
            if (productOptional.isPresent())
                throw new ProductException("Exists Product", "Product already with id " + product.getId());
        }
        this.productRepository.save(product);
    }

    @Override
    public void update(Long idProduct, Product product) throws NotFoundProductException {
        Product productUpdate = this.getOne(idProduct);
        productUpdate.setName(product.getName());
        productUpdate.setPrice(product.getPrice());
    }

    @Override
    public void addImg(Long idProduct, MultipartFile multipartFile) throws IOException, NotFoundProductException, ProductImgException {
        Product product = this.getOne(idProduct);
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null)
            throw new ProductImgException("Image not valid", "Error with file in product");
        Map<String, String> resultMap = cloudinaryService.upload(multipartFile);
        product.setImgUrl(resultMap.get("url"));
        product.setImgId(resultMap.get("public_id"));
        this.productRepository.save(product);
    }
}
