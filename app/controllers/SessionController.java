package controllers;

import models.Employee;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;


import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class SessionController extends BaseController
{
    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public SessionController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }

    public Result getLogin()
    {
        return ok(views.html.login.render(""));
    }

    public Result postLogout()
    {
        logout();
        return redirect("/login");
    }

    @Transactional(readOnly = true)
    public Result postLogin()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String username = form.get("username");
                                    //TODO change to email
        String sql = "SELECT e FROM Employee e WHERE email = :username";

        TypedQuery<Employee> query = db.em().createQuery(sql, Employee.class);
        query.setParameter("username", username);

        List<Employee> employees = query.getResultList();


        Result result;

        if (employees.size() == 1)
        {
            Employee employee = employees.get(0);
            result = redirect("/home");
            login(employee);
        }
        else
        {
            logout();
            String message = "Incorrect username or password. Please try again.";
            result = ok(views.html.login.render(message));
        }

        return result;
    }
}
