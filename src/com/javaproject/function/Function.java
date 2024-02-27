package com.javaproject.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.javaproject.dao.WorkDAO;
import com.javaproject.dto.RoutineDTO;
import com.javaproject.dto.WorkDTO;

public class Function {
	WorkDAO workDao = new WorkDAO();
	Scanner scan = new Scanner(System.in);

	// 메뉴 출력
	public void displayMenu() {
		System.out.println("----- 메뉴 -----");
		System.out.println("1. 운동 추천");
		System.out.println("2. 운동 추가");
		System.out.println("3. 운동 삭제");
		System.out.println("4. 운동 조회");
		System.out.println("5. 루틴 조회");
		System.out.println("6. 루틴 삭제");
		System.out.println("7. 헬스장 조회");
		System.out.println("0. 프로그램 저장 후 종료");
		System.out.println("----------------");
	}

	public void addExercise() {
		String workName, dummy;
		int set, number, sec, typeInput;
		String type = "";

		Scanner scanner = new Scanner(System.in);
		WorkDAO dao = new WorkDAO();

		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃" + "\u001b[1m" + " 운동 입력을 시작합니다. " + "\u001b[0m" + "┃");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
		System.out.println("운동 이름을 입력하세요.");

		E: while (true) {
			System.out.println("운동 입력을 취소하시려면 \"취소\"를 입력하세요.");
			System.out.println("");
			System.out.print("입력 : ");

			dummy = scanner.nextLine();

			if (dummy.equals("취소")) {
				System.out.println("메뉴 화면으로 돌아갑니다.");
				return;
			}

			boolean duplCheckResult;
			duplCheckResult = dao.duplicationCheckWork(dummy);

			if (duplCheckResult) {
				System.out.println("운동 이름이 중복되었습니다. 다시 입력해 주세요.");
				continue E;
			}

			workName = dummy;
			break;
		}

		A: while (true) {

			System.out.print("\033[H\033[2J");
			System.out.println("");
			System.out.println("┌────────────────────────┐");
			System.out.println("│" + " 운동 부위을 입력하세요." + "│");
			System.out.println("└────────────────────────┘");
			System.out.println("");
			System.out.println("\u001b[1m" + "1.어깨 2.가슴 3.등 4.팔 5.하체 6.전신" + "\u001b[0m");
			System.out.println("");
			System.out.println("운동 입력을 취소하시려면 \"취소\"를 입력하세요.");
			System.out.println("");
			System.out.print("입력 : ");

			if (scanner.hasNextInt()) {
				typeInput = scanner.nextInt();

				while (typeInput < 1 || typeInput > 6) {
					System.out.println("");
					System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
					continue A;
				}

				switch (typeInput) {
				case 1:
					type = "어깨";
					break A;
				case 2:
					type = "가슴";
					break A;
				case 3:
					type = "등";
					break A;
				case 4:
					type = "팔";
					break A;
				case 5:
					type = "하체";
					break A;
				case 6:
					type = "전신";
					break A;

				}

			} else {

				dummy = scanner.next();
				if (dummy.equals("취소")) {
					System.out.println("메뉴 화면으로 돌아갑니다.");
					return;
				}
				System.out.println("잘못 입력하셨습니다.");
				continue A;
			}

			break;

		}

		B: while (true) {

			System.out.println("");
			System.out.println("\u001b[1m" + "운동 한 세트를 설정합니다." + "\u001b[0m");
			System.out.println("");
			System.out.println("세트당 반복 횟수를 입력하세요. 없으면 0을 입력하세요.");
			System.out.println("운동 입력을 취소하시려면 \"취소\"를 입력하세요.");
			System.out.println("");
			System.out.print("입력 : ");

			if (scanner.hasNextInt()) {
				number = scanner.nextInt();
				break;

			} else {
				dummy = scanner.next();
				if (dummy.equals("취소")) {
					System.out.println("메뉴 화면으로 돌아갑니다.");
					return;
				}
				System.out.println("잘못 입력하셨습니다.");
				continue B;

			}

		}

		C: while (true) {
			System.out.println("");
			System.out.println("세트의 운동 시간을 입력하세요. 없으면 0을 입력하세요.");
			System.out.println("운동 입력을 취소하시려면 \"취소\"를 입력하세요.");
			System.out.println("");
			System.out.print("입력 : ");

			if (scanner.hasNextInt()) {
				sec = scanner.nextInt();
				break;

			} else {
				dummy = scanner.next();
				if (dummy.equals("취소")) {
					System.out.println("메뉴 화면으로 돌아갑니다.");
					return;
				}
				System.out.println("잘못 입력하셨습니다.");
				continue C;

			}

		}

		D: while (true) {
			System.out.println("");
			System.out.println("몇 세트 운동하시겠습니까?");
			System.out.println("운동 입력을 취소하시려면 \"취소\"를 입력하세요.");
			System.out.println();
			System.out.print("입력 : ");

			if (scanner.hasNextInt()) {
				set = scanner.nextInt();
				break;

			} else {
				dummy = scanner.next();
				if (dummy.equals("취소")) {
					System.out.println("메뉴 화면으로 돌아갑니다.");
					return;
				}
				System.out.println("잘못 입력하셨습니다.");
				continue D;

			}

		}

		dao.addExercise(workName, set, number, sec, type);

		System.out.println("입력이 완료되었습니다.");
	}

	// 3. 삭제
	public void deleteExercise() {

		System.out.print("삭제하실 운동명을 입력해 주세요. : ");
		String name = scan.nextLine();
		workDao.deleteExerciseByName(name);

	}

	public void viewExercises() {
		System.out.println("운동 목록을 조회합니다");
		List<String> exerciseList = workDao.getExerciseList();
		System.out.println("----- 운동 목록 -----");
		for (String exercise : exerciseList) {
			System.out.println(exercise);
		}
		System.out.println("----------------------");

	}

//	추천하는 메서드
	public void recommand() {

		WorkDAO dao = new WorkDAO();

		int tNum;
		boolean worked = false;

		String type = "";

		List<List<String>> wList = new ArrayList<List<String>>();

		List<String> shoulder = new ArrayList<String>();
		shoulder.add("어깨");
		wList.add(shoulder);

		List<String> chest = new ArrayList<String>();
		chest.add("가슴");
		wList.add(chest);

		List<String> leg = new ArrayList<String>();
		leg.add("하체");
		wList.add(leg);

		List<String> back = new ArrayList<String>();
		back.add("등");
		wList.add(back);

		List<String> arm = new ArrayList<String>();
		arm.add("팔");
		wList.add(arm);

		List<String> wBody = new ArrayList<String>();
		wBody.add("전신");
		wList.add(wBody);

		A: while (true) {
//			부위 입력
			System.out.println("\n┌─────────────── 운동 부위 선택────────────────┐");
			System.out.println("│ 0.종료 1.어깨 2.가슴 3.하체 4.등 5.팔 6.전신 │");
			System.out.println("└──────────────────────────────────────────────┘");
			System.out.print("입력 : ");
			tNum = scan.nextInt();
			while (6 < tNum || tNum < 0) {
				System.out.println("잘못된 값을 입력했습니다. 다시 입력해주세요.");
				System.out.println("\n┌─────────────── 운동 부위 선택────────────────┐");
				System.out.println("│ 0.종료 1.어깨 2.가슴 3.하체 4.등 5.팔 6.전신");
				System.out.println("└──────────────────────────────────────────────┘");
				System.out.print("입력 : ");
				tNum = scan.nextInt();
			}
			switch (tNum) {
			case 0:
				break A;
			case 1:
				tNum--;
				type = "어깨";
				break;
			case 2:
				tNum--;
				type = "가슴";
				break;
			case 3:
				tNum--;
				type = "하체";
				break;
			case 4:
				tNum--;
				type = "등";
				break;
			case 5:
				tNum--;
				type = "팔";
				break;
			case 6:
				tNum--;
				type = "전신";
				break;
			}
			List<WorkDTO> list = dao.recommand(type);

//			몇가지 운동할지 입력
			System.out.println("┌───────────────────────────────────────────────┐");
			System.out.println("│ [" + type + "]을(를) 선택하셨습니다. ");
			System.out.println("│ 몇 종류의 " + type + "운동을 하시겠습니까?");
			System.out.println("└───────────────────────────────────────────────┘");

			System.out.print("입력 : ");

			int count = scan.nextInt();
//			저장된 운동 종류보다 많은 수 입력 시.
			while (count > list.size()) {
				System.out.println("┌───────────────────────────────────────────────┐");
				System.out.println("│ 저장된 운동의 종류보다 큰 수를 입력했습니다.");
				System.out.println("│ 다시 입력해주세요.");
				System.out.println("│ 몇 종류의 " + type + "운동을 하시겠습니까?");
				System.out.println("└───────────────────────────────────────────────┘");
				System.out.print("입력 : ");
				count = scan.nextInt();
			}
//			랜덤한 운동 뽑기
			for (int i = 0; i < count; i++) {
				int ran = (int) (Math.random() * list.size());
				String temp = list.get(ran).getwName();

				if (!wList.get(tNum).contains(temp)) {
					wList.get(tNum).add(temp);
					worked = true;
				} else {
					i--;
				}
			}

			System.out.println("\n┌──────────────────────────────────────────────────");
			System.out.println("│ 현재까지의 추천 루틴\n│ ");

			for (int i = 0; i < wList.size(); i++) {
				if (wList.get(i).size() > 1) { // 부위 유무 확인
					System.out.print("│ " + wList.get(i).get(0) + " : ");
					System.out.println(wList.get(i).subList(1, wList.get(i).size()));
				}
			}
			System.out.println("│ ");
			System.out.println("└──────────────────────────────────────────────────");
		}
		if (worked) {
			System.out.println("┌──────────────────────오늘의 추천 루틴───────────────────────────");
			for (int i = 0; i < wList.size(); i++) {
				if (wList.get(i).size() > 1) { // 부위 유무 확인
					System.out.print("│ " + wList.get(i).get(0) + " : ");
					System.out.println(wList.get(i).subList(1, wList.get(i).size()));
				}
			}
			System.out.println("│ ");
//			저장
			save(wList);
			System.out.println("----------------");
		} else {
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│ 아무것도 입력하지 않으셨습니다.│");
			System.out.println("│ 추천 서비스가 종료됩니다.      │");
			System.out.println("└────────────────────────────────┘");

		}

	}

	// 루틴 저장하는 메서드
	public void save(List<List<String>> wList) {
		WorkDAO dao = new WorkDAO();
		// 루틴 저장 여부 확인
		while (true) {
			System.out.println("│ ");
			System.out.println("│ 해당 루틴을 저장하시겠습니까? [Y/N] ");
			System.out.println("└─────────────────────────────────────────────────────────────────");
			System.out.print("입력 : ");
			String answer = scan.next();

			// 루틴 저장
			if (answer.equals("Y")) {
				List<String> result = new ArrayList<String>();

				for (int i = 0; i < wList.size(); i++) {
					String temp = "";
					wList.get(i).remove(0);
					if (!wList.get(i).isEmpty()) {
						temp = "[";
						for (int j = 0; j < wList.get(i).size(); j++) {
							if (j == wList.get(i).size() - 1) {
								temp += "\"" + wList.get(i).get(j) + "\"" + "]";
							} else {
								temp += "\"" + wList.get(i).get(j) + "\"" + ",";
							}
						}
					}
					result.add(temp);
				}

				System.out.println("\n사용자의 이름을 입력해주세요.");
				System.out.print("사용자 이름 : ");
				String name = scan.next();

				dao.insertRountine(name, result);
				System.out.println("저장되었습니다.");
				System.out.println("추천 서비스가 종료됩니다.\n");
				break;
				// 루틴 저장하지 않음
			} else if (answer.equals("N")) {
				System.out.println("\n저장하지 않습니다.");
				System.out.println("추천 서비스가 종료됩니다.\n");
				break;
			} else {
				System.out.println("\n잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	// 저장한 루틴 확인하는 메서드
	public void showRecord() {
		WorkDAO dao = new WorkDAO();
		System.out.println("저장된 사용자 루틴을 조회합니다.");
		System.out.print("사용자 이름 : ");
		String name = scan.nextLine();
		List<RoutineDTO> list = dao.showRoutine(name);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("┌───────── 루틴No:" + list.get(i).getId() + " / " + list.get(i).getName() + "님 / "
					+ list.get(i).getwDate() + "─────────\n│ ");
			if (list.get(i).getShoulder().length() > 0) {
				System.out.println("│ 어깨 : " + list.get(i).getShoulder());
			}
			if (list.get(i).getChest().length() > 0) {
				System.out.println("│ 가슴 : " + list.get(i).getChest());
			}
			if (list.get(i).getLeg().length() > 0) {
				System.out.println("│ 하체 : " + list.get(i).getLeg());
			}
			if (list.get(i).getBack().length() > 0) {
				System.out.println("│  등  : " + list.get(i).getBack());
			}
			if (list.get(i).getArm().length() > 0) {
				System.out.println("│  팔  : " + list.get(i).getArm());
			}
			if (list.get(i).getwBody().length() > 0) {
				System.out.println("│ 전신 : " + list.get(i).getwBody());
			}
			System.out.println("│ \n└────────────────────────────────────────────────────");
		}
		if (list.isEmpty()) {
			System.out.println("존재하지 않는 사용자 입니다.");
		}
		System.out.println("");

	}

	public void delRecord() {
		WorkDAO dao = new WorkDAO();
		System.out.println("\n저장된 루틴을 삭제합니다.");
		System.out.print("루틴 번호 : ");
		int id = scan.nextInt();
		int result = dao.delRoutine(id);
		if (result == 1) {
			System.out.println(id + "번 루틴이 삭제되었습니다.");
		} else {
			System.out.println("존재하지 않는 루틴번호 입니다.");
		}
	}

	public void fitness() {
		int num = 0;
		String result = "";
		System.out.println("지역을 선택해주세요.");
		System.out.println("1.성동구 2.용산구 3.서초구");
		num = scan.nextInt();
		try {
			// 사이트에서 제공해주는 API 호출 URL을 넣어준다.
			URL seongUrl = new URL(
					"https://api.odcloud.kr/api/15073993/v1/uddi:164a860a-cb06-478e-b76e-d82c10a2f585?page=1&perPage=1000&serviceKey=iTIfrAVDWjD3nZqfj7sHlKJG7Vbr2CbyinnGOpwFBgJuBNM5g96ltVNISlvWzJpHnJ0YZ9YtJ425rvsyhP0yWg%3D%3D");
			URL youngUrl = new URL(
					"https://api.odcloud.kr/api/15074334/v1/uddi:950b23d1-7daf-4712-bf5d-dd2282762367?page=1&perPage=1000&serviceKey=iTIfrAVDWjD3nZqfj7sHlKJG7Vbr2CbyinnGOpwFBgJuBNM5g96ltVNISlvWzJpHnJ0YZ9YtJ425rvsyhP0yWg%3D%3D");
			URL seoUrl = new URL(
					"https://api.odcloud.kr/api/15074392/v1/uddi:e4e6b827-ddca-423b-b25d-ee11bfd81944?page=1&perPage=1000&serviceKey=iTIfrAVDWjD3nZqfj7sHlKJG7Vbr2CbyinnGOpwFBgJuBNM5g96ltVNISlvWzJpHnJ0YZ9YtJ425rvsyhP0yWg%3D%3D");
			// 빠르게 읽어오기 위해 BufferedReader 클래스를 사용
			// URL에서 제공되는 메소드를 사용하기 위해 InputStreamReader 속성을 사용
			while (true) {
				if (num == 1) {
					BufferedReader bf = new BufferedReader(new InputStreamReader(seongUrl.openStream(), "UTF-8"));

					result = bf.readLine();

					JSONParser parser = new JSONParser();
					JSONObject object = (JSONObject) parser.parse(result);
					JSONArray data = (JSONArray) object.get("data");

					// JSONObject data = (JSONObject)object.get("data");
					// System.out.println("도로명주소 : " + data);

					System.out.printf("%-50s %-10s %n%n", "||상호명||", "||주소||");
					System.out.println(
							"================================================================================");
					System.out.println(
							"================================================================================");
					System.out.println(
							"================================================================================");
					for (int i = 0; i < data.size(); i++) {
						JSONObject jsonObject = (JSONObject) data.get(i);
						String address = (String) jsonObject.get("도로명주소");
						String name = (String) jsonObject.get("상호");
						System.out.println(name + "\t\t\t\t" + address);
					}
					break;
				} else if (num == 2) {
					BufferedReader bf = new BufferedReader(new InputStreamReader(youngUrl.openStream(), "UTF-8"));

					result = bf.readLine();

					JSONParser parser = new JSONParser();
					JSONObject object = (JSONObject) parser.parse(result);
					JSONArray data = (JSONArray) object.get("data");

					// JSONObject data = (JSONObject)object.get("data");
					// System.out.println("도로명주소 : " + data);

					System.out.printf("%-30s %10s %n%n", "||상호명||", "||주소||");
					System.out.println(
							"================================================================================");
					System.out.println(
							"================================================================================");
					System.out.println(
							"================================================================================");
					for (int i = 0; i < data.size(); i++) {
						JSONObject jsonObject = (JSONObject) data.get(i);
						String address = (String) jsonObject.get("상호명");
						String name = (String) jsonObject.get("소재지(지번)");
						System.out.println(address + "\t\t\t\t" + name);
					}
					break;
				} else if (num == 3) {
					BufferedReader bf = new BufferedReader(new InputStreamReader(seoUrl.openStream(), "UTF-8"));

					result = bf.readLine();

					JSONParser parser = new JSONParser();
					JSONObject object = (JSONObject) parser.parse(result);
					JSONArray data = (JSONArray) object.get("data");

					// JSONObject data = (JSONObject)object.get("data");
					// System.out.println("도로명주소 : " + data);

					System.out.printf("%-30s %10s %n%n", "||상호명||", "||주소||");
					System.out.println(
							"================================================================================");
					System.out.println(
							"================================================================================");
					System.out.println(
							"================================================================================");
					for (int i = 0; i < data.size(); i++) {
						JSONObject jsonObject = (JSONObject) data.get(i);
						String address = (String) jsonObject.get("상호");
						String name = (String) jsonObject.get("시설주소(도로명)");
						System.out.printf("%-30s %s %n", address, name);
					}
					break;
				} else {
					System.out.println("잘못입력했습니다.");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
