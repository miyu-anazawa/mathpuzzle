package mathpuzzle;

import java.lang.reflect.Method;

public class Q64 {

//	private int board = 0b0000000000000000000000000;
	private int meet = 0;
	private int n = 4;
	private int start = 1;
	private int end = (int)Math.pow(n, 2);

	public void calcPerson(){

		int min_missboard = (int)Math.pow(2, (int)Math.pow(n, 2)-1);
		// int max_missboard = (int)Math.pow(2, (int)Math.pow(n ,2))-1;

		// System.out.println(min_missboard);   //16777216
		// System.out.println(max_missboard);   //33554431

		try{
			int a_muki = 4;
			int b_muki = 3;


			for(int board = 0b0000000000000000000000000; board <= min_missboard; board++){
				if(board % 2 != 1 && board < min_missboard){
					int a = start;
					int b = end;
					boolean finish = false;
					int a_count = 0;
					int b_count = 0;
					int miss_count = 0;

					//通れないマスをカウント
					for(int j = 1; j < end; j++){
						if(checkBoard(j,board)=="no"){
							miss_count++;
						}
					}

					//AさんorBさんが一歩も進めない場合、塗りつぶされている個数が多い(n-1)^2乗の場合は歩かせない
					if(((checkBoard(1 + n,board)=="no" && checkBoard(2,board)=="no")
							|| (checkBoard(end - n,board)=="no" && checkBoard(end - 1,board)=="no"))
							|| miss_count > Math.pow(n-1,2)){
					}else{
						do{


							if(a_muki == 0 || b_muki ==0){
								finish=true;
								break;
							}

							Q64 p = new Q64();
							String a_smethod = "person"+String.valueOf(a_muki);
							String b_smethod = "person"+String.valueOf(b_muki);
							Method a_method = Q64.class.getMethod(a_smethod,int.class,int.class);
							Method b_method = Q64.class.getMethod(b_smethod,int.class,int.class);

							if(a==start){
								a_count ++;
							}
							if(b==end){
								b_count ++;
							}

							int after_a = (int)a_method.invoke(p,a,board);
							int after_b = (int)b_method.invoke(p,b,board);

							//問題なく進めている場合、向きが変わっているかを進んだ座標から確認する
							if(after_a != 0 && after_b != 0){
								a_muki = setMuki(after_a - a);
								b_muki = setMuki(after_b - b);
							}else{
								finish = true;
								break;
							}

							a = after_a;
							b = after_b;

							//スタート地点に3回戻ってきた場合はゴールできないためループ終了
							if(a_count > 3 || b_count > 3){
								finish=true;
								break;
							}

							//座標が同じ場合は出会ったのでループ終了
							if(a == b){
								// System.out.println("calcPerson======="+board+"=========");
								// System.out.println("出会いました!!!!!!!!!!!!!!!!!。board: "+board);
								finish = true;
								meet++;
								break;
							}

							//どちらかがゴールした場合もループ終了
							if(a==end || b == start){
								finish=true;
								break;
							}

						}while(finish==false);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(meet+"回出会いました。");

	}

	public int setMuki(int move){
		switch(move){
		case 1:
			return 1;
		case -1:
			return 2;
		case 4:
			return 4;
		case -4:
			return 3;
		}

		return 0;
	}

	public int person4(int place,int board){
		if(place -1 > 0 && place % n != 1){
			if(checkBoard(place - 1,board)=="ok"){
				return place - 1;
			}
		}
		if(place + n <= end ){
			if(checkBoard(place + n,board)=="ok"){
				return place + n;
			}
		}
		if(place + 1 <= end && place % n != 0){
			if(checkBoard(place + 1,board)=="ok"){
				return place + 1;
			}
		}
		if(place - n > 0 ){
			if(checkBoard(place - n,board)=="ok"){
				return place - n;
			}
		}

		return 0;
	}

	public int person3(int place,int board){
		if(place + 1 <= end && place % n != 0){
			if(checkBoard(place + 1,board)=="ok"){
				return place + 1;
			}
		}
		if(place - n > 0 ){
			if(checkBoard(place - n,board)=="ok"){
				return place - n;
			}
		}
		if(place -1 > 0 && place % n != 1){
			if(checkBoard(place - 1,board)=="ok"){
				return place - 1;
			}
		}
		if(place + n <= end ){
			if(checkBoard(place + n,board)=="ok"){
				return place + n;
			}
		}

		return 0;
	}

	public int person2(int place, int board){
		if(place - n > 0 ){
			if(checkBoard(place - n,board)=="ok"){
				return place - n;
			}
		}
		if(place -1 > 0 && place % n != 1){
			if(checkBoard(place - 1,board)=="ok"){
				return place - 1;
			}
		}
		if(place + n <= end ){
			if(checkBoard(place + n,board)=="ok"){
				return place + n;
			}
		}
		if(place + 1 <= end && place % n != 0){
			if(checkBoard(place + 1,board)=="ok"){
				return place + 1;
			}
		}

		return 0;
	}

	public int person1(int place, int board){
		if(place + n <= end ){
			if(checkBoard(place + n,board)=="ok"){
				return place + n;
			}
		}
		if(place + 1 <= end && place % n != 0){
			if(checkBoard(place + 1,board)=="ok"){
				return place + 1;
			}
		}
		if(place - n > 0 ){
			if(checkBoard(place - n,board)=="ok"){
				return place - n;
			}
		}
		if(place -1 > 0 && place % n != 1){
			if(checkBoard(place - 1,board)=="ok"){
				return place - 1;
			}
		}
		return 0;
	}

	public String checkBoard(int move, int board){
		if((board >> (move - 1)) % 2 == 0){
			return "ok";
		}
		return "no";
	}

	public static void main(String args[]){
		Q64 p = new Q64();
		p.calcPerson();
	}
}