package controllers;

import com.google.common.io.Files;
import models.Customer;

import models.CustomerDetail;
import models.Employee;
import models.Item;
import play.Logger;
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
public Result getCustomer(int customerId)
{
    TypedQuery<Customer> query = db.em().createQuery("SELECT c FROM Customer c WHERE customerId = :customerId", Customer.class);
    query.setParameter("customerId", customerId);
    Customer customer = query.getSingleResult();


    return ok(views.html.customer.render(customer));
}

    @Transactional(readOnly = true)
    public Result getCustomerEdit(int customerId)
    {
        TypedQuery<Customer> query = db.em().createQuery("SELECT c FROM Customer c WHERE customerId = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        Customer customer = query.getSingleResult();

        return ok(views.html.customeredit.render(customer));
    }


    @Transactional
    public Result postCustomerEdit(int customerId)
    {
        TypedQuery<Customer> query = db.em().createQuery("SELECT c FROM Customer c WHERE customerId = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        Customer item = query.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        String phoneNumber = form.get("phoneNumber");
        String email = form.get("email");


        //edit attributes
        item.setFirstName(firstName);
        item.setLastName(lastName);
        item.setPhoneNumber(phoneNumber);
        item.setEmail(email);
        db.em().persist(item);

        return ok("saved");
    }

    @Transactional(readOnly=true)
    public Result getCustomerSearch()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortColumn = form.get("sortColumn");

        if (sortColumn == null)
        {
            sortColumn = "";
        }

        String sortOrder = "";

Logger.debug(sortColumn);
        switch (sortColumn)
        {
            case "customerIdASC":
                sortOrder = "customerId ASC, LastName";
                break;
            case "customerIdDESC":
                sortOrder = "customerId DESC, phoneNumber";
                break;
            case "firstNameASC":
            sortOrder = "firstName ASC, lastName";
            break;
            case "firstNameDESC":
            sortOrder = "firstName DESC, lastName";
            break;
            case "lastNameASC":
                sortOrder = "lastName ASC, firstName";
                break;
            case "lastNameDESC":
                sortOrder = "lastName DESC, firstName";
                break;
            case "phoneNumberASC":
                sortOrder = "phoneNumber ASC, lastName";
                break;
            case "phoneNumberDESC":
                sortOrder = "phoneNumber DESC, lastName";
                break;
            case "emailASC":
                sortOrder = "email ASC, lastName";
                break;
            case "emailDESC":
                sortOrder = "email DESC, lastName";
                break;
            default:
                sortOrder = "customerId";
                break;
        }


        String name = form.get("name");

        if (name == null)
        {
            name = "";
        }

        name = "%" + name + "%";


        TypedQuery<CustomerDetail> query =
                db.em().createQuery("SELECT NEW CustomerDetail(customerId, firstName, lastName, phoneNumber, email) " +
                                "FROM Customer  " +
                                "WHERE firstName LIKE :name OR lastName LIKE :name " +
                                "ORDER BY " + sortOrder
                        , CustomerDetail.class);
        Logger.debug(sortOrder);
        query.setParameter("name", name);
        List<CustomerDetail> customersearch = query.getResultList();

        return ok(views.html.customersearch.render(customersearch));
    }

    public Result getCustomerAdd()
    {
        return ok(views.html.customeradd.render());
    }

    @Transactional
    public Result postCustomerAdd()
    {

        Customer customer = new Customer();

        DynamicForm form = formFactory.form().bindFromRequest();


        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        String phoneNumber = form.get("phoneNumber");
        String email = form.get("email");


        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);

        db.em().persist(customer);

        return ok("saved");
    }





}
