package controllers;

import models.Customer;
import models.Test;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import java.util.List;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

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

    @Transactional(readOnly = true)
    public Result getCustomerDb()
    {
        String sql = "SELECT t FROM Test t";
        TypedQuery query = db.em().createQuery(sql, Customer.class);
        List<Customer> customers = query.getResultList();

        return ok(views.html.test.render("Rows: " + customers.size()));
    }

    @Transactional
    public Result postCustomerDb()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String CustomerName = form.get("test");
        Customer customer = new Customer();
        customer.setCustomerName(CustomerName);
        db.em().persist(customer);

        return redirect("/testdb");
    }

}
