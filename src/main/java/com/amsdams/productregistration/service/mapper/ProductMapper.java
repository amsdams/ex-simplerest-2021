package com.amsdams.productregistration.service.mapper;

import org.mapstruct.Mapper;

import com.amsdams.productregistration.domain.Product;
import com.amsdams.productregistration.service.dto.ProductDTO;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
}
