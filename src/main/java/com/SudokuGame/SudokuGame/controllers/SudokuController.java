package com.SudokuGame.SudokuGame.controllers;

import com.SudokuGame.SudokuGame.services.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping(path = "/api/sudoku")
public class SudokuController {
    private SudokuService sudokuService;

    @Autowired
    private void setSudokuService(SudokuService sudokuService){
        this.sudokuService = sudokuService;
    }

    @PostMapping(path = "/solve", consumes = "multipart/form-data")
    private ResponseEntity<Resource> solveSudoku(@RequestParam("file") MultipartFile input){
        return sudokuService.solveSudoku(input);
    }

}
