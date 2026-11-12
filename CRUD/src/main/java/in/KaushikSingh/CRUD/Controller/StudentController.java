package in.KaushikSingh.CRUD.Controller;

import in.KaushikSingh.CRUD.Dto.CreateStudentRequestDto;
import in.KaushikSingh.CRUD.Dto.CreateStudentResponseDto;
import in.KaushikSingh.CRUD.Entity.Student;
import in.KaushikSingh.CRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @RequestBody CreateStudentRequestDto createStudentRequestDto) {
        CreateStudentResponseDto studentResp = studentService.create(createStudentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentResp);
    }


    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAll() {
        List<CreateStudentResponseDto> studentResp = studentService.getStudent();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getById(@PathVariable Long id) {
        CreateStudentResponseDto studentResp = studentService.getStudentById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteById(@PathVariable Long id){
        studentService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}