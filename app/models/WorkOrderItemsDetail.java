package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class WorkOrderItemsDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workOrderItemsId;
    private String  itemName;
    private int quantity;
    private int workOrderId;
    private BigDecimal discount;
    private BigDecimal retailPrice;
    private BigDecimal retailTotal;
    private BigDecimal saleTotal;
    private int itemTotal;
    private int workOrderIdCount;

    //TODO NEED TO ADD ALL COLUMNS TO ENABLE CREATE WORK ORDER PAGE

    public WorkOrderItemsDetail(int workOrderItemsId, String itemName, int quantity, int workOrderId, BigDecimal discount, BigDecimal retailPrice, BigDecimal saleTotal)
    {
        this.workOrderItemsId = workOrderItemsId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.workOrderId = workOrderId;
        this.discount = discount;
        this.retailPrice = retailPrice;
        this.saleTotal = saleTotal;
    }

    public int getWorkOrderItemsId()
    {
        return workOrderItemsId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getWorkOrderId()
    {
        return workOrderId;
    }

    public BigDecimal getDiscount()
    {
        return discount;
    }

    public BigDecimal getRetailPrice()
    {
        return retailPrice;
    }

    public BigDecimal getSaleTotal()
    {


        return saleTotal;
    }

    public BigDecimal getRetailTotal()
    {

        retailTotal = retailPrice.multiply(new BigDecimal(quantity));
        return retailTotal;
    }

    public int getItemTotal()
    {
        itemTotal = quantity * workOrderItemsId;
        return itemTotal;
    }
}
