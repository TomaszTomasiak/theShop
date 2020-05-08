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
        groupBean.setGroupId(productGroupDto.getId());
        groupBean.setName(productGroupDto.getName());
        return groupBean;
    }

    public ProductGroupDto mapToProductGroup(final ProductGroup productGroup) {
        return new ProductGroupDto(
                productGroup.getGroupId(),
                productGroup.getName()
        );
    }

    public List<ProductGroup> mapToProductGroupList(final List<ProductGroupDto> productGroupDtoList) {
        return productGroupDtoList.stream()
                .map(this::mapToProductGroup)
                .collect(Collectors.toList());
    }

    public List<ProductGroupDto> mapToProductDtoList(final List<ProductGroup> productGroupList) {
        return productGroupList.stream()
                .map(this::mapToProductGroup)
                .collect(Collectors.toList());
    }
}
