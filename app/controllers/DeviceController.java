package controllers;

import models.Customer;
import models.Device;
import models.DeviceDetail;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

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


}
