package com.example.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    COLLECTION_LİST_BULUNAMADİ("999","Collectionlar Bulunamadı!"),
    COLLECTION_BULUNAMADİ("555","Collection Bulunamadı!"),
    TUTAR_EKSILI_OLAMAZ("300","Tutar Eksili Olamaz!");

    private String message;
    private String code;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
