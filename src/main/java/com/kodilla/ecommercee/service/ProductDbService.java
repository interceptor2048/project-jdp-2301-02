package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.*;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.mapper.*;
import com.kodilla.ecommercee.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final GroupRepository groupRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(final ProductSaveDto productSaveDto) throws Exception {
        if ((productSaveDto.getName() != null) && (productSaveDto.getDescription() != null)
                && (productSaveDto.getPrice() != null)) {
            if (ifExistByName(productSaveDto.getName()) == 0) {
                Product product = productMapper.mapToProduct(productSaveDto);
                product.setObsolete(false);
                setGroupToProduct(product, productSaveDto.getGroupId());
            } else {
                throw new RecordExistsException();
            }
        } else {
            throw new EmptyFieldException();
        }
    }

    public Product getProduct(long id) throws RecordNotExistsException {
        return productRepository.findById(id).orElseThrow(RecordNotExistsException::new);
    }

    public void updateProduct(ProductUpdateDto productUpdateDto) throws RecordExistsException, EmptyFieldException {
        int ifExist = ifExistByName(productUpdateDto.getName());
        try {
            Product product = productRepository.findById(productUpdateDto.getId()).orElseThrow(RecordNotExistsException::new);
            if ((ifExist == 0) || ((ifExist == 1) && (product.getName().equals(productUpdateDto.getName())))) {
                product.setName(productUpdateDto.getName());
                product.setDescription(productUpdateDto.getDescription());
                product.setPrice(productUpdateDto.getPrice());
                product.setQty((productUpdateDto.getQty()));
                product.setObsolete(productUpdateDto.isObsolete());
                setGroupToProduct(product, productUpdateDto.getGroupId());
            } else {
                throw new RecordExistsException();
            }
        } catch (RecordNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(long id) throws RecordNotExistsException {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RecordNotExistsException();
        }
    }

    private void setGroupToProduct(Product product, String groupId) {
        if (ifLong(groupId)) {
            try {
                Group group = groupRepository.findById(Long.parseLong(groupId)).orElseThrow(RecordNotExistsException::new);
                product.setGroup(group);
            } catch (RecordNotExistsException e) {
            }
        } else {
            product.setGroup(null);
        }
        productRepository.save(product);
    }

    private int ifExistByName(final String name) {
        List<Product> product = productRepository.findByNameIgnoreCase(name);
        return product.size();
    }

    private boolean ifLong(String str) {
        long id;
        if (str == null || str.equals("")) {
            return false;
        } else {
            try {
                id = Long.parseLong(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}