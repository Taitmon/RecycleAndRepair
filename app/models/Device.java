package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;
    private String IMEI;
    private int modelId;

    public int getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(int deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getIMEI()
    {
        return IMEI;
    }

    public void setIMEI(String IMEI)
    {
        this.IMEI = IMEI;
    }

    public int getModelId()
    {
        return modelId;
    }

    public void setModelId(int modelId)
    {
        this.modelId = modelId;
    }
}
