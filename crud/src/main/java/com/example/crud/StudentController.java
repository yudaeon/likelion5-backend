package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    // StudentService 를 Controller 에서 사용
    private final StudentService studentService;

    public StudentController(
            StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("email")
            String email
    ) {
        StudentDto studentDto
                = studentService.createStudent(name, email);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute(
                "studentList",
                studentService.readStudentAll()
        );
        return "home";
    }

    @GetMapping("/{id}")
    public String read(
        @PathVariable("id")
        Long id,
        Model model
    ){
        System.out.println(id);
        model.addAttribute("student",
            studentService.readStudent(id)
        );
        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(
        @PathVariable("id")
        Long id,
        Model model
    ){
        model.addAttribute("student", studentService.readStudent(id));
        return "update";
    }

    @GetMapping("/{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("name")
            String name,
            @RequestParam("email")
            String email
    ){
        studentService.updateStudent(id, name, email);
        return String.format("redirect:/%s", id);
    }

    @GetMapping("/{id}/delete-view")  //getmapping
    public String deleteView(//delete-view 만들기
        @PathVariable("id")
        Long id, Model model
    ){ //long id
        StudentDto studentdto = studentService.readStudent(id); //student dto
        model.addAttribute("student", studentdto);
        return "delete"; //return
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id
    ){
        studentService.deleteStudent(id);
        //update때는 데이터가 남아있지만
        //delete는 돌아갈 상세보기가 없다.
        // 그래서 홈을 돌아간다.
        return "redirect:/home"; //home으로 리턴
    }
}
