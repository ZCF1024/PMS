package com.company.pms.pmsrepository.employee;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.employee.domain.Employee;
import com.company.pms.pmsrepository.employee.domain.PositionTypeEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends GenericRepository<Employee, Long> {

    List<Employee> findAllByNameLikeAndDeleted(String name, Boolean deleted);

    List<Employee> findAllByAgeBetweenAndDeleted(Integer age1, Integer age2, Boolean deleted);

    List<Employee> findAllByGenderAndDeleted(Boolean gender, Boolean deleted);

    List<Employee> findAllByPhoneAndDeleted(String phone, Boolean deleted);

    List<Employee> findAllByPositionAndDeleted(PositionTypeEnum position, Boolean deleted);

    List<Employee> findAllByNameAndPasswordAndDeleted(String name, String password, Boolean deleted);

    List<Employee> findAllByPhoneAndPasswordAndDeleted(String phone, String password, Boolean deleted);

    @Modifying
    @Query(value = "update employee set deleted=?2 where id=?1", nativeQuery = true)
    void updateDeteled(Long id, Boolean deleted);

}
