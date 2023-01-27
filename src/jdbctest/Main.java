package jdbctest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import db.GoCSV;
import repository.DramaRepository;
import repository.Top10Repository;
import repository.UserLoginRepository;
import repository.UserRepository;
import db.InitialData;
import dto.User;

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
		DramaRepository drama = new DramaRepository();
		Top10Repository top10 = new Top10Repository();
		// 유저 선택 받음,
		Scanner sc = new Scanner(System.in);
		userChoice = sc.nextInt();
		// if login
		UserLoginRepository userLogin = new UserLoginRepository();
		UserRepository user = new UserRepository();
		if (userChoice == 1) {
			System.out.println("현제 등록되어있는 아이디 ");
			System.out.println(userLogin.showIdList());
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
			System.out.println("비밀번호를 입력하세요 ");
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
			System.out.println("회원가입중...");
			System.out.println("회원가입 완료...");

		}

		// 로그인후, 연령대 거른후 top10보여주고 좋아하는 장르 , 배우 , vod, 감독 선택해서 보여주고
		System.out.println("로그인이 완료되였습니다...");
		boolean flag1 = true;
		int idx = userLogin.showIdList().indexOf(userId) + 1;
		while (flag1) {
			System.out.println("다음 보기중 하나를 선택하세요 ");
			System.out.println("1. top10랭킹 드라마 , 2. 유저 정보 확인, 3. 검색 필터 사용 9.종료 ");
			userChoice = sc.nextInt();
			// if 1번이면
			if (userChoice == 1) {

				System.out.println(top10.findTop10(idx));
//				System.out.println(u.getUserId());
			}
			// int age = 올해 (2023) - (select birthYear from user where user name = "name"
			// age < select 관람가 from drama where 앞 두자리 숫자 가저온것보다 age 가 작은경우

			// elseif 2번이면
			else if (userChoice == 2) {
				System.out.println(user.findUser(idx));
				// select * from user

			} else if (userChoice == 3) {
				// 3번 검색 필터 사용
				boolean flag2 = true;
				while (flag2) {
					System.out.println("1.평점순으로 정렬 2. 특정 배우 작품 찾기 3. 특정 플랫폼의 작품 찾기 4.특정 장르 검색  9. 메인화면 ");
					userChoice = sc.nextInt();
					// 공통적으로 나가기 만들것.!!!!
					if (userChoice == 1) {

						System.out.println("1. 오름차순, 2.내림차순 9.나가기");
						userChoice = sc.nextInt();
						// 공통적으로 나가기 만들것.!!!!
						if (userChoice == 1) {
							System.out.println(drama.ascedingRating(idx));

						} else if (userChoice == 2) {
							System.out.println(drama.descedingRating(idx));
						} else if (userChoice == 9)
							continue;

					} else if (userChoice == 2) {
						// inpu 배우scanner 사용
						System.out.println("찾으시는 배우 이름을 입력하세요 ");
						String actor = null;
						actor = sc.next();
						System.out.println(top10.findTop10byCast(idx, actor));

					} else if (userChoice == 3) {
						// input 감독
						// select * from drama where 감독 like "감독 "
						boolean flag3 = true;
						while (flag3) {
							System.out.println("찾으시는 플랫폼 이름을 입력하세요 ");
							List<String> pNames = Arrays.asList("Netflix", "Wavve", "TVING", "Watcha");
							System.out.println(pNames + "중에서 하나 입력 ");

							String platform = null;
							platform = sc.next();
							System.out.println(pNames.contains(platform));
							if (!(pNames.contains(platform))) {

								System.out.println("잘못된 이름 ");
								continue;
							} else {
								System.out.println(top10.findTop10byPlatform(idx, platform));
								flag3 = false;
							}
						}

					} else if (userChoice == 4) {
						// input 장르
						// select * from drama where 장르like "장르"
						boolean flag4 = true;
						while (flag4) {
							System.out.println("찾으시는 장르 이름을 입력하세요 ");
							List<String> gNames = Arrays.asList("Thriller", "Action", "Romance", "Melodrama");
							System.out.println(gNames + "중에서 하나 입력 ");

							String genre = null;
							genre = sc.next();
							if (!(gNames.contains(genre))) {

								System.out.println("잘못된 이름 ");
								continue;
							} else {
								System.out.println(top10.findTop10byGenre(idx, genre));
								flag4 = false;
							}
						}

					}

					else if (userChoice == 9) {
						flag2 = false;
					}
				}
			} else if (userChoice == 9) {
				flag1 = false;
				System.out.println("시스템 종료... ");
			}
		}

		// 그리고 만들떄 미리 데이터 몇가지 넣어 둘 것... 특히 user 정보랑 user login 갯수까지 맞춰서.

	}

}
