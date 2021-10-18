package com.anish.Monsters;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {
    private Class<T> kind;
    private T[] a;
    private T[][] matrixFormOfA;

    public BubbleSorter(Class<T> kind){
        this.kind = kind;
    }
    @Override
    public void load(T[][] a) {
        this.matrixFormOfA = a;
        this.a = MyUtils.transformMatrixToArray(matrixFormOfA,kind);
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i].compareTo(a[i + 1]) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}