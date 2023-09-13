package com.kabelo.springangularshop.config;

import com.kabelo.springangularshop.Entity.Product;
import com.kabelo.springangularshop.Entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager= theEntityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

                HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};

        // disable HTTP methods for Product: PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // disable HTTP methods for ProductCategory: PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // Expose entity ID's

        //Get a list of all the entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array list of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // get entity types for entities
        for (EntityType tempEntityType: entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);


    }

    //    @Override
//    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};
//
//        // disable HTTP methods for Product: PUT, POST and DELETE
//        config.getExposureConfiguration()
//                .forDomainType(Product.class)
//                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
//                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
//
//        // disable HTTP methods for ProductCategory: PUT, POST and DELETE
//        config.getExposureConfiguration()
//                .forDomainType(ProductCategory.class)
//                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
//                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
//    }
}
