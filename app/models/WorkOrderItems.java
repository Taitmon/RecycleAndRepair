package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class WorkOrderItems
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workOrderItemsId;
    private int itemId;
    private int quantity;
    private int workOrderId;
    private BigDecimal discount;
    private BigDecimal retailPrice;

    public int getWorkOrderItemsId()
    {
        return workOrderItemsId;
    }

    public void setWorkOrderItemsId(int workOrderItemsId)
    {
        this.workOrderItemsId = workOrderItemsId;
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getWorkOrderId()
    {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId)
    {
        this.workOrderId = workOrderId;
    }

    public BigDecimal getDiscount()
    {
        return discount;
    }

    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }

    public BigDecimal getRetailPrice()
    {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice)
    {
        this.retailPrice = retailPrice;
    }
}
