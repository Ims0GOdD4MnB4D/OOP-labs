import util.HibernateUtil;
import lombok.SneakyThrows;
import model.employee.Employee;
import model.task.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

// BLUE "\033[44m"
// YELLOW "\033[43m"
// RESET "\033[0m"

//TODO: get hierarchy
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Employee tl1 = new Employee("Volodymyr Zelensky");
        Employee manager1 = new Employee("Andriy Taran");
        Employee manager2 = new Employee("Ihor Petrashko");
        Task task1 = Task.builder()
                .title("lab")
                .description("do fooking lab")
                .employee(manager1)
                .appointing(tl1)
                .build();
        task1.switchTaskState();
        task1.switchTaskState();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        session.save(manager1);
        tl1.addEmployee(manager1);
        session.save(tl1);
        session.save(task1);
        System.out.println("\033[44m" + session.find(tl1.getClass(), tl1.getEmployeeId()) + "\033[0m");
        tl1.setName("Petro Poroshenko");
        session.update(tl1);
        System.out.println("\033[43m" + session.find(tl1.getClass(), tl1.getEmployeeId()) + "\033[0m");
        transaction.commit();
        session.close();
    }
}
