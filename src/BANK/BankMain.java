package BANK;

import java.util.Scanner;

public class BankMain {

	public static void main(String[] args) {
		
		// BankDTO의 정보를 담는 client객체 선언
		BankDTO client = new BankDTO();
		
		// BankDAO의 정보를 담는 server객체 선언
		BankDAO server = new BankDAO();
		
		// 입력을 위한 sc객체 선언
		Scanner sc = new Scanner(System.in);
		
		// 프로그램 실행을 위한 변수 run 선언
		boolean run = true;
		
		// 항목을 선택할 때 필요한 변수 menu 선언
		int menu = 0;
		
		System.out.println("ICIA은행에 오신것을 환영합니다!");
		System.out.println("원하시는 메뉴를 선택해주세요!");
		System.out.println();
		// while문을 사용하여 프로그램 실행
		server.connect();
		while(run) {
			System.out.println();
			System.out.println("=====================");
			System.out.println("1.계좌생성       2.입금");
			System.out.println("3.출금             4.잔액조회");
			System.out.println("5.송금             6.종료");
			System.out.println("=====================");
			System.out.print("메뉴 선택 >> ");
			menu = sc.nextInt();
			
			
			switch(menu) {
			
			case 1:
				
				int clientNumber = server.clientNumber()+1 ;
				
				System.out.println("회원정보를 입력해주세요!");
				System.out.print("회원이름 >> ");
				String cName = sc.next();
				
				System.out.print("계좌번호 >> ");
				String accountNumber = sc.next();
				
				System.out.print("초기 입금액 >> ");
				int balance = sc.nextInt();
				//client.setBalance(balance);
				
				client = new BankDTO(clientNumber, cName, accountNumber, balance);

				server.insertClient(client);
				
				break;
				
			case 2:
				System.out.print("계좌번호 >> ");
				accountNumber = sc.next();
				
				System.out.print("입금액 >> ");
				balance = sc.nextInt();
				
				// client = new BankDTO(clientNumber, cName, accountNumber, balance);
				
				// client에 입력한 정보 저장하기
				client.setAccountNumber(accountNumber);
				client.setBalance(balance);
				
				// (1) BankDTO(client)의 정보를 모두 넘긴다
				server.deposit(client);
				
				// (2) accountNumber(계좌번호)와 balance(입금액)정보만 넘긴다
				//server.deposit(accountNumber,balance);
				
				break;
				
			case 3:
				// 잔액조회를 사용해서
				// 출금액이 잔액보다 많을 경우 출금하지 못하도록
				System.out.print("계좌번호 >> ");
				accountNumber = sc.next();
				
				int cBalance = server.checkBalance(accountNumber);
				// cBalance는 현재 출금하고자 하는 계좌의 잔액
				
				System.out.print("출금액 >> ");
				balance = sc.nextInt();
				// balance는 출금하고자 하는 금액
				
				if(cBalance >= balance) {
					server.withdraw(accountNumber,balance);
				} else {
					System.out.println("출금액이 " + (balance - cBalance) + "원 부족합니다!");
					System.out.println("현재 잔액은 " + cBalance + "원, 출금 요청금액은 " + balance + "원 입니다.");
				}
				break;
				
			case 4:
				System.out.print("계좌번호 >> ");
				accountNumber = sc.next();
				
				balance = server.checkBalance(accountNumber);
				
				System.out.println("잔액조회 : " + balance);
				
				break;
				
			case 5:

				// 1.송금받을 계좌가 있는지?
				// send Account
				System.out.print("송금 할 계좌번호 >> ");
				String sAccountNumber = sc.next();
				
				// receive Account
				System.out.print("송금 받을 계좌번호 >> ");
				String rAccountNumber = sc.next();
				
				System.out.print("송금액 >> ");
				balance = sc.nextInt();
				
				// 계좌가 존재하면 true, 존재하지 않으면 false
				boolean sAccount = server.checkAccount(sAccountNumber);
				boolean rAccount = server.checkAccount(rAccountNumber);

				
				// 송금할 계좌의 잔액이 송금액보다 많지 않은지?
				int sBalance = server.checkBalance(sAccountNumber);
				
				
				// 1. 보내는 사람 계좌번호
				if(sAccount) {
					// 2. 받는 사람 계좌번호
					if(rAccount) {
						// 3. 잔액이 송금보다 많아야된다
						if(sBalance >= balance) {
							// server에 send() 메소드 생성
							server.send(sAccountNumber, rAccountNumber, balance);
						} else {
							System.out.println("송금액이 " + (balance - sBalance) + "원 부족합니다!");
							System.out.println("현재 잔액은 " + sBalance + "원, 송금 요청금액은 " + balance + "원 입니다.");
						}
					} else {
						System.out.println("받으실 분의 계좌를 확인해주세요!");
					} 
				} else {
					System.out.println("보내실 분의 계좌를 확인해주세요!");
				} 
				break;
				
			case 6:
				run = false;
				System.out.println("이용해주셔서 감사합니다!");
				break;
				
			default:
				System.out.println("다시 입력해주세요!");
				break;
				
			}//end switch
			
		}//end while
		server.conClose();
		
		
		
		
		
	}

}
