package jdbctest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.GoCSV;
import repository.DramaRepository;
import repository.UserLoginRepository;
import repository.UserRepository;
import db.InitialData;
import java.util.Scanner;

//import dto.Author;
//import service.AuthorServiceImpl;
public class Main {

	public static void main(String[] args) {
		int userChoice = 0;
		String userId = null;
		String password = null;
		System.out.println("영화추천 서비스에 오신것을 환영합니다.");
		System.out.println("1. 로그인 2. 회원가입");
		InitialData init = new InitialData();
		// 유저 선택 받음,
		Scanner sc = new Scanner(System.in);
		userChoice = sc.nextInt();
		// if login
		UserLoginRepository userLogin = new UserLoginRepository();
		UserRepository user = new UserRepository();
		if (userChoice == 1) {
			System.out.println("아이디를 입력하세요 ");
			userId = sc.next();

			// id 가 존재하는지 확인
			while (userLogin.isExistUserId(userId) == false) {
				System.out.println("올바른 아이디가 아닙니다. 다시입력하세요");
				userId = sc.next();
			}
			// password 입력

			System.out.println("비밀번호를 입력하세요 ");
			password = sc.next();
			// 그 아이디에 상응하는 패스워드도 일치하는지 확인
			while (userLogin.isExistUserIdPassword(userId, password) == false) {
				// 일치하지 않으면 재입력
				System.out.println("올바른 비밀번호가 아닙니다. 다시입력하세요");
				password = sc.next();
			}
			// 일치하면 ㄱㄱㄱ

		}
		// elif 회원가입
		else if (userChoice == 2) {
			// 아이디 생일 이름 --> 유저 테이블에 입력
			System.out.println("아이디를 입력하세요 ");
			userId = sc.next();
			// id 가 존재하는지 확인
			while (userLogin.isExistUserId(userId) == true) {
				System.out.println("중복된 아이디입니다. 다시입력하세요");
				userId = sc.next();
			}
			System.out.println("비밀번를 입력하세요 ");
			password = sc.next();
			System.out.println("이름를 입력하세요 ");
			String name = sc.next();
			System.out.println("구독하는 서비스 입력하세요 (넷플 1, 웨이브 2, 왓챠 3, 티빙 4 입 ");
			int vod = sc.nextInt();
			System.out.println("태어난 연도를 입력하세요 ");
			int birthYear = sc.nextInt();
			userLogin.insertUserLogin(userId, password);
			// vod name birthYear 만 user table 에 insert.
			user.insertUser(name, vod, birthYear);
			

		}

		// 아이디 비번 -- 유져 로그인 테이블에 입력후 로그인 처리

		// 로그인후, 연령대 거른후 top10보여주고 좋아하는 장르 , 배우 , vod, 감독 선택해서 보여주고
		// 랭킹 별점

	}

}
