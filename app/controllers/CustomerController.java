package controllers;

import models.Customer;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import java.util.List;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class CustomerController extends BaseController
{
    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public CustomerController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }




    @Transactional(readOnly = true)
    public Result getCustomer()
    {
        Result result = redirect("/login");
        if (isLoggedIn())
        {
            String sql = "SELECT t FROM Test t";
            TypedQuery query = db.em().createQuery(sql, Customer.class);
            List<Customer> customers = query.getResultList();

            result = ok(views.html.test.render("Rows: " + customers.size()));
        }
        return  result;
    }

    @Transactional
    public Result postCustomer()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String CustomerName = form.get("test");
        Customer customer = new Customer();
        customer.setCustomerName(CustomerName);
        db.em().persist(customer);

        return redirect("/testdb");
    }

}
