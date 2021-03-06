package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class WorkOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workOrderId;
    private int employeeId;
    private int deviceId;
    private int customerId;
    private LocalDateTime dateTime;



    public int getWorkOrderId()
    {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId)
    {
        this.workOrderId = workOrderId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public int getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(int deviceId)
    {
        this.deviceId = deviceId;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }
}
