package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkOrderDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workOrderId;
    private String  employeeFirstName;
    private String  employeeLastName;
    private int     deviceId;
    private String  modelName;
    private String  customerFirstName;
    private String  customerLastName;

    public WorkOrderDetail(int workOrderId, String employeeFirstName, String employeeLastName, int deviceId, String modelName, String customerFirstName, String customerLastName)
    {
        this.workOrderId = workOrderId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.deviceId = deviceId;
        this.modelName = modelName;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

    public int getWorkOrderId()
    {
        return workOrderId;
    }

    public String getEmployeeFirstName()
    {
        return employeeFirstName;
    }

    public String getEmployeeLastName()
    {
        return employeeLastName;
    }

    public int getDeviceId()
    {
        return deviceId;
    }

    public String getModelName()
    {
        return modelName;
    }

    public String getCustomerFirstName()
    {
        return customerFirstName;
    }

    public String getCustomerLastName()
    {
        return customerLastName;
    }
}
