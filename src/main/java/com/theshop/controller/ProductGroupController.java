package com.theshop.controller;

import com.theshop.domain.dto.ProductGroupDto;
import com.theshop.exception.NotFoundException;
import com.theshop.mapper.ProductGroupMapper;
import com.theshop.service.ProductGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/theshop/groups")
public class ProductGroupController {

    @Autowired
    private ProductGroupService service;

    @Autowired
    private ProductGroupMapper mapper;

    private final Logger log = LoggerFactory.getLogger(ProductGroupController.class);

    @GetMapping
    public List<ProductGroupDto> getAllGroups(){
        log.debug("REST request to get all groups");
        return mapper.mapToProductGroupDtoList(service.getGroups());
    }

    @GetMapping ("/{id}")
    public ProductGroupDto getGroup(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get group with id: ", id);
        return mapper.mapToProductGroupDto(service.getGroup(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDto createGroup(@RequestBody ProductGroupDto productGroupDto) {
        log.debug("REST request to add new group: ", productGroupDto);
        service.saveGroup(mapper.mapToProductGroup(productGroupDto));
        return productGroupDto;
    }

    @PutMapping(name = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDto updateGroupById(@PathVariable("id") Long id, @RequestBody ProductGroupDto productGroupDto) {
        log.debug("REST request to update group with id: ", id);
        return mapper.mapToProductGroupDto(service.saveGroup(mapper.mapToProductGroup(productGroupDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteGroupById(@PathVariable long id) {
        log.debug("REST request to delete group with id: ", id);
        service.deleteGroup(id);
        //do zrobienia ProductGroupService.deleteGroupAndImputeDefoultGroup()
    }
}
