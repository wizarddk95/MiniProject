package lottoSimulation;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 2022.05.07
 * 추가하고 싶은 기능들.
 * 1. 수동으로 번호 입력할 때 한줄에 숫자마다 공백을 넣어주고 입력하게한 뒤 입력한 문자열을 공백을 구분자로 번호를 추출. 
 * 해당 번호로 배열 생성 및 Set에 저장하기. (이건 오히려 더 복잡한 로직이 될거같아서 패스함)
 * 
 * 알게 된 점
 * 1. 제네릭 타입변수에 배열도 대입 가능.
 * 2. return문은 메소드를 종료하므로 while문을 빠져나오려면 break을 이용.
 * 3. 배열의 요소의 순서와 값을 비교하려면 Arrays.equals() 메서드를 이용. 
 * 4. == 연산자는 주소값을 비교하기 때문에 배열 요소의 순서와 값이 같아도 주소가 다르면 false가 뜸.
 * 
 * 궁금한 점
 * 1. 49행과 79행에서 int[] lotto = new int[6]을 for문 위에다 쓰게 되면 Set 객체에 같은 값이 6개 저장됨.
 * 2. 디버그 모드에서는 에러가 안뜨는데 실행하면 에러가 뜨는 경우가 있었다. 이유를 도저히 모르겠다. 
 * 3. 에러가 뜨는 곳은 160행인데 게임수 * 1000을 count와 곱하게 되면 제대로된 연산이 되지않는다. 
 * (결과 앞에 "-" 가 뜰때도 있다. 어이가 없다. 초창기에 그렇게 처리하긴 했는데 지금은 없음)
 * 그래서 연산을 없애고 옆에 (x" + lottoSet.size() + " x1000 원 소요)를 붙였다.
 */
public class LottoSimulation {
	static Set<int[]> lottoSet = new HashSet<>();
	
	public static void main(String[] args) {
		System.out.println("===================================");
		System.out.println("      [로또 번호 생성 및 시뮬레이션]       ");
		System.out.println("===================================");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수동/자동을 선택해주세요");
		System.out.println("1. 수동");
		System.out.println("2. 자동");
		System.out.print("> ");
		
		String kind = sc.next();
		
		while (true) {
			/* 수동 번호 */
			if (kind.equals("1") || kind.equals("수동")) {
				System.out.print("\n게임 수를 입력해주세요(최대:5게임)> ");
				int game = sc.nextInt();
				
				/* 수동 번호 입력 */
				for(int i=0; i<game; i++) {
					int[] lotto = new int[6];
					
					System.out.println("\n\t[" + (i+1) + "번째 조합]");
					for(int j=0; j<6; j++) {
						System.out.print((j+1) + "번째 번호 입력> ");
						int manual = sc.nextInt();
						lotto[j] = manual;
						
						/* 중복 제거 */
						if(j > 0) {
							for(int k=0; k<j; k++) {
								if(lotto[k] == lotto[j]) {
									System.out.println("번호가 중복되었습니다. 다시 입력해 주세요.");
									j--;
								}
							}
						}
					}
					Arrays.sort(lotto);
					lottoSet.add(lotto);
				}
				break;
			/* 자동 번호 */
			} else if (kind.equals("2") || kind.equals("자동")) {
				System.out.print("\n게임 수를 입력해주세요(최대:5게임)> ");
				int game = sc.nextInt();
				
				/* 자동 번호 생성 */
				for(int i=0; i<game; i++) {
					int[] lotto = new int[6];
					
					for(int j=0; j<6; j++) {
						int auto = (int)(Math.random()*45+1);
						lotto[j] = auto;
						
						/* 중복 제거 */
						if(j > 0) {
							for(int k=0; k<j; k++) {
								if(lotto[k] == lotto[j]) {
									j--;
								}
							}
						}
					}
					Arrays.sort(lotto);
					
					/* 중복 조합 제거 */
					Iterator<int[]> iter = lottoSet.iterator();
					while(iter.hasNext()) {
						if(Arrays.equals(lotto, iter.next())) {
							i--;
						} 
					}
					lottoSet.add(lotto);
				}
				break;
			
			/* 입력 오류 */
			} else {
				System.out.print("다시 입력해주세요> ");
				kind = sc.next();
				continue;
			}
		}
		
		/* 로또 조합 출력 */
		System.out.println("\n=====[ 로또 조합 출력 ]======");
		Iterator<int[]> iter = lottoSet.iterator();
		while(iter.hasNext()) {
			int[] lotto = iter.next();
			System.out.println(Arrays.toString(lotto));
		}
		
		System.out.println("==========================");
		
		System.out.print("시뮬레이션을 돌리시겠습니까(y/n)> ");
		String simul = sc.next();
		
		if(simul.equals("y")) {
			int count = 0;
			
			/* 비교 번호 생성*/
			while(true) {
				count++; //반복 횟수 
				int[] correct = new int[6];
				for (int j = 0; j < 6; j++) {
					int auto = (int) (Math.random() * 45 + 1);
					correct[j] = auto;

					if (j > 0) {
						for (int k = 0; k < j; k++) {
							if (correct[k] == correct[j]) {
								j--;
							}
						}
					}
				}
				Arrays.sort(correct);
				System.out.println(Arrays.toString(correct)); //비교 번호 출력
				
				/* 내 로또번호와 비교 */
				Iterator<int[]> iterator = lottoSet.iterator();
				while (iterator.hasNext()) {
					int[] myLotto = iterator.next();
					
					if (Arrays.equals(myLotto, correct)) {
						DecimalFormat formatter = new DecimalFormat("###,###");
						System.out.println("\n=======[시뮬레이션 결과]=======\n");
						System.out.println(Arrays.toString(myLotto) + " - 당첨번호");
						System.out.println("\n" + formatter.format(count) + "번 실행 (x" + lottoSet.size() + " x1000 원 소요)");
						System.out.println("\n===========================");
						
						sc.close();
						System.out.println("\n[프로그램을 종료합니다]");
						return;
					} else {
						continue;
					}
				}
			}
		} else {
			System.out.println("[프로그램을 종료합니다]");
			System.exit(0);
		}
		
	}
}
