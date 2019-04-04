package controllers;

import models.*;
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
        for ( WorkOrderItemsDetail item : workOrderItemsDetail)
        {
            saleTotal = saleTotal.add(item.getSaleTotal());
        }

        return ok(views.html.workorder.render(workOrderDetail, workOrderItemsDetail, saleTotal));
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


    @Transactional
    public Result getWorkOrderAdd()
    {
        return ok("test");
    }
/*
    public Result getSaleTotal()
    {
        SELECT wo.workOrderId,
            ((i.retailPrice * woi.Quantity) * count(wo.workorderId)) AS saleTotal
        FROM WorkOrder wo
        JOIN WorkOrderItems woi ON wo.workorderid = woi.WorkOrderId
        JOIN Employee e ON wo.employeeId = e.employeeId
        JOIN Device d ON wo.deviceId = d.deviceId
        JOIN Model m ON d.deviceId = m.modelId
        JOIN Customer c ON wo.customerId = c.customerId
        JOIN Item i ON woi.itemId = i.itemId
        GROUP BY wo.workorderId
    }
*/
}
