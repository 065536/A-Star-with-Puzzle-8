import java.util.*;

public class main{
    public static void main(String[] args){
        Queue<Puzzle> q = new PriorityQueue<Puzzle>();
        List<int[]> l_q = new ArrayList<int[]>();
        Scanner input = new Scanner(System.in);
        int[] beginNum = new int[9];
        int[] end = new int[9];
        Puzzle root = new Puzzle();

        for(int i = 0; i < 9; i++){
            int data = input.nextInt();
            if (data == 0){
                root.zeroLoc = i;
            }
            beginNum[i] = data;
            end[i] = (i+1)%10;
        }

        
        root.arr = beginNum;
        root.step = 0;
        l_q.add(root.arr);
        int count = 0;
        while(root.MD() > 0){
            boolean j = false;
            if(root.canUp()){
                Puzzle temp = root.move(0);
                for(int i = 0; i < l_q.size(); i++){
                    if(Arrays.equals(l_q.get(i), temp.arr)){
                        j = true;
                        break;
                    }
                }
                if(!j){
                    q.add(temp);
                }
                j = false;
            }
            if(root.canDown()){
                Puzzle temp = root.move(1);
                for(int i = 0; i < l_q.size(); i++){
                    if(Arrays.equals(l_q.get(i), temp.arr)){
                        j = true;
                        break;
                    }
                }
                if(!j){
                    q.add(temp);
                }
                j = false;
            }
            if(root.canLeft()){
                Puzzle temp = root.move(2);
                for(int i = 0; i < l_q.size(); i++){
                    if(Arrays.equals(l_q.get(i), temp.arr)){
                        j = true;
                        break;
                    }
                }
                if(!j){
                    q.add(temp);
                }
                j = false;
            }
            if(root.canRight()){
                Puzzle temp = root.move(3);
                for(int i = 0; i < l_q.size(); i++){
                    if(Arrays.equals(l_q.get(i), temp.arr)){
                        j = true;
                        break;
                    }
                }
                if(!j){
                    q.add(temp);
                }
                j = false;
            }
            count ++;
            root = q.poll();
            l_q.add(root.arr);
        }
        System.out.println(root.step);
        Stack<Puzzle> stack = new Stack();
        stack.push(root);
        while(root.parent != null){
            stack.push(root.parent);
            root = root.parent;
        }

        while(!stack.isEmpty()){
            System.out.println("");
            Puzzle result = stack.pop();
            result.print();
        }

    }
}


class Puzzle implements Comparable<Puzzle>{
    int[] arr;// data
    int zeroLoc; //location of 0
    int step; 
    Puzzle parent;

    public int value(){
        return this.step + this.MD();
    }

    //compute the Manhattan distance
    public int MD(){
        int distance = 0;
        for (int i = 0; i < 9; i++){
            int value = this.arr[i]; //the value of i position
            if(value != 0){
                int location = value - 1; //the correct position
                int temp = Math.abs(location - i)/ 3 + Math.abs(location - i) % 3;
                distance += temp;
            }
        }
        return distance;
    }

    public int compareTo(Puzzle p){
        return this.value() - p.value();
    }
    public boolean canUp(){
        int pos = this.zeroLoc;
        if (pos < 3){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean canDown(){
        int pos = this.zeroLoc;
        if (pos > 5){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean canLeft(){
        int pos = this.zeroLoc;
        if (pos%3 == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean canRight(){
        int pos = this.zeroLoc;
        if (pos%3 == 2){
            return false;
        }
        else{
            return true;
        }
    }
    public Puzzle move(int n){
        Puzzle temp = new Puzzle();        
        temp.arr = new int[9];
        for (int i = 0; i < 9; i++){
            temp.arr[i] = this.arr[i];
        }
        int pos = this.zeroLoc;
        int p = 0;
        switch (n) {
            //up
            case 0:
                p = pos - 3;
                break;
            //down
            case 1:
                p = pos + 3;
                break;
            //left
            case 2:
                p = pos - 1;
                break;
            //right
            case 3:
                p = pos + 1;
                break;
        }
        temp.arr[pos] = this.arr[p];
        temp.arr[p] = 0;
        temp.zeroLoc = p;
        temp.step = this.step + 1;
        temp.parent = this;
        return temp;
    }

    public void print(){
        for(int i = 0; i < 9; i++){
            if (i%3 == 2){
                System.out.println(this.arr[i]);
            }
            else{
                System.out.print(this.arr[i]);
                System.out.print(" ");
            }
        }
    }
}