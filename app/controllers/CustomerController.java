package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class CustomerController
{
    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public CustomerController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }

    public Result getCustomer()
    {
        return ok(views.html.customer.render("Test Text"));
    }

    public Result postCustomer()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String test = form.get("test");
        return ok(views.html.customer.render(test));
    }

}
