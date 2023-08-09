package com.example.restservice;

import java.util.LinkedList;

public class StackEntity {

    private long id;

    LinkedList<Float> stackElements = new LinkedList<Float>();

    public StackEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void push(Float newElement) {
        stackElements.add(0, newElement);
    }

    public void applyOp(String op){

        System.out.println("applyOp() op : " + op);

        if (op.equals(RPNCalculatorController.OPERANDS.PLUS.toString())){

            float lastVal = stackElements.removeLast();
            float newVal = lastVal + stackElements.get(stackElements.size() - 1);
            stackElements.set(stackElements.size() - 1, newVal);

        } else if (op.equals(RPNCalculatorController.OPERANDS.MINUS.toString())){

            float lastVal = stackElements.removeLast();

            System.out.println("applyOp() MINUS lastVal = " + lastVal);

            float newVal = lastVal - stackElements.get(stackElements.size() - 1);
            stackElements.set(stackElements.size() - 1, newVal);

            System.out.println("applyOp() MINUS RESULT : " + newVal);

        } else if (op.equals(RPNCalculatorController.OPERANDS.MULT.toString())){

          float lastVal = stackElements.removeLast();

          System.out.println("applyOp() MULT lastVal = " + lastVal);

          float newVal = lastVal * stackElements.get(stackElements.size() - 1);
          stackElements.set(stackElements.size() - 1, newVal);

          System.out.println("applyOp() MULT RESULT : " + newVal);

        } else if (op.equals(RPNCalculatorController.OPERANDS.DIV.toString())){

            float lastVal = stackElements.removeLast();

            System.out.println("applyOp() DIV lastVal = " + lastVal);

            float newVal = lastVal / stackElements.get(stackElements.size() - 1);
            stackElements.set(stackElements.size() - 1, newVal);

            System.out.println("applyOp() DIV RESULT : " + newVal);
        }
    }

    public void clear(){
        stackElements.clear();
    }

    public LinkedList<Float> getAllElement(){
        return stackElements;
    }
}
