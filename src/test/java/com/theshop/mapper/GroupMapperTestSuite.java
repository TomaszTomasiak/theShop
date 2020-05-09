package com.theshop.mapper;

import com.theshop.domain.ProductGroup;
import com.theshop.domain.dto.ProductGroupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupMapperTestSuite {

    @Autowired
    private ProductGroupMapper groupMapper;

    private ProductGroup createGroup() {
        return ProductGroup.builder()
                .groupId(1L)
                .name("Test group")
                .build();

    }

    private ProductGroupDto createGroupDto() {
        return ProductGroupDto.builder()
                .id(1L)
                .name("Test group")
                .build();

    }

    @Test
    public void testMapToGroup() {
        //Given
        ProductGroupDto groupDto = createGroupDto();


        //When
        ProductGroup group = groupMapper.mapToProductGroup(groupDto);
        long id = group.getGroupId();

        //Then
        assertNotNull(group);
        assertEquals(1L, id);
        assertEquals("Test group", group.getName());
    }

    @Test
    public void testMapToGroupDto() {
        //Given
        ProductGroup group = createGroup();

        //When
        ProductGroupDto groupDto = groupMapper.mapToProductGroupDto(group);

        //Then
        assertNotNull(group);
        assertEquals(Long.valueOf(1), groupDto.getId());
        assertEquals("Test group", groupDto.getName());
    }

    @Test
    public void testMapToGroupDtoList() {
        //Given
        ProductGroup group1 = ProductGroup.builder()
                .groupId(1L)
                .name("Test group")
                .build();

        ProductGroup group2 = ProductGroup.builder()
                .groupId(2L)
                .name("Test group 2")
                .build();

        List<ProductGroup> groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);

        //When
        List<ProductGroupDto> groupDtoList = groupMapper.mapToProductGroupDtoList(groupList);

        //Then
        //Then
        assertNotNull(groupDtoList);
        assertEquals(2, groupDtoList.size());
    }
}
