package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Model
{
    @Id
    private int modelId;
    private String modelName;
    private int manufacturerId;
    private byte[] picture;

    public int getModelId()
    {
        return modelId;
    }

    public String getModelName()
    {
        return modelName;
    }

    public int getManufacturerId()
    {
        return manufacturerId;
    }

    public byte[] getPicture()
    {
        return picture;
    }
}
