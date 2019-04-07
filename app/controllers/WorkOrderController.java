package controllers;

import com.sun.xml.internal.bind.v2.TODO;
import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

import static play.mvc.Results.ok;

public class WorkOrderController
{

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public WorkOrderController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getWorkOrder(int workOrderId)
    {
        TypedQuery<WorkOrderDetail> query =
                db.em().createQuery("SELECT NEW WorkOrderDetail( wo.workOrderId, e.firstName, e.lastName, d.deviceId, " +
                        "m.modelName, c.firstName, c.lastName, c.phoneNumber, c.email, wo.dateTime) " +
                        "FROM WorkOrder wo " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId  " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "WHERE workOrderId = :workOrderId ", WorkOrderDetail.class);
        query.setParameter("workOrderId", workOrderId);
        WorkOrderDetail workOrderDetail = query.getSingleResult();


        TypedQuery<WorkOrderItemsDetail> queryItems =
                db.em().createQuery("SELECT NEW WorkOrderItemsDetail(woi.workOrderItemsId, i.itemName, woi.quantity, " +
                        "wo.workOrderId, woi.discount, i.retailPrice, ((i.retailPrice * woi.quantity)) AS saleTotal) " +
                        "FROM WorkOrder wo " +
                        "JOIN WorkOrderItems woi ON wo.workOrderId = woi.workOrderId " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "JOIN Item i ON woi.itemId = i.itemId " +
                        "WHERE wo.workOrderId = :workOrderId ", WorkOrderItemsDetail.class);
        queryItems.setParameter("workOrderId", workOrderId);
        List<WorkOrderItemsDetail> workOrderItemsDetail = queryItems.getResultList();

        BigDecimal saleTotal = new BigDecimal(0.0);
        for (WorkOrderItemsDetail item : workOrderItemsDetail)
        {
            saleTotal = saleTotal.add(item.getSaleTotal());
        }

        TypedQuery<Item> itemQuery = db.em().createQuery("SELECT i FROM Item i", Item.class);
        List<Item> items = itemQuery.getResultList();

        return ok(views.html.workorder.render(workOrderDetail, workOrderItemsDetail, saleTotal, items));
    }

    @Transactional
    public Result postWorkOrder(int workOrderId)
    {
        WorkOrderItems workOrderItems = new WorkOrderItems();

        DynamicForm form = formFactory.form().bindFromRequest();

        int itemId = Integer.parseInt(form.get("itemId"));
        int quantity = Integer.parseInt(form.get("quantity"));

        workOrderItems.setItemId(itemId);
        workOrderItems.setQuantity(quantity);
        workOrderItems.setWorkOrderId(workOrderId);
        db.em().persist(workOrderItems);


        TypedQuery<WorkOrderDetail> query =
                db.em().createQuery("SELECT NEW WorkOrderDetail( wo.workOrderId, e.firstName, e.lastName, d.deviceId, " +
                        "m.modelName, c.firstName, c.lastName, c.phoneNumber, c.email, wo.dateTime) " +
                        "FROM WorkOrder wo " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId  " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "WHERE workOrderId = :workOrderId ", WorkOrderDetail.class);
        query.setParameter("workOrderId", workOrderId);
        WorkOrderDetail workOrderDetail = query.getSingleResult();


        TypedQuery<WorkOrderItemsDetail> queryItems =
                db.em().createQuery("SELECT NEW WorkOrderItemsDetail(woi.workOrderItemsId, i.itemName, woi.quantity, " +
                        "wo.workOrderId, woi.discount, i.retailPrice, ((i.retailPrice * woi.quantity)) AS saleTotal) " +
                        "FROM WorkOrder wo " +
                        "JOIN WorkOrderItems woi ON wo.workOrderId = woi.workOrderId " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "JOIN Item i ON woi.itemId = i.itemId " +
                        "WHERE wo.workOrderId = :workOrderId ", WorkOrderItemsDetail.class);
        queryItems.setParameter("workOrderId", workOrderId);
        List<WorkOrderItemsDetail> workOrderItemsDetail = queryItems.getResultList();

        BigDecimal saleTotal = new BigDecimal(0.0);
        for (WorkOrderItemsDetail item : workOrderItemsDetail)
        {
            saleTotal = saleTotal.add(item.getSaleTotal());
        }

        TypedQuery<Item> itemQuery = db.em().createQuery("SELECT i FROM Item i", Item.class);
        List<Item> items = itemQuery.getResultList();

        return ok(views.html.workorder.render(workOrderDetail, workOrderItemsDetail, saleTotal, items));
    }

    @Transactional
    public Result postWorkOrderDeleteItem(int workOrderItemsId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();


        int workOrderId;

        WorkOrderItems workOrderItemsRemove = db.em().find(WorkOrderItems.class, workOrderItemsId);
        WorkOrderItems workOrderItemsAdd = new WorkOrderItems();

            db.em().remove(workOrderItemsRemove);


        workOrderId = Integer.parseInt(form.get("workOrderId"));

        TypedQuery<WorkOrderDetail> query =
                db.em().createQuery("SELECT NEW WorkOrderDetail( wo.workOrderId, e.firstName, e.lastName, d.deviceId, " +
                        "m.modelName, c.firstName, c.lastName, c.phoneNumber, c.email, wo.dateTime) " +
                        "FROM WorkOrder wo " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId  " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "WHERE workOrderId = :workOrderId ", WorkOrderDetail.class);
        query.setParameter("workOrderId", workOrderId);
        WorkOrderDetail workOrderDetail = query.getSingleResult();


        TypedQuery<WorkOrderItemsDetail> queryItems =
                db.em().createQuery("SELECT NEW WorkOrderItemsDetail(woi.workOrderItemsId, i.itemName, woi.quantity, " +
                        "wo.workOrderId, woi.discount, i.retailPrice, ((i.retailPrice * woi.quantity)) AS saleTotal) " +
                        "FROM WorkOrder wo " +
                        "JOIN WorkOrderItems woi ON wo.workOrderId = woi.workOrderId " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "JOIN Item i ON woi.itemId = i.itemId " +
                        "WHERE wo.workOrderId = :workOrderId ", WorkOrderItemsDetail.class);
        queryItems.setParameter("workOrderId", workOrderId);
        List<WorkOrderItemsDetail> workOrderItemsDetail = queryItems.getResultList();

        BigDecimal saleTotal = new BigDecimal(0.0);
        for (WorkOrderItemsDetail item : workOrderItemsDetail)
        {
            saleTotal = saleTotal.add(item.getSaleTotal());
        }

        TypedQuery<Item> itemQuery = db.em().createQuery("SELECT i FROM Item i", Item.class);
        List<Item> items = itemQuery.getResultList();

        return ok(views.html.workorder.render(workOrderDetail, workOrderItemsDetail, saleTotal, items));
    }


    @Transactional(readOnly = true)
    public Result getWorkOrderSearch()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortColumn = form.get("sortColumn");

        if (sortColumn == null)
        {
            sortColumn = "";
        }

        String sortOrder = "";


        switch (sortColumn)
        {
            case "workOrderIdASC":
                sortOrder = "wo.workOrderId ASC, c.lastName";
                break;
            case "workOrderIdDESC":
                sortOrder = "wo.workOrderId DESC, c.lastName";
                break;
            case "dateTimeASC":
                sortOrder = "wo.dateTime ASC, c.lastName";
                break;
            case "dateTimeDESC":
                sortOrder = "wo.dateTime DESC, c.lastName";
                break;
            case "customerNameASC":
                sortOrder = "c.firstName ASC, c.lastName";
                break;
            case "customerNameDESC":
                sortOrder = "c.firstName DESC, c.lastName";
                break;
            case "customerPhoneNumberASC":
                sortOrder = "c.phoneNumber ASC, wo.dateTime";
                break;
            case "customerPhoneNumberDESC":
                sortOrder = "c.phoneNumber DESC, wo.dateTime";
                break;
            case "employeeNameASC":
                sortOrder = "e.lastName ASC, e.firstName";
                break;
            case "employeeNameDESC":
                sortOrder = "e.lastName DESC, e.firstName";
                break;
            default:
                sortOrder = "workOrderId";
                break;
        }


        String name = form.get("name");

        if (name == null)
        {
            name = "";
        }

        name = "%" + name + "%";


        TypedQuery<WorkOrderDetail> query =
                db.em().createQuery("SELECT NEW WorkOrderDetail( wo.workOrderId, e.firstName, e.lastName, d.deviceId, " +
                        "m.modelName, c.firstName, c.lastName, c.phoneNumber, c.email, wo.dateTime) " +
                        "FROM WorkOrder wo " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId  " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "WHERE c.firstName LIKE :name OR wo.workOrderId LIKE :name OR c.phoneNumber LIKE :name OR  " +
                        "wo.dateTime LIKE :name " +
                        "ORDER BY " + sortOrder, WorkOrderDetail.class);
        query.setParameter("name", name);
        List<WorkOrderDetail> workOrderSearch = query.getResultList();

        return ok(views.html.workordersearch.render(workOrderSearch));
    }
//TODO add a create WO post and get
    @Transactional
    public Result getWorkOrderCreate()
    {
        return ok(views.html.createworkorder.render());
    }

    @Transactional
    public Result getWorkOrderAdd()
    {
        return ok(views.html.workorderadd.render());
    }

    @Transactional
    public Result getExistingCustomer()
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


        return ok(views.html.existingcustomer.render(customersearch));
    }

    public Result getCustomerAddToWorkOrder()
    {
        return ok(views.html.customeraddtoworkorder.render());
    }
    @Transactional
    public Result postCustomerAddToWorkOrder()
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

        return ok(views.html.createworkorder.render());
    }

