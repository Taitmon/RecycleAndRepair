package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeviceDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;
    private String IMEI;
    private String modelName;

    public DeviceDetail(int deviceId, String IMEI, String modelName)
    {
        this.deviceId = deviceId;
        this.IMEI = IMEI;
        this.modelName = modelName;
    }

    public int getDeviceId()
    {
        return deviceId;
    }

    public String getIMEI()
    {
        return IMEI;
    }

    public String getModelName()
    {
        return modelName;
    }
}
