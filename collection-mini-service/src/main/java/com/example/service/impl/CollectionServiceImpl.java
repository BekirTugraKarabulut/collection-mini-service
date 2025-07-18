package com.example.service.impl;

import com.example.dto.DtoCollection;
import com.example.dto.DtoCollectionFiyat;
import com.example.dto.DtoCollectionUI;
import com.example.exception.BaseException;
import com.example.exception.ErrorMessage;
import com.example.exception.MessageType;
import com.example.model.Collection;
import com.example.repository.CollectionRepository;
import com.example.repository.DurumRepository;
import com.example.service.CollectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private DurumRepository durumRepository;

    @Override
    public DtoCollection getCollectionStatusByPoliceId(Long policyId) {

        Collection collection = collectionRepository.findByPolicyId(policyId);

        if (collection == null) {
            return null;
        }

        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(collection, dtoCollection);

        return dtoCollection;
    }

    @Override
    public List<DtoCollection> findAll() {

        List<Collection> collections = collectionRepository.findAll();

        if (collections.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_LİST_BULUNAMADİ, collections.toString()));
        }

        List<DtoCollection> dtoCollections = new ArrayList<>();

        for (Collection collection : collections) {
            DtoCollection dtoCollection = new DtoCollection();
            BeanUtils.copyProperties(collection, dtoCollection);
            dtoCollections.add(dtoCollection);
        }

        return dtoCollections;
    }

    @Override
    public DtoCollection findByCollectionId(Integer collectionId) {

        Collection collection = collectionRepository.findByCollectionId(collectionId);

        if (collection == null) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_BULUNAMADİ, collectionId.toString()));
        }

        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(collection, dtoCollection);

        return dtoCollection;
    }

    @Override
    public DtoCollection saveCollection(DtoCollectionUI dtoCollectionUI) {

        Collection collection = new Collection();

        collection.setPolicyId(dtoCollectionUI.getPolicyId());
        collection.setTutar(dtoCollectionUI.getTutar());
        collection.setCollectionDate(dtoCollectionUI.getCollectionDate());
        collection.setCollectionDate(new Date());

        if (collection.getTutar() > 0) {
            collection.setOdenmeDurumu(false);
        }

        if (collection.getTutar() == 0) {
            collection.setOdenmeDurumu(true);
        }

        if (collection.getTutar() < 0) {
            throw new BaseException(new ErrorMessage(MessageType.TUTAR_EKSILI_OLAMAZ, collection.getTutar().toString()));
        }

        Collection dbCollection = collectionRepository.save(collection);
        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(dbCollection, dtoCollection);

        return dtoCollection;
    }

    @Override
    public DtoCollection updateCollection(Integer collectionId, DtoCollectionUI dtoCollectionUI) {

        Collection collection = collectionRepository.findById(collectionId).get();

        if (collection == null) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_BULUNAMADİ, collectionId.toString()));
        }

        Collection updateCollection = collectionRepository.save(collection);
        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(updateCollection, dtoCollection);

        return dtoCollection;
    }

    @Override
    public void deleteCollection(Integer collectionId) {

        Collection collection = collectionRepository.findByCollectionId(collectionId);

        if (collection == null) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_BULUNAMADİ, collectionId.toString()));
        }

        collectionRepository.delete(collection);
    }

    @Override
    public List<DtoCollection> odenmeDurumuFalseByPolicyId(Long policyId) {

        List<Collection> collections = durumRepository.findByPolicyId(policyId);
        List<DtoCollection> dtoCollections = new ArrayList<>();

        if (collections.isEmpty()) {
            return null;
        }

        for (Collection collection : collections) {
            if(collection.isOdenmeDurumu() == false) {
            DtoCollection dtoCollection = new DtoCollection();
            BeanUtils.copyProperties(collection, dtoCollection);
            dtoCollections.add(dtoCollection);
            }
        }

        return dtoCollections;
    }

    @Override
    public List<DtoCollection> odenmeDurumuTrueByPolicyId(Long policyId) {

        List<Collection> collections = durumRepository.findByPolicyId(policyId);
        List<DtoCollection> dtoCollections = new ArrayList<>();

        if (collections.isEmpty()) {
            return null;
        }

        for (Collection collection : collections) {
            if(collection.isOdenmeDurumu() == true) {
                DtoCollection dtoCollection = new DtoCollection();
                BeanUtils.copyProperties(collection, dtoCollection);
                dtoCollections.add(dtoCollection);
            }
        }

        return dtoCollections;
    }

    @Override
    public DtoCollection durumGuncelle(Integer collectionId , DtoCollectionFiyat dtoCollectionFiyat) {

        Collection collection = collectionRepository.findByCollectionId(collectionId);

        if (collection == null) {
            return  null;
        }

        Collection updateCollection = new  Collection();
        updateCollection.setCollectionId(collection.getCollectionId());
        updateCollection.setCollectionDate(collection.getCollectionDate());
        updateCollection.setPolicyId(collection.getPolicyId());
        updateCollection.setTutar(collection.getTutar() - dtoCollectionFiyat.getTutar());

        if(updateCollection.getTutar() == 0) {
            updateCollection.setOdenmeDurumu(true);
        }else{
            updateCollection.setOdenmeDurumu(false);
        }

        Collection dbCollection = collectionRepository.save(updateCollection);
        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(dbCollection, dtoCollection);

        return dtoCollection;
    }

    public boolean isValidExpired(Date expired){
        return new Date().before(expired);
    }

    @Override
    public List<DtoCollection> getCollectionisValidAndFalse() {

        List<Collection> collectionList = collectionRepository.findAll();
        List<DtoCollection> dtoCollections = new ArrayList<>();

        for (Collection collection : collectionList) {
            if(!isValidExpired(collection.getCollectionDate()) && !collection.isOdenmeDurumu()) {
                DtoCollection dtoCollection = new DtoCollection();
                BeanUtils.copyProperties(collection, dtoCollection);
                dtoCollections.add(dtoCollection);
            }
        }
        return dtoCollections;
    }
}
