package kr.bit.model;

public class MyCalc {
	
	public int hap(int su1, int su2) {
		
		int sum = 0;
		for(int i = su1; i <= su2; i++) {
			sum += i;
		}
		return sum;
		

		// 주의)) 실수로 무한반복문으로 짰더니, 응답이 되지 않았다.
		// hap메서드 호출 후, 무한루프에 빠져 return되지 못했음.
//		int sum = 0;
//		for(int i = su1; su1 <= su2; i++) {
//			sum += i;
//		}
//		return sum;
		
	}
	
	public int sum() {
		int sum = 0;
		for(int i = 1; i <=100; i++) {
			sum += i;
		}
		return sum;
	}

}
