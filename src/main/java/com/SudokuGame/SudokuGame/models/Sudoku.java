package com.SudokuGame.SudokuGame.models;

import com.SudokuGame.SudokuGame.exceptions.InvalidCharacterException;
import com.SudokuGame.SudokuGame.exceptions.SudokuFileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sudoku {
    private String path;
    private Long[] gridPointer = new Long[9];
    private List<Long> priority;

    public  static SudokuCell[][] readSudoku(MultipartFile input){
        int rowCount = 0;
        SudokuCell[][] sudokuBoard = new SudokuCell[9][9];
        String line;
        if(input.isEmpty())
            throw new SudokuFileNotFoundException("The sudoku file is not found");
        try (InputStream inputStream = input.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            SudokuCell newCell = null;
            while((line = reader.readLine()) != null){
                if(rowCount % 4 != 0){
                    System.out.println(line);
                    for(int i=0; i<9; i++){
                        int cellRow = rowCount - 1 - (rowCount / 4);
                        char value = line.charAt(2 * (i+ i / 3));
                        if(value >= '0' && value <= '9')
                            newCell = new SudokuCell(Integer.parseInt(String.valueOf(value)),i,  cellRow);
                        else
                            throw new InvalidCharacterException("Sudoku should include numbers from 1 to 9 in boxes, " + value + " is invalid char");
                        sudokuBoard[cellRow][i] = newCell;
                    }
                }
                rowCount++;
            }
            return sudokuBoard;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File saveSudoku(SudokuCell[][] sudokuBoard, String fileName, long startTime){
        File file = new File(fileName);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("---------------------\n");
            for(int row = 0; row < 9; row++){
                String line = "";
                for(int col=0; col < 9; col++){
                    line += sudokuBoard[row][col].getValue() + " ";
                    if(((col + 1) % 3 == 0) && col != 8)
                        line += "| ";
                }
                writer.write(line + "\n");
                if((row + 1) % 3 == 0)
                    writer.write("---------------------\n");
            }
            writer.write("\n\nSolved in: " + ((System.nanoTime() - startTime) / 1_000_000) + " ms");
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printSudokuBoard(SudokuCell[][] sudokuBoard){
        System.out.println("---------------------");
        for(int row = 0; row < 9; row++){
            for(int col=0; col < 9; col++){
                System.out.print(sudokuBoard[row][col].getValue() + " ");
                if(((col + 1) % 3 == 0) && col != 8)
                    System.out.print("| ");
            }
            System.out.println("");
            if((row + 1) % 3 == 0)
                System.out.println("---------------------");
        }
    }
}
