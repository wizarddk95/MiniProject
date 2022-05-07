package lottoSimulation;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 2022.05.07
 * �߰��ϰ� ���� ��ɵ�.
 * 1. �������� ��ȣ �Է��� �� ���ٿ� ���ڸ��� ������ �־��ְ� �Է��ϰ��� �� �Է��� ���ڿ��� ������ �����ڷ� ��ȣ�� ����. 
 * �ش� ��ȣ�� �迭 ���� �� Set�� �����ϱ�.
 * 
 * �˰� �� ��
 * 1. ���׸� Ÿ�Ժ����� �迭�� ���� ����.
 * 2. return���� �޼ҵ带 �����ϹǷ� while���� ������������ break�� �̿�.
 * 3. �迭�� ����� ������ ���� ���Ϸ��� Arrays.equals() �޼��带 �̿�. 
 * 4. == �����ڴ� �ּҰ��� ���ϱ� ������ �迭 ����� ������ ���� ���Ƶ� �ּҰ� �ٸ��� false�� ��.
 * 
 * �ñ��� ��
 * 1. 49��� 79�࿡�� int[] lotto = new int[6]�� for�� ������ ���� �Ǹ� Set ��ü�� ���� ���� 6�� �����.
 * 
 */
public class LottoSimulation {
	static Set<int[]> lottoSet = new HashSet<>();
	
	public static void main(String[] args) {
		System.out.println("===================================");
		System.out.println("      [�ζ� ��ȣ ���� �� �ùķ��̼�]       ");
		System.out.println("===================================");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("����/�ڵ��� �������ּ���");
		System.out.println("1. ����");
		System.out.println("2. �ڵ�");
		System.out.print("> ");
		
		String kind = sc.next();
		
		while (true) {
			/* ���� ��ȣ */
			if (kind.equals("1") || kind.equals("����")) {
				System.out.print("\n���� ���� �Է����ּ���(�ִ�:5����)> ");
				int game = sc.nextInt();
				
				/* ���� ��ȣ �Է� */
				for(int i=0; i<game; i++) {
					int[] lotto = new int[6];
					
					System.out.println("\t[" + (i+1) + "��° ����]");
					for(int j=0; j<6; j++) {
						System.out.print((j+1) + "��° ��ȣ �Է�> ");
						int manual = sc.nextInt();
						lotto[j] = manual;
						
						/* �ߺ� ���� */
						if(j > 0) {
							for(int k=0; k<j; k++) {
								if(lotto[k] == lotto[j]) {
									j--;
								}
							}
						}
						
					}
					Arrays.sort(lotto);
					lottoSet.add(lotto);
					break;
				}
				
			/* �ڵ� ��ȣ */
			} else if (kind.equals("2") || kind.equals("�ڵ�")) {
				System.out.print("\n���� ���� �Է����ּ���(�ִ�:5����)> ");
				int game = sc.nextInt();
				
				/* �ڵ� ��ȣ ���� */
				for(int i=0; i<game; i++) {
					int[] lotto = new int[6];
					
					for(int j=0; j<6; j++) {
						int auto = (int)(Math.random()*45+1);
						lotto[j] = auto;
						
						/* �ߺ� ���� */
						if(j > 0) {
							for(int k=0; k<j; k++) {
								if(lotto[k] == lotto[j]) {
									j--;
								}
							}
						}
					}
					Arrays.sort(lotto);
					
					/* �ߺ� ���� ���� */
					if(lottoSet.contains(lotto)) {
						i--;
					} else {
						lottoSet.add(lotto);
					}
				}
				break;
			
			/* �Է� ���� */
			} else {
				System.out.print("�ٽ� �Է����ּ���> ");
				kind = sc.next();
				continue;
			}
		}
		
		/* �ζ� ���� ��� */
		System.out.println("\n=====[ �ζ� ���� ��� ]======");
		
		Iterator<int[]> iter = lottoSet.iterator();
		while(iter.hasNext()) {
			int[] lotto = iter.next();
			System.out.println(Arrays.toString(lotto));
		}
		
		System.out.println("==========================");
		
		System.out.print("�ùķ��̼��� �����ðڽ��ϱ�(y/n)> ");
		String simul = sc.next();
		
		if(simul.equals("y")) {
			int count = 0;
			
			/* �� ��ȣ ����*/
			while(true) {
				count++; //�ݺ� Ƚ�� 
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
				System.out.println(Arrays.toString(correct)); //�� ��ȣ ���
				
				/* �� �ζǹ�ȣ�� �� */
				Iterator<int[]> iterator = lottoSet.iterator();
				while (iterator.hasNext()) {
					int[] myLotto = iterator.next();
					
					if (Arrays.equals(myLotto, correct)) {
						DecimalFormat formatter = new DecimalFormat("###,###");
						System.out.println("\n[�ùķ��̼� ���]");
						System.out.println(formatter.format(count) + "�� ����");
						System.out.println(formatter.format(count*lottoSet.size()*1000) + "�� �ҿ�");
						return;
					} else {
						continue;
					}
				}
			}
		} else {
			System.out.println("[���α׷��� �����մϴ�]");
			System.exit(0);
		}
		
	}
}
