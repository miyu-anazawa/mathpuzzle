package mathpuzzle;

import java.util.List;
import java.util.ArrayList;

public class Q1 {

    //10進数,2進数,8進数のいずれで表現しても回文数となる数のうち、10進数の10以上で最小の値を求めてください。
    
    //10進数の回文チェック
    public boolean decimal(int n){

        //桁数を取得
        int n_10len = String.valueOf(n).length();

        //10進数を桁数ごとにListに格納
        List<Integer> decimal = new ArrayList<Integer>();
        for(int i = n_10len - 1; i > 0; i--){
            int number = (int) Math.floor( n / Math.pow(10,i));
            n -= number * Math.pow(10,i);
            decimal.add(number);

            if(i==1){
                decimal.add(n);
            }
        }
        return check(decimal);
    }

    public boolean check(List<Integer> list){
        //回文チェック
        for(int j = 0; j < Math.floor(list.size() / 2); j++){
            if(list.get(j) != list.get(list.size() - 1 - j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        Q1 q = new Q1();
        System.out.println(q.decimal(3345433));
    }
    
}