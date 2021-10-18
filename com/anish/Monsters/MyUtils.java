package com.anish.Monsters;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Random;

public class MyUtils {
    public static Monster[][] generateMonsters(World world){
        int row = 16,col = 16;
        int curIdx = 0;
        Random random = new Random();
        int curRVal = 0,curGVal = 0,curBVal = 0;
        Monster[][] monsters = new Monster[row][col];
        while(curIdx < 256){
            curRVal = random.nextInt(256);
            curGVal = random.nextInt(256);
            curBVal = random.nextInt(256);
            monsters[Math.floorDiv(curIdx, col)][curIdx%col] = new Monster(new Color(curRVal,curGVal,curBVal), world);
            curIdx ++;
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

    public static <T extends Creature> void putCreaturesInPlace(T[][] creatures,World world,int row,int col,int gap,int startX,int startY){
        int curX = startX,curY = startY;    
        for(int i = 0; i < row; i ++){
                for(int j = 0; j < col;j++){
                    world.put(creatures[i][j],curX,curY);
                    curY+=gap;
                }
                curY = startY;
                curX += gap;
            }
    }


    
}
