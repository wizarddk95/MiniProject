package gradeManagement;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * (2022.05.07)
 * 추가하고 싶은 기능들 
 * 1. Scanner 객체의 메소드로 입력값을 받을 때 다른 타입이 들어오면 다시 적으라고 출력하기. (현재는 에러가 떠서 프로그램이 멈춤)
 * 2. "학생 정보 추가" 에서 특정 키, 예를 들면 q 를 입력했을 때까지는 계속 추가할 수 있게 하는 기능. (추가완료 - 22.05.07)
 * 
 * 알게 된 점.
 * 1. nextLine()을 쓰게 되면 입력값을 기다리지 않고 다음으로 넘어가진다. next() 메서드를 사용해서 해결.
 * 2. 전역변수를 메소드에서 바로 쓸 수 없어서 Set 객체를 클래스 변수로 선언함.
 * 3. case 문에서 {}를 사용하지 않으면 로컬변수를 사용할 수 없음.
 * 4. String에서 문자값을 비교할 때 == 로 비교하게 되면 메모리 주소값을 비교하기때문에 equals()를 사용해야함.
 */
public class Management {
	
	/* 학생을 관리할 테이블 생성 */
	private static Set<Student> table = new HashSet<>();
	
	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("           [   성 적 관 리 프 로 그 램   ]         	  ");
		System.out.println("==================================================");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 성적표 출력");
			System.out.println("3. 학생 정보 수정");
			System.out.println("4. 학생 정보 삭제");
			System.out.println("5. 종료");
			System.out.println();
			
			System.out.print("번호를 입력해주세요> ");
			
			switch(sc.nextInt()) {
				/* 1. 학생 정보 추가 */
				case 1:
				{	
					while (true) {
						System.out.println("\n[정보 추가]");
						System.out.println("[추가할 학생의 이름을 입력해주세요][종료: q]");

						System.out.print("학생 이름> ");
						String name = sc.next();
						if(name.equals("q")) {
							System.out.println();
							break; 
						}

						System.out.print("국어 점수> ");
						int kor = sc.nextInt();

						System.out.print("영어 점수> ");
						int eng = sc.nextInt();

						System.out.print("수학 점수> ");
						int mat = sc.nextInt();

						Student student = new Student(name, kor, eng, mat);
						table.add(student);
						System.out.println("[해당 학생 정보가 추가되었습니다]\n");
					}
					break;
				}
				
				/* 2. 성적표 출력 */
				case 2:
				{
					System.out.println("\n=================[성적표 출력]====================");
					System.out.println(" 학생이름\t 국어\t 영어\t 수학\t 평균점수");
					
					Iterator<Student> iter = table.iterator();
					while(iter.hasNext()) {
						Student student = iter.next();
						String name = student.getName();
						int kor = student.getKor();
						int eng = student.getEng();
						int mat = student.getMath();
						
						double avg = ( (int)((kor + eng + mat)/3.0*10+0.5) ) / 10.0;
						
						
						System.out.println(" "+name+"\t "+kor+"\t "+eng+"\t "+mat +"\t "+ avg);
					}
					System.out.println("===============================================\n");
					break;
				}
				
				/* 3. 학생 정보 수정 */
				case 3: 
				{
					System.out.println("\n[학생 정보 수정]");
					System.out.println("[수정할 학생의 이름을 입력해주세요]");
					String name = sc.next();
					
					Iterator<Student> iter = table.iterator();
					while(iter.hasNext()) {
						Student student = iter.next();
						if(name.equals(student.getName())) {
							System.out.print("국어 점수> ");
							int kor = sc.nextInt();
							student.setKor(kor);

							System.out.print("영어 점수> ");
							int eng = sc.nextInt();
							student.setEng(eng);

							System.out.print("수학 점수> ");
							int mat = sc.nextInt();
							student.setMath(mat);
						}
					}
					System.out.println("[해당 학생정보가 수정 되었습니다]\n");
					break;
				}
				
				/* 4. 학생 정보 삭제 */
				case 4:
				{
					System.out.println("\n[학생 정보 삭제]");
					System.out.print("학생 이름> ");
					String name = sc.next();
					
					Iterator<Student> iter = table.iterator();
					while(iter.hasNext()) {
						Student student = iter.next();
						if(name.equals(student.getName())) {
							table.remove(student);
						}
					}
					System.out.println("[해당 학생의 정보가 삭제되었습니다]\n");
					break;
				}
				
				/* 5. 프로그램 종료 */
				case 5:
					System.out.println("\n[프로그램을 종료합니다]");
					sc.close();
					System.exit(0);
			}
		}
	}
}
