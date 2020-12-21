import model.dto.EmployeeDTO;
import repository.EmployeeRepository;
import repository.ReportRepository;
import repository.TaskRepository;
import service.ServiceDB;
import lombok.SneakyThrows;
import model.employee.Employee;
import model.task.Task;

import java.util.ArrayList;

// BLUE "\033[44m"
// YELLOW "\033[43m"
// RESET "\033[0m"

//TODO: get hierarchy
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Employee manager1 = Employee
                .builder()
                .name("Andriy Taran")
                .build();
        Employee tl1 = Employee
                .builder()
                .name("Volodymyr Zelensky")
                .build();
        Employee manager2 = Employee
                .builder()
                .name("Ihor Petrashko")
                .build();
        Task task1 = Task.builder()
                .title("lab")
                .description("do fooking lab")
                .employee(manager1)
                .appointing(tl1)
                .build();
        task1.switchTaskState();
        task1.switchTaskState();

        ServiceDB service = new ServiceDB();
        EmployeeRepository employeeRepository = new EmployeeRepository(service);
        TaskRepository taskRepository = new TaskRepository(service);
        ReportRepository reportRepository = new ReportRepository(service);
        employeeRepository.save(manager2);
        System.out.println(employeeRepository.get(manager2.getEmployeeId()));
        employeeRepository.save(manager1);
        //tl1.addEmployee(manager1);
        employeeRepository.save(tl1);
        System.out.println(employeeRepository.get(tl1.getEmployeeId()));
        System.out.println(employeeRepository.getEmployeeTable());
        EmployeeDTO employeeDTO = new EmployeeDTO();
//        System.out.println("\033[44m" + service.get(tl1.getClass(), tl1.getEmployeeId()) + "\033[0m");
//        tl1.setName("Petro Poroshenko");
//        session.update(tl1);
//        System.out.println("\033[43m" + session.find(tl1.getClass(), tl1.getEmployeeId()) + "\033[0m");
//        transaction.commit();
//        session.close();
    }
}