    public Result getAddOrExistingDevice()
    {
        return ok(views.html.addorexistingdevice.render());
    }

    @Transactional
    public Result getDeviceAddToWorkOrder()
    {


        TypedQuery<Model> modelQuery = db.em().createQuery("SELECT c FROM Model c ORDER BY modelName", Model.class);
        List<Model> models = modelQuery.getResultList();

        return ok(views.html.deviceaddtoworkorder.render(models));
    }

    @Transactional
    public Result postDeviceAddToWorkOrder()
    {

        Device device = new Device();

        DynamicForm form = formFactory.form().bindFromRequest();


        int modelId = Integer.parseInt(form.get("modelId"));
        String IMEI = form.get("IMEI");


        device.setModelId(modelId);
        device.setIMEI(IMEI);
        db.em().persist(device);
        return ok(views.html.createworkorder.render());
    }

    @Transactional
    public Result getExistingDevice()
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
            case "deviceIdASC":
                sortOrder = "deviceId ASC, IMEI";
                break;
            case "deviceIdDESC":
                sortOrder = "deviceId DESC, IMEI";
                break;
            case "IMEIASC":
                sortOrder = "IMEI ASC, deviceId";
                break;
            case "IMEIDESC":
                sortOrder = "IMEI DESC, deviceId";
                break;
            case "modelNameASC":
                sortOrder = "modelName ASC, IMEI";
                break;
            case "modelNameDESC":
                sortOrder = "modelName DESC, IMEI";
                break;
            default:
                sortOrder = "d.deviceId";
                break;
        }


        String name = form.get("name");

        if (name == null)
        {
            name = "";
        }

        name = "%" + name + "%";


        TypedQuery<DeviceDetail> query =
                db.em().createQuery("SELECT NEW DeviceDetail(d.deviceId, d.IMEI, m.modelName) " +
                                "FROM Device d  " +
                                "JOIN Model m ON d.modelId = m.modelId " +
                                "WHERE d.IMEI LIKE :name OR m.modelName LIKE :name " +
                                "ORDER BY " + sortOrder
                        , DeviceDetail.class);
        Logger.debug(sortOrder);
        query.setParameter("name", name);
        List<DeviceDetail> devicesearch = query.getResultList();


        return ok(views.html.existingdevice.render(devicesearch));
    }

}
