package com.example.controller.impl;

import com.example.controller.CollectionController;
import com.example.dto.DtoCollection;
import com.example.dto.DtoCollectionUI;
import com.example.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api")
public class CollectionControllerImpl implements CollectionController {

    @Autowired
    private CollectionService collectionService;

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

}
