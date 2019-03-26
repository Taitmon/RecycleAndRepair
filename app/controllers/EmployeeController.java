package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;


public class EmployeeController
{
    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public EmployeeController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }

    public Result getEmployee()
    {
        return ok(views.html.employee.render("Test Text"));
    }

    public Result postEmployee()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String test = form.get("test");
        return ok(views.html.employee.render(test));
    }
}
