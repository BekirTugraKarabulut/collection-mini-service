package com.example.controller.impl;

import com.example.controller.CollectionController;
import com.example.dto.DtoCollection;
import com.example.dto.DtoCollectionFiyat;
import com.example.dto.DtoCollectionUI;
import com.example.service.CollectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api")
@Tag(name = "Collection Controller" , description = "Collection APİ İşlemleri")
public class CollectionControllerImpl implements CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Override
    @GetMapping(path = "/policy/{policyId}")
    public DtoCollection getCollectionStatusByPoliceId(@PathVariable(name = "policyId" , required = true) Long policyId) {
        return collectionService.getCollectionStatusByPoliceId(policyId);
    }

    @Override
    @GetMapping(path = "/all-collection")
    public List<DtoCollection> findAll() {
        return collectionService.findAll();
    }

    @Override
    @GetMapping(path = "/collection/{collectionId}")
    public DtoCollection findByCollectionId(@PathVariable(name = "collectionId" , required = true) Integer collectionId) {
        return collectionService.findByCollectionId(collectionId);
    }

    @Override
    @PostMapping(path = "/collection-ekle")
    public DtoCollection saveCollection(@RequestBody DtoCollectionUI dtoCollectionUI) {
        return collectionService.saveCollection(dtoCollectionUI);
    }

    @Override
    @PutMapping(path = "/update-collection/{collectionId}")
    public DtoCollection updateCollection(@PathVariable(name = "collectionId" , required = true) Integer collectionId,@RequestBody DtoCollectionUI dtoCollectionUI) {
        return collectionService.updateCollection(collectionId, dtoCollectionUI);
    }

    @Override
    @DeleteMapping(path = "/delete/{collectionId}")
    public void deleteCollection(@PathVariable(name = "collectionId" , required = true) Integer collectionId) {
        collectionService.deleteCollection(collectionId);
    }

    @Override
    @GetMapping(path = "/odenmemis/{policyId}")
    public List<DtoCollection> odenmeDurumuFalseByPolicyId(@PathVariable(name = "policyId" , required = true) Long policyId) {
        return collectionService.odenmeDurumuFalseByPolicyId(policyId);
    }

    @Override
    @GetMapping(path = "/odenmis/{policyId}")
    public List<DtoCollection> odenmeDurumuTrueByPolicyId(@PathVariable(name = "policyId" , required = true) Long policyId) {
        return collectionService.odenmeDurumuTrueByPolicyId(policyId);
    }

    @Override
    @PutMapping(path = "/collection-guncelle/{collectionId}")
    public DtoCollection durumGuncelle(@PathVariable(name = "collectionId" , required = true) Integer collectionId,@RequestBody DtoCollectionFiyat dtoCollectionFiyat) {
        return collectionService.durumGuncelle(collectionId, dtoCollectionFiyat);
    }

}
