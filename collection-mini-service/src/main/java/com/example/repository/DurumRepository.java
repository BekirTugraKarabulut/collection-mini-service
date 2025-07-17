package com.example.repository;

import com.example.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DurumRepository extends JpaRepository<Collection , Integer> {

    List<Collection> findByPolicyId(Long policyId);

    Collection findByCollectionId(Integer collectionId);

}
