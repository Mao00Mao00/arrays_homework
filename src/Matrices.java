import java.util.Arrays;

import static java.lang.Math.sqrt;

public class Matrices {
    static <T> T todo() {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    public static class Vector {
        private final int[] items;

        public Vector(int n) {
            this.items = new int[n];
        }

        public Vector(int[] items) {
            this.items = items;
        }

        public Vector add(Vector other) {//Сложение
            if (this.items.length != other.items.length) {
                System.out.println("У векторов разная длина");
            }
            int[] result = new int[this.items.length];
            for (int i = 0; i < this.items.length; i++) {
                result[i] = this.items[i] + other.items[i];
            }
            return new Vector(result);
        }

        public Vector subtract(Vector other) { //Вычитание
            if (this.items.length != other.items.length) {
                System.out.println("У векторов разная длина");
            }
            int[] result = new int[this.items.length];
            for (int i = 0; i < this.items.length; i++) {
                result[i] = this.items[i] - other.items[i];
            }
            return new Vector(result);
        }

        public int dotProduct(Vector other) {//Вычисляет скалярное произведение данного вектора с другим вектором.
            if (this.items.length != other.items.length) {
                System.out.println("У векторов разная длина");
            }
            int composition = 0;
            for (int i = 0; i < this.items.length; i++) {
                composition += this.items[i] * other.items[i];
            }
            return composition;
        }
//s = a_x * b_x + a_y * b_y + a_z * b_z
        public Vector scalarMultiply(int scalar) {//Умножает данный вектор на скаляр.
            int[] result = new int[this.items.length];
            for (int i = 0; i < this.items.length; i++) {
                result[i] = this.items[i] * scalar;
            }
            return new Vector(result);
        }

        public double length() { //Вычисляет длину (норму) данного вектора.
            double result = 0;
            double sum = 0;
            for (int i = 0; i< this.items.length;i++){
                sum += this.items[i]*this.items[i];
            }
            result = sqrt(sum);
            return result;
        }

        @Override
        public String toString() {
            return Arrays.toString(items);
        }
    }

    public static class Matrix {
        private final int nRows;
        private final int nCols;
        private final int[][] rows;


        public Matrix(int nRows, int nCols) {//Создает матрицу (nRows x nCols)
            this.nRows = nRows;
            this.nCols = nCols;
            this.rows = new int[nRows][nCols];
        }

        @Override
        public String toString() {//Выводит матрицу в консоль построчно с правым выравниванием чисел по столбцам.
            int[] colWidths = new int[nCols];
            for (int col = 0; col < nCols; col++) {
                int maxWidth = 0;
                for (int row = 0; row < nRows; row++) {
                    int width = Integer.toString(rows[row][col]).length();
                    maxWidth = Math.max(maxWidth, width);
                }
                colWidths[col] = maxWidth;
            }

            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < nRows; row++) {
                for (int col = 0; col < nCols; col++) {
                    String cell = String.format("%" + colWidths[col] + "d", rows[row][col]);
                    sb.append(cell);
                    if (col < nCols - 1) {
                        sb.append(" ");
                    }
                }
                if (row < nRows - 1) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        }
        public void setElement(int row, int col, int value) {
            rows[row][col] = value;
        }

        public int getElement(int row, int col) {
            return rows[row][col];
        }

        public Matrix add(Matrix other) { //Складывает текущую матрицу с другой матрицей.
            if (this.nCols != other.nCols && this.nRows!= other.nRows){
                System.out.println("Матрицы не соразмерны");
            }
            Matrix result = new Matrix(2, 2);
            for (int i = 0; i < other.nCols; i++){//столбец  
                for (int l = 0; l < other.nRows; l++) {//строка
                    result.setElement(i, l, this.getElement(i, l) + other.getElement(i, l));
                }
            }
            return result;
        }

        public Matrix subtract(Matrix other) {//Вычитает другую матрицу из текущей матрицы.
            if (this.nCols != other.nCols && this.nRows!= other.nRows){
                System.out.println("Матрицы не соразмерны");
            }
            Matrix result = new Matrix(2, 2);
            for (int i = 0; i < other.nCols; i++){//столбец
                for (int l = 0; l < other.nRows; l++) {//строка
                    result.setElement(i, l, this.getElement(i, l) - other.getElement(i, l));
                }
            }
            return result;
        }

        public Matrix multiply(Matrix other) {//Умножает текущую матрицу на другую матрицу.
            if (this.nCols != other.nCols && this.nRows!= other.nRows){
                System.out.println("Матрицы не соразмерны");
            }
            Matrix result = new Matrix(2,2);
            for (int i = 0; i < this.nCols; i++){//столбец
                for (int l = 0; l < this.nRows; l++) {//строка
                    int variable = 0;
                    for(int k =0; k<this.nRows;k++){
                        variable += this.getElement(i,k) * other.getElement(k,l);
                    }
                    result.setElement(i,l,variable);
                }
            }
            return result;
        }

        public Matrix scalarMultiply(int scalar) {//Умножает текущую матрицу на скаляр.
            Matrix result = new Matrix(2,2);
            for (int i = 0; i < this.nCols; i++){//столбец
                for (int l = 0; l < this.nRows; l++) {//строка
                    result.setElement(i, l, this.getElement(i, l) * scalar);
                }
            }
            return result;
        }

        public Matrix transpose() {//Транспонирует текущую матрицу.
            Matrix result = new Matrix(2,2);
            for (int i = 0; i < this.nCols; i++){//столбец
                for (int l = 0; l < this.nRows; l++) {//строка
                    result.setElement(l, i, this.getElement(i, l));
                }
            }
            return result;
        }

        public int determinant() {//Вычисляет определитель текущей матрицы.
            return determinant(this.rows);
        }

        private static int determinant(int[][] matrix) {
            if (matrix.length == 1) {
                return matrix[0][0];
            }

            if (matrix.length == 2) {
                return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            }

            int result = 0;
            for (int i = 0; i < matrix.length; i++) {
                int[][] smallerMatrix = new int[matrix.length - 1][matrix.length - 1];
                for (int row = 1; row < matrix.length; row++) {
                    for (int col = 0; col < matrix.length; col++) {
                        if (col < i) {
                            smallerMatrix[row - 1][col] = matrix[row][col];
                        } else if (col > i) {
                            smallerMatrix[row - 1][col - 1] = matrix[row][col];
                        }
                    }
                }
                int subDeterminant = determinant(smallerMatrix);
                int sign = (i % 2 == 0) ? 1 : -1;
                result += sign * matrix[0][i] * subDeterminant;
            }
            return result;
        }


    }

    public static void main(String[] args) {

        // Пример создания нулевого вектора длины 3
        Vector zeroVector = new Vector(3);
        System.out.println("Нулевой вектор длины 3: " + zeroVector);

        // Пример создания вектора с заданными элементами
        int[] items = {1, 2, 3};
        Vector vectorA = new Vector(items);
        System.out.println("Вектор A: " + vectorA);

        // Пример сложения двух векторов
        int[] itemsB = {4, 5, 6};
        Vector vectorB = new Vector(itemsB);
        System.out.println("Вектор B: " + vectorB);
        Vector sumVector = vectorA.add(vectorB);
        System.out.println("Сумма векторов A и B: " + sumVector);

        // Пример вычитания двух векторов
        Vector diffVector = vectorA.subtract(vectorB);
        System.out.println("Разность векторов A и B: " + diffVector);

        // Пример скалярного произведения двух векторов
        int dotProduct = vectorA.dotProduct(vectorB);
        System.out.println("Скалярное произведение векторов A и B: " + dotProduct);

        // Пример умножения вектора на скаляр
        int scalar = 2;
        Vector scalarProduct = vectorA.scalarMultiply(scalar);
        System.out.println("Умножение вектора A на скаляр 2: " + scalarProduct);

        // Пример вычисления длины (нормы) вектора
        double length = vectorA.length();
        System.out.println("Длина (норма) вектора A: " + length);

        // Создание матрицы A 2x2
        Matrix A = new Matrix(2, 2);
        A.rows[0] = new int[]{1, 2};
        A.rows[1] = new int[]{3, 4};
        System.out.println("Matrix A:");
        System.out.println(A.toString());

        // Создание матрицы B 2x2
        Matrix B = new Matrix(2, 2);
        B.rows[0] = new int[]{2, 0};
        B.rows[1] = new int[]{1, 2};
        System.out.println("Matrix B:");
        System.out.println(B.toString());

        // Сложение матриц A и B
        Matrix C = A.add(B);
        System.out.println("Matrix A + B:");
        System.out.println(C.toString());

        // Вычитание матриц B из A
        Matrix D = A.subtract(B);
        System.out.println("Matrix A - B:");
        System.out.println(D.toString());

        // Умножение матриц A и B
        Matrix E = A.multiply(B);
        System.out.println("Matrix A * B:");
        System.out.println(E.toString());

        // Умножение матрицы A на скаляр
        int scalar3 = 3;
        Matrix F = A.scalarMultiply(scalar3);
        System.out.println("Matrix A * " + scalar3 + ":");
        System.out.println(F.toString());

        // Транспонирование матрицы A
        Matrix G = A.transpose();
        System.out.println("Matrix A Transpose:");
        System.out.println(G.toString());

        // Создание квадратной матрицы 3x3
        Matrix H = new Matrix(3, 3);
        H.rows[0] = new int[]{4, 3, 2};
        H.rows[1] = new int[]{1, 3, 1};
        H.rows[2] = new int[]{2, 1, 4};
        System.out.println("Matrix H:");
        System.out.println(H.toString());

        // Вычисление определителя матрицы H
        int det = H.determinant();
        System.out.println("Determinant of Matrix H:");
        System.out.println(det);
    }
}