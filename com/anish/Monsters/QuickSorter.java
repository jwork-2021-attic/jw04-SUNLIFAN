package com.anish.Monsters;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {
    private Class<T> kind;
    private T[] a;
    private T[][] matrixFormOfA;

    public QuickSorter(Class<T> kind){
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
        quickSort(0, a.length-1);
    }

    private void quickSort(int l,int r){
        if(l >= r)return;
        T point = a[l+r>>1];
        int i = l-1,j = r+1;
        while(i < j){
            do i++;while(a[i].compareTo(point) < 0);
            do j--;while(a[j].compareTo(point) > 0);
            if(i < j){
                swap(i, j);
            }
        }
        quickSort(l, j);
        quickSort(j+1, r);
    }

    @Override
    public String getPlan() {
        return this.plan;
    }    
}
