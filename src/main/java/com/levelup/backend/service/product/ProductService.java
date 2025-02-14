package com.levelup.backend.service.product;

import com.levelup.backend.api.model.dto.ProductCreateDto;
import com.levelup.backend.api.model.dto.ProductFilterDto;
import com.levelup.backend.api.model.dto.ProductUpdateDto;
import com.levelup.backend.api.rest.exception.ResourceNotFoundException;
import com.levelup.backend.data.access.repository.CategoryRepository;
import com.levelup.backend.data.access.repository.ProductRepository;
import com.levelup.backend.data.access.repository.SupplierRepository;
import com.levelup.backend.data.access.specification.ProductSpecification;
import com.levelup.backend.data.entity.Category;
import com.levelup.backend.data.entity.Product;
import com.levelup.backend.data.entity.Supplier;
import com.levelup.backend.service.converter.PaginationDtoWithFieldsMappingToPageableConverter;
import com.levelup.backend.service.converter.model.PaginationDtoWithFieldsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoWithFieldsMappingToPageableConverter;

    @Autowired
    public ProductService(final ProductRepository productRepository,
                          final CategoryRepository categoryRepository,
                          final SupplierRepository supplierRepository,
                          PaginationDtoWithFieldsMappingToPageableConverter paginationDtoWithFieldsMappingToPageableConverter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.paginationDtoWithFieldsMappingToPageableConverter = paginationDtoWithFieldsMappingToPageableConverter;
    }

    public Page<Product> getPage(final ProductFilterDto productFilterDto) {
        final Specification<Product> specification = ProductSpecification.filterBy(productFilterDto);

        final Pageable pageable = paginationDtoWithFieldsMappingToPageableConverter.convert(new PaginationDtoWithFieldsMapping(productFilterDto));
        Objects.requireNonNull(pageable);

        return productRepository.findAll(specification, pageable);
    }

    public Product findById(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product createProduct(final ProductCreateDto productCreateDto) {
        Objects.requireNonNull(productCreateDto);

        Category category = categoryRepository.findById(productCreateDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Supplier supplier = supplierRepository.findById(productCreateDto.getSupplier())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        final Product product = new Product();
        product.setName(productCreateDto.getName());
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setBuyPrice(productCreateDto.getBuyPrice());
        product.setSellPrice(productCreateDto.getSellPrice());
        product.setBarCode(productCreateDto.getBarCode());
        product.setImage(productCreateDto.getImage());

        return productRepository.save(product);

    }

    public Product updateProduct(final Long id, final ProductUpdateDto productUpdateDto) {
        Objects.requireNonNull(productUpdateDto);
        Objects.requireNonNull(id);

        Category category = categoryRepository.findById(productUpdateDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Supplier supplier = supplierRepository.findById(productUpdateDto.getSupplier())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        final Product product = findById(id);
        product.setName(productUpdateDto.getName());
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setBuyPrice(productUpdateDto.getBuyPrice());
        product.setSellPrice(productUpdateDto.getSellPrice());
        product.setBarCode(productUpdateDto.getBarCode());
        product.setImage(productUpdateDto.getImage());
        product.setInventory(productUpdateDto.getInventory());

        return productRepository.save(product);
    }
}
