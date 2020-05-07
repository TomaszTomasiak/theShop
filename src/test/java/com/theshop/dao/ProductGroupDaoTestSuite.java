package com.theshop.dao;
import com.theshop.domain.ProductGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductGroupDaoTestSuite {

    @Autowired
    ProductGroupDao productGroupDao;

    @Test
    public void testProductGroupDaoSave() {

        //Given
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName("food");

        //When
        productGroupDao.save(productGroup);
        Long id = productGroup.getGroupId();

        //Then
        assertEquals("food", productGroup.getName());

        //CleanUp
        productGroupDao.deleteById(id);
    }
}
