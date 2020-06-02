package com.theshop.mapper;

import com.theshop.domain.ProductGroup;
import com.theshop.domain.dto.ProductGroupDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupMapper {

    public ProductGroup mapToProductGroup(final ProductGroupDto productGroupDto) {
        ProductGroup groupBean = new ProductGroup();
        groupBean.setId(productGroupDto.getId());
        groupBean.setName(productGroupDto.getName());
        return groupBean;
    }

    public ProductGroupDto mapToProductGroupDto(final ProductGroup productGroup) {
        return new ProductGroupDto(
                productGroup.getId(),
                productGroup.getName()
        );
    }

    public List<ProductGroup> mapToProductGroupList(final List<ProductGroupDto> productGroupDtoList) {
        return productGroupDtoList.stream()
                .map(this::mapToProductGroup)
                .collect(Collectors.toList());
    }

    public List<ProductGroupDto> mapToProductGroupDtoList(final List<ProductGroup> productGroupList) {
        return productGroupList.stream()
                .map(this::mapToProductGroupDto)
                .collect(Collectors.toList());
    }
}
