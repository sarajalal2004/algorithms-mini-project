## Sudoku Solver
- Solves complex sudokus in few milliseconds.
- Receive file request and return response.

## API user guide

![POST](https://img.shields.io/badge/POST-e2daeb?style=flat-square)
`http://localhost:8080/api/sudoku/solve`

Attach text file in the body, with key `file`
The text file must be in format:
```
---------------------
0 0 0 | 0 0 0 | 0 0 0
0 0 0 | 0 0 0 | 0 0 0
0 0 0 | 0 0 0 | 0 0 0
---------------------
0 0 0 | 0 0 0 | 0 0 0
0 0 0 | 0 0 0 | 0 0 0
0 0 0 | 0 0 0 | 0 0 0
---------------------
0 0 0 | 0 0 0 | 0 0 0
0 0 0 | 0 0 0 | 0 0 0
0 0 0 | 0 0 0 | 0 0 0
---------------------

* 0 to indecate empty cells
```

##### The program returns a text file included the result.
![Postman screenshot for result image](https://i.imgur.com/eT4pDns.png)




