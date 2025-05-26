package org.skypro.skyshop.fix.controller;

import org.skypro.skyshop.fix.exceptions.NoSuchProductException;
import org.skypro.skyshop.fix.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProductException e) {
        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND", // Код ошибки
                "Продукт с ID " + e.getProductId() + " не найден" // Сообщение
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}