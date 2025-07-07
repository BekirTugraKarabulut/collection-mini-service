package com.example.service.impl;

import com.example.dto.DtoCollection;
import com.example.dto.DtoCollectionUI;
import com.example.exception.BaseException;
import com.example.exception.ErrorMessage;
import com.example.exception.MessageType;
import com.example.model.Collection;
import com.example.repository.CollectionRepository;
import com.example.service.CollectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public List<DtoCollection> findAll() {

        List<Collection> collections = collectionRepository.findAll();

        if(collections.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_LİST_BULUNAMADİ , collections.toString()));
        }

        List<DtoCollection> dtoCollections = new ArrayList<>();

        for (Collection collection : collections) {
            DtoCollection dtoCollection = new DtoCollection();
            BeanUtils.copyProperties(collection,dtoCollection);
            dtoCollections.add(dtoCollection);
        }

        return dtoCollections;
    }

    @Override
    public DtoCollection findByCollectionId(Integer collectionId) {

        Collection collection = collectionRepository.findByCollectionId(collectionId);

        if (collection  == null) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_BULUNAMADİ , collectionId.toString()));
        }

        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(collection,dtoCollection);

        return dtoCollection;
    }

    @Override
    public DtoCollection saveCollection(DtoCollectionUI dtoCollectionUI) {

        Collection collection = new Collection();

        collection.setPolicyId(dtoCollectionUI.getPolicyId());
        collection.setTutar(dtoCollectionUI.getTutar());
        collection.setCollectionDate(dtoCollectionUI.getCollectionDate());

        if(collection.getTutar() > 0){
            collection.setOdenmeDurumu(true);
        }

        if(collection.getTutar() == 0){
            collection.setOdenmeDurumu(false);
        }

        if(collection.getTutar() < 0){
            throw new BaseException(new ErrorMessage(MessageType.TUTAR_EKSILI_OLAMAZ , collection.getTutar().toString()));
        }

        Collection dbCollection = collectionRepository.save(collection);
        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(dbCollection,dtoCollection);

        return dtoCollection;
    }

    @Override
    public DtoCollection updateCollection(Integer collectionId , DtoCollectionUI dtoCollectionUI) {

        Collection collection =  collectionRepository.findById(collectionId).get();

        if (collection == null) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_BULUNAMADİ , collectionId.toString()));
        }

        collection.setPolicyId(dtoCollectionUI.getPolicyId());
        collection.setCollectionDate(dtoCollectionUI.getCollectionDate());

        Collection updateCollection = collectionRepository.save(collection);
        DtoCollection dtoCollection = new DtoCollection();
        BeanUtils.copyProperties(updateCollection,dtoCollection);

        return dtoCollection;
    }

    @Override
    public void deleteCollection(Integer collectionId) {

        Collection collection = collectionRepository.findByCollectionId(collectionId);

        if (collection == null) {
            throw new BaseException(new ErrorMessage(MessageType.COLLECTION_BULUNAMADİ , collectionId.toString()));
        }

        collectionRepository.delete(collection);
    }
}
