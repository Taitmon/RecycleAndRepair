package controllers;

import models.Customer;
import models.WorkOrder;
import models.WorkOrderDetail;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

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
                        "m.modelName, c.firstName, c.lastName) " +
                        "FROM WorkOrder wo " +
                        "JOIN Employee e ON wo.employeeId = e.employeeId  " +
                        "JOIN Device d ON wo.deviceId = d.deviceId " +
                        "JOIN Model m ON d.deviceId = m.modelId " +
                        "JOIN Customer c ON wo.customerId = c.customerId " +
                        "WHERE workOrderId = :workOrderId ", WorkOrderDetail.class);
        query.setParameter("workOrderId", workOrderId);
        WorkOrderDetail workOrderDetail = query.getSingleResult();

        return ok(views.html.workorder.render(workOrderDetail));
    }
}
