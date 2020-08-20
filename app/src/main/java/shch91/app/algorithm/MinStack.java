package shch91.app.algorithm;

import java.util.Stack;

class MinStack {

    private Stack<Integer> min;
    private Stack<Integer> stack;
    /** initialize your data structure here. */
    public MinStack() {
          min=new Stack<>();
          stack=new Stack<>();
    }
    
    public void push(int x) {
         stack.push(x);
         if(min.empty()){
             min.push(x);
         }else{
             if(x<=min.peek()){
                   min.push(x);
             }
         }
    }
    
    public void pop() {
            int top=stack.pop();
            if(top==min.peek()){
                min.pop();
            }
    }
    
    public int top() {
         return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}
