package com.SudokuGame.SudokuGame.exceptions;

import tools.jackson.databind.exc.InvalidFormatException;

public class InvalidCharacterException extends RuntimeException {
    public InvalidCharacterException(String message){
        super(message);
    }
}
