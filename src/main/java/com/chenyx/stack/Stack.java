package com.chenyx.stack;

import java.util.ArrayList;
import java.util.List;

/***
 * @desc 定义一个栈，用于实现pv操作
 * @author chenyx
 * @date 2020-05-10
 *
 * */
public class Stack<T> {

    private List<T> list;

    private int size ;
    public Stack() {
        this.list = new ArrayList<>();
    }

    public Stack(int size) {
        this.size = size;
        this.list = new ArrayList<>(size);
    }

    /**
     * @desc 出栈
     * @author chenyx
     * @date 2020-05-10
     * */
    public synchronized T pop() {
        T t = null;
        if (this.size <= 0) {
            return  null;
        }
        t = this.list.get(this.size - 1);
        this.list.remove(this.size - 1);
        this.size = this.list.size();
        return t;
    }

    /**
     * @desc 入栈
     * @author chenyx
     * @date 2020-05-10
     * */
    public synchronized void push(T t) {
        try {
            this.list.add(t);
            this.size = this.list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

