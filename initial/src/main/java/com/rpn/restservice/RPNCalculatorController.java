package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RPNCalculatorController {

	private static final String template = "Welcome %s!";
	private final AtomicLong counter = new AtomicLong();

    private final HashMap allStacks = new HashMap<Long, StackEntity>();

    List operandList = new LinkedList<String>();

    public enum OPERANDS {
        PLUS, MINUS, MULT, DIV
    };

    RPNCalculatorController(){

        operandList.add(OPERANDS.PLUS.toString());
        operandList.add(OPERANDS.MINUS.toString());
        operandList.add(OPERANDS.MULT.toString());
        operandList.add(OPERANDS.DIV.toString());
    }

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

    @PostMapping("rpn/new-stack")
    public StackEntity newStack() {

        long stackId = counter.incrementAndGet();

        StackEntity newStack = new StackEntity(stackId);

        allStacks.put(newStack.getId(), newStack);

        return newStack;
    }

    @GetMapping("rpn/new_stack_as_get")
    public StackEntity newStackAsGet() {

        long stackId = counter.incrementAndGet();

        StackEntity newStack = new StackEntity(stackId);

        allStacks.put(newStack.getId(), newStack);

        return newStack;
    }

    @GetMapping("rpn/list_stacks")
    public HashMap<Long, StackEntity> listStacks() {
        //return new Stack(counter.incrementAndGet());
        return allStacks;
    }

    @GetMapping("rpn/op")
    public List listOps() {
        return operandList;
    }

    @GetMapping("rpn/push_value")
    public StackEntity pushNewValueToStackAsGet(@RequestParam(value = "stack_id", defaultValue = "0") long stack_id,
        @RequestParam(value = "val", defaultValue = "0") float val) {

        System.out.println("pushNewValueToStackAsGet() val = " + val + " pushed to stack_id = " + stack_id);

        StackEntity theStack = (StackEntity) allStacks.get(stack_id);

        if (theStack == null){
            System.out.println("pushNewValueToStackAsGet() WARNING : Stack does not exist => Creating it ...");
            theStack = new StackEntity(stack_id);
            allStacks.put(stack_id, theStack);
        }

        theStack.push(val);

        return theStack;
    }

    @GetMapping("rpn/apply_op")
    public StackEntity applyOperandAsGet(@RequestParam(value = "stack_id", defaultValue = "0") long stack_id,
        @RequestParam(value = "op", defaultValue = "PLUS") String op) {

        System.out.println("applyOperandAsGet() apply = " + op + " to stack_id = " + stack_id);

        StackEntity theStack = (StackEntity) allStacks.get(stack_id);

        if (theStack == null){
            System.out.println("applyOperandAsGet() WARNING : Stack not found");
            return null;
        }

        theStack.applyOp(op);

        return theStack;
    }

    @GetMapping("rpn/delete_stack")
    public StackEntity deleteStackAsGet(@RequestParam(value = "stack_id") long stack_id) {

        System.out.println("deleteStackAsGet() stack_id = " + stack_id);

        StackEntity theStack = (StackEntity) allStacks.remove(stack_id);

        if (theStack == null){
            System.out.println("deleteStackAsGet() WARNING : Stack not found. stack_id = " + stack_id);
            return null;
        }

        return theStack;
    }
}
