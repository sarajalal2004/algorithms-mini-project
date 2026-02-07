package com.SudokuGame.SudokuGame.services;

import com.SudokuGame.SudokuGame.models.Sudoku;
import com.SudokuGame.SudokuGame.models.SudokuCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Time;
import java.util.Timer;

@Service
public class SudokuService {
    public ResponseEntity<Resource> solveSudoku(MultipartFile file){
        long startTime = System.nanoTime();
        SudokuCell[][] sudokuBoard = Sudoku.readSudoku(file);
        SudokuSolver.solveSudoku(sudokuBoard);
        Sudoku.printSudokuBoard(sudokuBoard);
        File outputfile = Sudoku.saveSudoku(sudokuBoard, file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf(".") + 1) + "solution.txt", startTime);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + outputfile.getName())
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(outputfile.length())
                .body(new FileSystemResource(outputfile));
    }
}
