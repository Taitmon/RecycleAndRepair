package controllers;

import com.google.common.io.Files;
import models.Employee;
import models.EmployeeDetail;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class EmployeeController extends BaseController
{
    private JPAApi db;

    private FormFactory formFactory;

    @Inject
    public EmployeeController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getEmployee(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();


        return ok(views.html.employee.render(employee));
    }

    @Transactional(readOnly = true)
    public Result getPicture(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        return ok(employee.getPicture());
    }

    @Transactional(readOnly = true)
    public Result getEmployeeEdit(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        return ok(views.html.employeeedit.render(employee));
    }


    @Transactional
    public Result postEmployeeEdit(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        String phoneNumber = form.get("phoneNumber");
        String email = form.get("email");

        //edit attributes
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        db.em().persist(employee);

        //Picture
        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();

        byte[] picture;

        try
        {
            picture = Files.toByteArray(file);

            if (picture != null && picture.length > 0)
            {
                employee.setPicture(picture);
            }
        } catch (Exception e)
        {
            picture = null;
        }

        return redirect("/employeesearch");
    }

    public Result getEmployeeAdd()
    {
        return ok(views.html.employeeadd.render());
    }

    @Transactional
    public Result postEmployeeAdd()
    {

        Employee employee = new Employee();

        DynamicForm form = formFactory.form().bindFromRequest();


        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        String phoneNumber = form.get("phoneNumber");
        String email = form.get("email");


        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);

        db.em().persist(employee);

        return redirect("/employeesearch");
    }

    @Transactional(readOnly = true)
    public Result getEmployees()
    {
        Result result = redirect("/login");
        if (isLoggedIn())
        {


            TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e ORDER BY lastName, firstName, employeeId", Employee.class);
            List<Employee> employees = query.getResultList();


            result =  ok(views.html.employees.render(employees));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Result getEmployeeSearch()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");

        if (name == null)
        {
            name = "";
        }

        name = "%" + name + "%";


        TypedQuery<EmployeeDetail> query =
                db.em().createQuery("SELECT NEW EmployeeDetail(employeeId, firstName, lastName, phoneNumber, email) " +
                        "FROM Employee " +
                        "WHERE lastName LIKE :name OR firstName LIKE :name " +
                        "ORDER BY lastName, firstName, employeeId", EmployeeDetail.class);
        query.setParameter("name", name);
        List<EmployeeDetail> employees = query.getResultList();

        return ok(views.html.employeesearch.render(employees));
    }

}
