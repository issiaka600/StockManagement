package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.product.CreateProductDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.dto.product.ProductWithLineStorageDTO;
import com.traore.stockmanagement.dto.product.ProductWithStockMovementDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.CategoryMapper;
import com.traore.stockmanagement.mapper.ProductMapper;
import com.traore.stockmanagement.model.Product;
import com.traore.stockmanagement.repository.CategoryRepo;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.repository.ProductRepo;
import com.traore.stockmanagement.service.AttachmentService;
import com.traore.stockmanagement.service.ProductService;
import com.traore.stockmanagement.validator.ProductValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepo repository;
    private ProductMapper mapper;
    private CategoryRepo categoryRepo;
    private EnterpriseRepo enterpriseRepo;
    private AttachmentService attachmentService;

    @Override
    public CreateProductDTO save(CreateProductDTO dto) {
        List<String> errors = ProductValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Product create dto {} not valid",dto);
            throw new InvalidEntityException("Produit invalide", ErrorCodes.PRODUCT_NOT_VALID, errors);
        }
        if (!categoryRepo.existsById(dto.getCategory().getId())){
            throw new EntityNotFoundException(String.format("Catégorie avec ID {%s} non trouvée", dto.getCategory().getId()), ErrorCodes.CATEGORY_NOT_FOUND);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return mapper.fromEntityToCreateProductDTO(
                repository.save(
                        mapper.fromCreateProductDTOToEntity(dto)
                )
        );
    }

    @Override
    public CreateProductDTO save(MultipartFile file, String name, BigDecimal unitPrice, BigDecimal quantityMin, Long categoryId, Long enterpriseId) {
        CreateProductDTO dto = CreateProductDTO.builder()
                .name(name)
                .unitPrice(unitPrice)
                .quantityMin(quantityMin)
                .category(
                        CategoryDTO.builder()
                                .id(categoryId)
                                .build()
                )
                .enterprise(
                        UpdateEnterpriseDTO.builder()
                                .id(enterpriseId)
                                .build()
                )
                .build();

        List<String> errors = ProductValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Product create dto {} not valid",dto);
            throw new InvalidEntityException("Produit invalide", ErrorCodes.PRODUCT_NOT_VALID, errors);
        }
        if (!categoryRepo.existsById(dto.getCategory().getId())){
            throw new EntityNotFoundException(String.format("Catégorie avec ID {%s} non trouvée", dto.getCategory().getId()), ErrorCodes.CATEGORY_NOT_FOUND);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        //Inserting image
        if (!file.isEmpty()){
            dto.setPhoto(
                    attachmentService.saveImage(file)
            );
        }

        return mapper.fromEntityToCreateProductDTO(
                repository.save(
                        mapper.fromCreateProductDTOToEntity(dto)
                )
        );
    }

    @Override
    public CreateProductDTO update(ProductDTO dto) {
        List<String> errors = ProductValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Product create dto {} not valid",dto);
            throw new InvalidEntityException("Produit invalide", ErrorCodes.PRODUCT_NOT_VALID, errors);
        }

        Product product = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvée", dto.getId()))
        );
        if (!dto.getName().isEmpty() && !dto.getName().isBlank()){
            product.setName(dto.getName());
        }
        if (!dto.getPhoto().isEmpty() && !dto.getPhoto().isBlank()){
            product.setPhoto(dto.getPhoto());
        }
        if (dto.getUnitPrice().compareTo(BigDecimal.ZERO)>0){
            product.setUnitPrice(dto.getUnitPrice());
        }
        if (dto.getQuantityMin().compareTo(BigDecimal.ZERO)>0){
            product.setQuantityMin(dto.getQuantityMin());
        }
        if (dto.getCategory()!= null && dto.getCategory().getId()!= null){
            if (categoryRepo.existsById(dto.getCategory().getId())){
                product.setCategory(
                        CategoryMapper.toEntityStatic(dto.getCategory())
                );
            }
        }

        return mapper.fromEntityToCreateProductDTO(
                repository.save(product)
        );
    }

    @Override
    public CreateProductDTO getProduct(String productId) {
        if (!productId.isEmpty() && !productId.isBlank()){
            Product product = repository.findById(productId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvée", productId), ErrorCodes.PRODUCT_NOT_FOUND)
            );
            return mapper.fromEntityToCreateProductDTO(product);
        }
        throw new InvalidEntityException("Idenfiant invalide pour un produit", ErrorCodes.PRODUCT_NOT_VALID);
    }

    @Override
    public ProductWithLineStorageDTO getProductWithLineStorages(String productId) {
        if (!productId.isEmpty() && !productId.isBlank()){
            Product product = repository.findById(productId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvée", productId), ErrorCodes.PRODUCT_NOT_FOUND)
            );
            return mapper.fromEntityToProductWithLineStorages(
                    product
            );
        }
        throw new InvalidEntityException("Idenfiant invalide pour un produit", ErrorCodes.PRODUCT_NOT_VALID);
    }

    @Override
    public ProductWithStockMovementDTO getProductWithStockMovements(String productId) {
        if (!productId.isEmpty() && !productId.isBlank()){
            Product product = repository.findById(productId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvée", productId), ErrorCodes.PRODUCT_NOT_FOUND)
            );
            return mapper.fromEntityToProductWithStockMvt(
                    product
            );
        }
        throw new InvalidEntityException("Idenfiant invalide pour un produit", ErrorCodes.PRODUCT_NOT_VALID);
    }

    @Override
    public List<ProductDTO> getProducts(Long categoryId) {
        if (!categoryRepo.existsById(categoryId)){
            throw new EntityNotFoundException(String.format("Catégorie avec ID {%s} non trouvée", ErrorCodes.CATEGORY_NOT_FOUND));
        }

        return mapper.fromEntityToProductDTO(
                repository.findProductsByCategory_Id(categoryId)
        );
    }

    @Override
    public void delete(String productId) {
        if (!productId.isEmpty() && !productId.isBlank()){
            Product product = repository.findById(productId).orElseThrow(
                    ()-> new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvée", productId), ErrorCodes.PROVIDER_NOT_FOUND)
            );
            if (product.getCommandLineClients().isEmpty() &&
            product.getLineStorages().isEmpty() && product.getCommandLineProviders().isEmpty() &&
            product.getSaleLines().isEmpty() && product.getStockMovements().isEmpty()){
                repository.deleteById(productId);
            }else {
                throw new InvalidOperationException("Produit avec ID {%s} est utilisée par d'autres éléments", ErrorCodes.PRODUCT_ALREADY_IN_USE);
            }
        }
        throw new InvalidEntityException("Identifiant invalide pour produit", ErrorCodes.PRODUCT_NOT_VALID);
    }
}
