package models;

import controllers.WorkOrderController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String  customerPhoneNumber;
    private String  customerEmail;
    private LocalDateTime dateTime;
    private String  customerName;
    private String  employeeName;
    private String deviceIMEI;


    private BigDecimal saleTotal;

    public WorkOrderDetail(int workOrderId, String employeeFirstName, String employeeLastName, int deviceId, String modelName, String customerFirstName, String customerLastName, String customerPhoneNumber, String customerEmail, LocalDateTime dateTime, String deviceIMEI)
    {
        this.workOrderId = workOrderId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.deviceId = deviceId;
        this.modelName = modelName;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.dateTime = dateTime;
        this.deviceIMEI = deviceIMEI;
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

    public String getCustomerPhoneNumber()
    {
        return customerPhoneNumber;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public String getCustomerName()
    {
        customerName = customerFirstName + " " + customerLastName;
        return customerName;
    }

    public String getEmployeeName()
    {
        employeeName = employeeFirstName + " " + employeeLastName;
        return employeeName;
    }

    public BigDecimal getSaleTotal()
    {

        return saleTotal;
    }

    public String getDeviceIMEI()
    {
        return deviceIMEI;
    }
}
