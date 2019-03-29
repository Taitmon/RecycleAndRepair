package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category
{
    @Id
    private int categoryId;
    private String categoryName;

    public int getCategoryId()
    {
        return categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

}
