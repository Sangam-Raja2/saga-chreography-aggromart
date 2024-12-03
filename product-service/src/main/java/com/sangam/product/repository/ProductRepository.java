package com.sangam.product.repository;

import com.sangam.product.entity.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,Long> {
    List<Product> findByCategory(String category);

    @Aggregation(pipeline = { "{ '$group': { '_id' : '$category' } }" })
    List<String> findDistinctCategories();

//    @Query("{'active':true}")
//    List<Book> findAll();
//
    @Query("{'category' : ?0, 'price': {$gte: ?1, $lte:?2 }}")
    List<Product> findByCategoryAndPrice(String category, Float startRange, Float endRange);

    //find with NamedParameters and between range
    @Query("{'price': {$gte: ?0, $lte:?1 }}")
    List<Product> findProductBetweenPrice( Float startRange, Float endRange);
}
