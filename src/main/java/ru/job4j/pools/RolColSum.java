package ru.job4j.pools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] summary = new Sums[matrix.length];
        int sumRow = 0;
        int sumCol = 0;
        for (int index = 0; index < matrix.length; index++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (sumCol != 0) {
                        break;
                    }
                    sumRow = sumRow + matrix[i][j];
                }
                sumCol = sumCol + matrix[index][i];
            }
            summary[index] = new Sums(sumRow, sumCol);
            sumRow = 0;
            sumCol = 0;
        }
        return summary;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] summary = new Sums[matrix.length];
        for (int i = 0; i < summary.length; i++) {
            summary[i] = new Sums(getSummaryRow(matrix, i).get(), getSummaryCol(matrix, i).get());
        }
        return summary;
    }

    public static CompletableFuture<Integer> getSummaryRow(int[][] data, int row) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < data.length; i++) {
                sum = sum + data[row][i];
            }
            return sum;
        });
    }

    public static CompletableFuture<Integer> getSummaryCol(int[][] data, int col) {
        return CompletableFuture.supplyAsync(() -> {
           int sum = 0;
            for (int i = 0; i < data.length; i++) {
                sum = sum + data[i][col];
            }
            return sum;
        });
    }
}
