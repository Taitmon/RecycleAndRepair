package controllers;

import com.google.common.io.Files;
import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Http;
import play.mvc.Result;
import views.html.devicesearch;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class DeviceController extends BaseController
{


    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public DeviceController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getDevice(int deviceId)
    {
        /*TypedQuery<Device> query =
                db.em().createQuery("SELECT d FROM Device d WHERE deviceId = :deviceId", Device.class);
        query.setParameter("deviceId", deviceId);
        Device device = query.getSingleResult(); */

        TypedQuery<DeviceDetail> query =
                db.em().createQuery("SELECT NEW DeviceDetail(d.deviceId, d.IMEI, m.modelName) " +
                        "FROM Device d JOIN Model m ON d.modelId = m.modelId " +
                        "WHERE deviceId = :deviceId ", DeviceDetail.class);
        query.setParameter("deviceId", deviceId);
        DeviceDetail device = query.getSingleResult();

        return ok(views.html.device.render(device));
    }

    @Transactional
    public Result getDeviceSearch()
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


        return ok(views.html.devicesearch.render(devicesearch));
    }

    @Transactional(readOnly = true)
    public Result getDeviceEdit(int deviceId)
    {
        TypedQuery<Device> query = db.em().createQuery("SELECT d FROM Device d WHERE deviceId = :deviceId", Device.class);
        query.setParameter("deviceId", deviceId);
        Device device = query.getSingleResult();

        TypedQuery<Model> modelQuery = db.em().createQuery("SELECT m FROM Model m", Model.class);
        List<Model> models = modelQuery.getResultList();

        return ok(views.html.deviceedit.render(device, models));
    }


    @Transactional
    public Result postDeviceEdit(int deviceId)
    {
        TypedQuery<Device> query = db.em().createQuery("SELECT d FROM Device d WHERE deviceId = :deviceId", Device.class);
        query.setParameter("deviceId", deviceId);
        Device device = query.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String IMEI = form.get("IMEI");
        String modelName = form.get("modelName");


        return ok("saved");
    }

    @Transactional
    public Result getDeviceAdd()
    {


        TypedQuery<Model> modelQuery = db.em().createQuery("SELECT c FROM Model c ORDER BY modelName", Model.class);
        List<Model> models = modelQuery.getResultList();

        return ok(views.html.deviceadd.render(models));
    }

    @Transactional
    public Result postDeviceAdd()
    {

        Device device = new Device();

        DynamicForm form = formFactory.form().bindFromRequest();


        int modelId = Integer.parseInt(form.get("modelId"));
        String IMEI = form.get("IMEI");



        device.setModelId(modelId);
        device.setIMEI(IMEI);
        db.em().persist(device);



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

        return ok(views.html.devicesearch.render(devicesearch));
    }



}
