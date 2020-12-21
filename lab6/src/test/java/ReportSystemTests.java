import exceptions.InvalidAttemptSwitchingTaskStateException;
import model.dto.EmployeeDTO;
import model.employee.Employee;
import model.manager.EmployeeManager;
import model.manager.ReportManager;
import model.manager.TaskManager;
import model.mapper.EmployeeMapper;
import model.report.Report;
import model.task.Task;
import org.junit.Assert;
import org.junit.Test;
import repository.EmployeeRepository;
import repository.ReportRepository;
import repository.TaskRepository;
import service.ServiceDB;

import java.util.ArrayList;
import java.util.List;

public class ReportSystemTests {
    @Test
    public void test1() {
        ServiceDB serviceDB = new ServiceDB();
        TaskRepository taskRepository = new TaskRepository(serviceDB);
        EmployeeRepository employeeRepository = new EmployeeRepository(serviceDB);
        ReportRepository reportRepository = new ReportRepository(serviceDB);
        TaskManager taskManager = new TaskManager(taskRepository, employeeRepository);
        ReportManager reportManager =
                new ReportManager(reportRepository, taskManager, employeeRepository, taskRepository);
        EmployeeManager employeeManager =
                new EmployeeManager(employeeRepository);
        Employee manager1 = new Employee("Andriy Taran");
        Employee tl1 = new Employee("Volodymyr Zelensky");
        Employee manager2 = new Employee("Ihor Petrashko");
        Task task1 = Task.builder()
                .title("lab")
                .description("do fooking lab")
                .employee(manager1)
                .appointing(tl1)
                .build();
        task1.switchTaskState();
        task1.switchTaskState();
        manager1.setReportDraft(new Report());
        manager2.setReportDraft(new Report());
        tl1.setReportDraft(new Report());
        manager1 = employeeManager.addEmployee(EmployeeMapper.convertToDTO(manager1));
        manager2 = employeeManager.addEmployee(EmployeeMapper.convertToDTO(manager2));
        tl1 = employeeManager.addEmployee(EmployeeMapper.convertToDTO(tl1));
        List<EmployeeDTO> expected = new ArrayList<>();
        expected.add(EmployeeMapper.convertToDTO(employeeRepository.get(manager1.getEmployeeId())));
        expected.add(EmployeeMapper.convertToDTO(employeeManager.getById(manager2.getEmployeeId())));
        expected.add(EmployeeMapper.convertToDTO(employeeManager.getById(tl1.getEmployeeId())));;
        Assert.assertEquals(expected, employeeManager.getHierarchy());
        serviceDB.commit();
        serviceDB.closeSession();
    }

    @Test(expected = InvalidAttemptSwitchingTaskStateException.class)
    public void test2() {
        Employee manager1 = new Employee("Andriy Taran");
        Employee tl1 = new Employee("Volodymyr Zelensky");
        Task task1 = Task.builder()
                .title("lab")
                .description("do fooking lab")
                .employee(manager1)
                .appointing(tl1)
                .build();
        task1.switchTaskState();
        task1.switchTaskState();
        task1.switchTaskState();
    }

    @Test
    public void test3() {
        ServiceDB serviceDB = new ServiceDB();
        TaskRepository taskRepository = new TaskRepository(serviceDB);
        EmployeeRepository employeeRepository = new EmployeeRepository(serviceDB);
        ReportRepository reportRepository = new ReportRepository(serviceDB);
        TaskManager taskManager = new TaskManager(taskRepository, employeeRepository);
        ReportManager reportManager =
                new ReportManager(reportRepository, taskManager, employeeRepository, taskRepository);
        EmployeeManager employeeManager =
                new EmployeeManager(employeeRepository);
        Employee manager1 = new Employee("Andriy Taran");
        Employee tl1 = new Employee("Volodymyr Zelensky");
        Employee manager2 = new Employee("Ihor Petrashko");
        Task task1 = Task.builder()
                .title("lab")
                .description("do fooking lab")
                .employee(manager1)
                .appointing(tl1)
                .build();
        manager1 = employeeManager.addEmployee(EmployeeMapper.convertToDTO(manager1));
        manager2 = employeeManager.addEmployee(EmployeeMapper.convertToDTO(manager2));
        tl1 = employeeManager.addEmployee(EmployeeMapper.convertToDTO(tl1));
        employeeManager.changeHead(EmployeeMapper.convertToDTO(manager1)
                , EmployeeMapper.convertToDTO(tl1));
        Assert.assertEquals(manager1.getHead(),
                employeeManager.getEmployeeRepository()
                        .get(manager1.getEmployeeId()).getHead());
    }
}
