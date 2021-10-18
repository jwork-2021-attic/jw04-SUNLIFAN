package com.anish.Monsters;
import java.awt.Color;
import java.lang.reflect.Array;

public class MyUtils {
    public static Monster[][] generateMonsters(World world){
        int row = 16,col = 16;
        int curIdx = 0;
        Monster[][] monsters = new Monster[row][col];
        for(int curRVal = 0; curRVal <= 255; curRVal+=30){
            for(int curGVal = 0;curGVal <= 255;curGVal+=30){
                for(int curBVal = 0;curBVal <= 255;curBVal+=30){
                    monsters[curIdx/col][curIdx%col] = new Monster(new Color(curRVal,curGVal,curBVal),world);
                }
            }
        }
        return monsters;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] createGenericArray(int length,Class<T> kind){
        T[] array = (T[])Array.newInstance(kind, length);
        return array;
    }

    public static <T> T[] transformMatrixToArray(T[][] matrix,Class<T> kind){
        int row = matrix.length;
        int col = matrix[0].length;
        int curId = 0;
        int length = row*col;
        T[] array = createGenericArray(length, kind);
        for(int i = 0; i < row; i ++)
            for(int j = 0; j < col;j++){
                array[curId] = matrix[i][j];
                curId++;
            }
        return array;
    }


    
}
