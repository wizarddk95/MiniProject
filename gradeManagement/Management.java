package gradeManagement;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * (2022.05.07)
 * �߰��ϰ� ���� ��ɵ� 
 * 1. Scanner ��ü�� �޼ҵ�� �Է°��� ���� �� �ٸ� Ÿ���� ������ �ٽ� ������� ����ϱ�. (����� ������ ���� ���α׷��� ����)
 * 2. "�л� ���� �߰�" ���� Ư�� Ű, ���� ��� q �� �Է����� �������� ��� �߰��� �� �ְ� �ϴ� ���. (�߰��Ϸ� - 22.05.07)
 * 
 * �˰� �� ��.
 * 1. nextLine()�� ���� �Ǹ� �Է°��� ��ٸ��� �ʰ� �������� �Ѿ����. next() �޼��带 ����ؼ� �ذ�.
 * 2. ���������� �޼ҵ忡�� �ٷ� �� �� ��� Set ��ü�� Ŭ���� ������ ������.
 * 3. case ������ {}�� ������� ������ ���ú����� ����� �� ����.
 * 4. String���� ���ڰ��� ���� �� == �� ���ϰ� �Ǹ� �޸� �ּҰ��� ���ϱ⶧���� equals()�� ����ؾ���.
 */
public class Management {
	
	/* �л��� ������ ���̺� ���� */
	private static Set<Student> table = new HashSet<>();
	
	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("           [   �� �� �� �� �� �� �� ��   ]         	  ");
		System.out.println("==================================================");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("1. �л� ���� �߰�");
			System.out.println("2. ����ǥ ���");
			System.out.println("3. �л� ���� ����");
			System.out.println("4. �л� ���� ����");
			System.out.println("5. ����");
			System.out.println();
			
			System.out.print("��ȣ�� �Է����ּ���> ");
			
			switch(sc.nextInt()) {
				/* 1. �л� ���� �߰� */
				case 1:
				{	
					while (true) {
						System.out.println("\n[���� �߰�]");
						System.out.println("[�߰��� �л��� �̸��� �Է����ּ���][����: q]");

						System.out.print("�л� �̸�> ");
						String name = sc.next();
						if(name.equals("q")) {
							System.out.println();
							break; 
						}

						System.out.print("���� ����> ");
						int kor = sc.nextInt();

						System.out.print("���� ����> ");
						int eng = sc.nextInt();

						System.out.print("���� ����> ");
						int mat = sc.nextInt();

						Student student = new Student(name, kor, eng, mat);
						table.add(student);
						System.out.println("[�ش� �л� ������ �߰��Ǿ����ϴ�]\n");
					}
					break;
				}
				
				/* 2. ����ǥ ��� */
				case 2:
				{
					System.out.println("\n=================[����ǥ ���]====================");
					System.out.println(" �л��̸�\t ����\t ����\t ����\t �������");
					
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
				
				/* 3. �л� ���� ���� */
				case 3: 
				{
					System.out.println("\n[�л� ���� ����]");
					System.out.println("[������ �л��� �̸��� �Է����ּ���]");
					String name = sc.next();
					
					Iterator<Student> iter = table.iterator();
					while(iter.hasNext()) {
						Student student = iter.next();
						if(name.equals(student.getName())) {
							System.out.print("���� ����> ");
							int kor = sc.nextInt();
							student.setKor(kor);

							System.out.print("���� ����> ");
							int eng = sc.nextInt();
							student.setEng(eng);

							System.out.print("���� ����> ");
							int mat = sc.nextInt();
							student.setMath(mat);
						}
					}
					System.out.println("[�ش� �л������� ���� �Ǿ����ϴ�]\n");
					break;
				}
				
				/* 4. �л� ���� ���� */
				case 4:
				{
					System.out.println("\n[�л� ���� ����]");
					System.out.print("�л� �̸�> ");
					String name = sc.next();
					
					Iterator<Student> iter = table.iterator();
					while(iter.hasNext()) {
						Student student = iter.next();
						if(name.equals(student.getName())) {
							table.remove(student);
						}
					}
					System.out.println("[�ش� �л��� ������ �����Ǿ����ϴ�]\n");
					break;
				}
				
				/* 5. ���α׷� ���� */
				case 5:
					System.out.println("\n[���α׷��� �����մϴ�]");
					sc.close();
					System.exit(0);
			}
		}
	}
}
