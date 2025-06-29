package io.member;

import io.member.impl.DataMemberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MemberConsoleMain {

//    private static final MemberRepository repository = new MemoryMemberRepository();
//    private static final MemberRepository repository = new FileMemberRepository();
    private static final MemberRepository repository = new DataMemberRepository();


    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        while(true){
            System.out.println("1. 회원 등록 | 2. 회원 목록 조회 | 3. 종료");
            System.out.print("선택 : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    //회원 등록
                    registerMember(scanner);
                    break;
                case 2 :
                    //회원 목록 조회
                    displayMembers();
                    break;
                case 3 :
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요");
            }
        }

    }

    private static void registerMember(Scanner scanner) {
        System.out.print("ID 입력 : ");
        String id = scanner.nextLine();

        System.out.print("Name 입력 : ");
        String name = scanner.nextLine();

        System.out.print("Age 입력 : ");
        Integer age = scanner.nextInt();
        scanner.nextLine(); //newLine 제거
        Member newMember  = new Member(id,name,age);
        repository.add(newMember);
        System.out.println("회원이 성공적으로 등록 되었습니다");
    }

    private static void displayMembers() {
        if(repository.findAll().isEmpty()){
            System.out.println("조회된 목록이 없습니다.");
            return;
        }
        System.out.println("회원 목록 : ");
        List<Member> members = repository.findAll();
        for (Member member : members) {
            System.out.println(member);
        }
    }

}
