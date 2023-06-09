package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    //복수의 studentDto를 담는 변수 만들기
    private final List<StudentDto> studentList = new ArrayList<>();

    private Long nextId = 1L;

    public StudentService() {
        createStudent("alex", "alex@gmail.com");
        createStudent("brad", "brad@gmail.com");
        createStudent("chad", "chad@gmail.com");
    }

    // 새로운 studentDto를 생성하는 메소드
    public StudentDto createStudent(String name, String email) {
        StudentDto newStudent = new StudentDto(nextId, name, email);
        nextId++;
        studentList.add(newStudent);
        return newStudent;
    }

    public List<StudentDto> readStudentAll() {
        return studentList;
    }

    //Service에서 단일 SatudentDto를 주는 메소드를 만들겁니다.
    public StudentDto readStudent(Long id) {
        for (StudentDto studentDto : studentList) {
            if (studentDto.getId().equals(id))
                return studentDto;
        }
        return null;
    }

    //어떤 학생 데이터를 갱신할 것인지
    // 그 학생으 ㅣ갱신될 데이터
    public StudentDto updateStudent(Long id, String name, String email) {
        // 하나의 StudentDto를 찾아서
        int target = -1;
        //studentList의 크기 만큼 반복
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                //인덱스 기록
                target = i;
                //반복 종료
                break;
            }
        }
        if (target != -1) {
            //name과 email을 바꿔주자
            studentList.get(target).setName(name);
            studentList.get(target).setEmail(email);
            return studentList.get(target);
        }
        //대상을 못찾았다면
        else return null;
    }

    //삭제 성공, 실패를 확인하기 위해 boolean 사용
    public boolean deleteStudent(Long id){
        int target = -1;
        // 학생 리스트를 살펴보며
        for (int i = 0; i < studentList.size(); i++) {
            // 대상을 선정한다.
            if (studentList.get(i).getId().equals(id)){
                target = i;
                break;
            }
        }
        //만약 타켓을 찾았다면, 타겟제거
        if (target!=-1){
            studentList.remove(target);
            return true; //성공
        }
        //아니라면 실패
        else return false;
    }
}
