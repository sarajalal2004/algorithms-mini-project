package com.SudokuGame.SudokuGame.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SudokuCell {
    private int value;
    private int column;
    private int row;
}
