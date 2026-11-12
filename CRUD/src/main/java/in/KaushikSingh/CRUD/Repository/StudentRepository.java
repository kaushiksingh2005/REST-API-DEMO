package in.KaushikSingh.CRUD.Repository;


import in.KaushikSingh.CRUD.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByEmail(String emailId);
}
