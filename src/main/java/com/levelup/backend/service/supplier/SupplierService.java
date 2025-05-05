package com.levelup.backend.service.supplier;

import com.levelup.backend.api.model.dto.SupplierDto;
import com.levelup.backend.api.model.dto.SupplierFilterDto;
import com.levelup.backend.api.rest.exception.ResourceNotFoundException;
import com.levelup.backend.data.access.repository.SupplierRepository;
import com.levelup.backend.data.access.specification.SupplierSpecification;
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
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoToPageableConverter;

    @Autowired
    public SupplierService(final SupplierRepository supplierRepository, final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoToPageableConverter) {
        this.supplierRepository = supplierRepository;
        this.paginationDtoToPageableConverter = paginationDtoToPageableConverter;
    }

    public Page<Supplier> getPage(final SupplierFilterDto filterDto) {
        final Specification<Supplier> specification = SupplierSpecification.filterBy(filterDto);

        final Pageable pageable = paginationDtoToPageableConverter.convert(new PaginationDtoWithFieldsMapping(filterDto));
        Objects.requireNonNull(pageable);

        return supplierRepository.findAll(specification, pageable);
    }

    public Supplier findById(final Long id) {
        return supplierRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Supplier not found"));
    }

    public Supplier createSupplier(final SupplierDto supplierDto) {
        Objects.requireNonNull(supplierDto);

        final Supplier supplier = new Supplier();
        setSupplierParams(supplierDto, supplier);

        return supplierRepository.save(supplier);
    }

    private static void setSupplierParams(SupplierDto supplierDto, Supplier supplier) {
        supplier.setName(supplierDto.getName());
        supplier.setContact(supplierDto.getContact());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setWeb(supplierDto.getWeb());
        supplier.setMinimumOrder(supplierDto.getMinimumOrder());
    }

    public Supplier updateSupplier(final Long id, final SupplierDto supplierDto) {
        Objects.requireNonNull(supplierDto);
        Objects.requireNonNull(id);

        final Supplier supplier = findById(id);

        setSupplierParams(supplierDto, supplier);

        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(final Long id) {
        findById(id);
        supplierRepository.deleteById(id);
    }
}
