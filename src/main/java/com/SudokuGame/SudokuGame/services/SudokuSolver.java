package com.SudokuGame.SudokuGame.services;

import com.SudokuGame.SudokuGame.models.Sudoku;
import com.SudokuGame.SudokuGame.models.SudokuCell;

import java.util.*;

public class SudokuSolver {
    public static SudokuCell[][] solveSudoku(SudokuCell[][] sudokuBoard){
        ArrayList<Map.Entry<Integer, ArrayList<Integer>>> priority;
        boolean solved = false;
        if((priority = setPriority(sudokuBoard)).isEmpty())
            solved = true;
        while (!solved){
            Map.Entry<Integer, ArrayList<Integer>> boxData = priority.get(0);
            int tries = boxData.getValue().size();
            while (tries > 0){
                int initialRow = boxData.getKey() / 3 * 3;
                int initialCol = boxData.getKey() % 3 * 3;
                for(int row = initialRow; row < initialRow + 3; row ++){
                    for (int col = initialCol; col < initialCol + 3; col++){
                        if(sudokuBoard[row][col].getValue() == 0){
                            ArrayList<Integer> valuesCouldInserted = new ArrayList<>();
                            notInsertedLoop:
                            for (int notInserted : boxData.getValue()){
                                for(int searchRow = 0; searchRow< 9; searchRow++){
                                    if(sudokuBoard[searchRow][col].getValue() == notInserted)
                                        continue notInsertedLoop;
                                }
                                for(int searchCol = 0; searchCol< 9; searchCol++){
                                    if(sudokuBoard[row][searchCol].getValue() == notInserted)
                                        continue notInsertedLoop;
                                }
                                valuesCouldInserted.add(notInserted);
                            }
                            if(valuesCouldInserted.size() == 1){
                                sudokuBoard[row][col].setValue(valuesCouldInserted.get(0));
                                boxData.getValue().remove(Integer.valueOf(valuesCouldInserted.get(0)));
                                tries = boxData.getValue().size();
                            }else {
                                tries --;
                            }
                        }
                    }
                }
            }
            if(boxData.getValue().size() == 0){
                priority.remove(0);
            }else {
                priority.remove(0);
                priority.add(boxData);
            }
            if(priority.isEmpty())
                solved = true;
        }
        return sudokuBoard;
    }

    private static ArrayList<Map.Entry<Integer, ArrayList<Integer>>> setPriority(SudokuCell[][] sudokuBoard){
        ArrayList<Map.Entry<Integer, ArrayList<Integer>>> priority = new ArrayList<>();
        for(int box=0; box<9; box++){
            int initialRow = box / 3 * 3;
            int initialCol = box % 3 * 3;
            ArrayList<Integer> ListOfEmpty = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for(int row = initialRow; row < initialRow + 3; row ++){
                for (int col = initialCol; col < initialCol + 3; col++){
                    if(sudokuBoard[row][col].getValue() != 0){
                        // force remove by object
                        ListOfEmpty.remove(Integer.valueOf(sudokuBoard[row][col].getValue()));
                    }
                }
            }
            if(ListOfEmpty.size() != 0){
                priority.add(new AbstractMap.SimpleEntry(box, ListOfEmpty));
            }
        }
        priority.sort(Comparator.comparing(e -> e.getValue().size()));
        return priority;
    }
}
