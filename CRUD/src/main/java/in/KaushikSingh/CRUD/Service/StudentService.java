package in.KaushikSingh.CRUD.Service;

import in.KaushikSingh.CRUD.Dto.CreateStudentRequestDto;
import in.KaushikSingh.CRUD.Dto.CreateStudentResponseDto;
import in.KaushikSingh.CRUD.Entity.Student;
import in.KaushikSingh.CRUD.ExceptionHandling.DuplicateException;
import in.KaushikSingh.CRUD.ExceptionHandling.ResourceNotFoundException;
import in.KaushikSingh.CRUD.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    //Post Method.
    public CreateStudentResponseDto create(
            CreateStudentRequestDto createStudentRequestDto){
        Student studentResp = mapToEntity(createStudentRequestDto);

        if(emailExists(studentResp)){
            throw new DuplicateException(
                    "Student with email "+ studentResp.getEmail() + " is already exists");
        }

        Student studentResponse = studentRepository.save(studentResp);
        return mapToDto(studentResponse);
    }


    // Get All Students Method
    public List<CreateStudentResponseDto> getStudent() {
        List<Student> studentResp = studentRepository
                .findAll();
        return studentResp.stream()
                .map(this::mapToDto)
                .toList();
    }

    //Get By id.
    public CreateStudentResponseDto getStudentById(Long id){
        Student studentResp = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id "+ id +" not found"));

        return mapToDto(studentResp);

    }

    public void delete(Long id){
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id "+ id +" not found"));

        studentRepository.delete(studentToBeDeleted);
    }

    private Student mapToEntity
            (CreateStudentRequestDto createStudentRequestDto){
        Student student = new Student();

        student.setName(createStudentRequestDto.getName());
        student.setAge(createStudentRequestDto.getAge());
        student.setEmail(createStudentRequestDto.getEmail());
        student.setRollNo(createStudentRequestDto.getRollNo());

        return student;
    }

    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto studentResp = new CreateStudentResponseDto();

        studentResp.setId(student.getId());
        studentResp.setName(student.getName());
        studentResp.setEmail(student.getEmail());

        return studentResp;
    }

    private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }
}
