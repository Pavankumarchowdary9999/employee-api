package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> findById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(idGenerator.incrementAndGet());
        }

        employees.removeIf(existing ->
                existing.getId().equals(employee.getId()));

        employees.add(employee);

        return employee;
    }

    public void deleteById(Long id) {
        employees.removeIf(employee ->
                employee.getId().equals(id));
    }

    public boolean existsById(Long id) {
        return employees.stream()
                .anyMatch(employee ->
                        employee.getId().equals(id));
    }
}
