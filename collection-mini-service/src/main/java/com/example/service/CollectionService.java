package com.example.service;

import com.example.dto.DtoCollection;
import com.example.dto.DtoCollectionFiyat;
import com.example.dto.DtoCollectionUI;

import java.util.List;

public interface CollectionService {

    public DtoCollection getCollectionStatusByPoliceId(Long policyId);

    public List<DtoCollection> findAll();

    public DtoCollection findByCollectionId(Integer collectionId);

    public DtoCollection saveCollection(DtoCollectionUI dtoCollectionUI);

    public DtoCollection updateCollection(Integer collectionId , DtoCollectionUI dtoCollectionUI);

    public void deleteCollection(Integer collectionId);

    public List<DtoCollection> odenmeDurumuFalseByPolicyId(Long policyId);

    public List<DtoCollection> odenmeDurumuTrueByPolicyId(Long policyId);

    public DtoCollection durumGuncelle(Integer collectionId , DtoCollectionFiyat dtoCollectionFiyat);

}
