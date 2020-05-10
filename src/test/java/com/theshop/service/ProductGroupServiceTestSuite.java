package com.theshop.service;


import com.theshop.domain.ProductGroup;
import com.theshop.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductGroupServiceTestSuite {

    @Autowired
    private ProductGroupService groupService;

    @Test
    public void testSaveOrGetGroup() throws NotFoundException {
        //Given
        long prevNumOfRecords =  groupService.getGroups().size();
        List<ProductGroup> groups = new ArrayList<>();
        ProductGroup tempGroup = null;
        ProductGroup group = ProductGroup.builder()
                .name("the group")
                .build();

        //When
        groupService.saveGroup(group);
        tempGroup = groupService.getGroup(group.getId()).orElseThrow(NotFoundException::new);
        groups = groupService.getGroups();
        long afterNumOfRecords =  groupService.getGroups().size();
        //Then
        assertEquals(group.getId(), tempGroup.getId());
        assertEquals(group.getName(), tempGroup.getName());
        assertEquals(1, afterNumOfRecords - prevNumOfRecords);
        //CleanUp
        groupService.deleteGroup(group.getId());
    }

    @Test
    public void testUpdateGroup() throws NotFoundException {
        //Given
        ProductGroup tempGroup = null;
        ProductGroup group = new ProductGroup();
        group.setName("agd");
        groupService.saveGroup(group);

        ProductGroup updatedGroup = new ProductGroup();
        updatedGroup.setId(group.getId());
        updatedGroup.setName("Updated Group");
        groupService.saveGroup(updatedGroup);
        //When
        tempGroup = groupService.getGroup(group.getId()).orElseThrow(NotFoundException::new);
        //Then
        assertEquals("Updated Group", tempGroup.getName());

        //CleanUp
        groupService.deleteGroup(group.getId());
    }

}