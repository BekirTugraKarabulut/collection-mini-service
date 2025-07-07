package com.example.repository;

import com.example.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection,Integer> {

    Collection findByCollectionId(Integer collectionId);

}
