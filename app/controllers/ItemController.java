package controllers;

import models.EmployeeDetail;
import models.ItemDetail;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class ItemController extends Controller
{
    private JPAApi db;

    private FormFactory formFactory;

    @Inject
    public ItemController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly=true)
    public Result getItemSearch()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortColumn = form.get("sortColumn");

        if (sortColumn == null)
        {
            sortColumn = "";
        }

        String sortOrder = "";


        switch (sortColumn)
        {
            case "itemIdASC":
                sortOrder = "itemId ASC, itemName";
                break;
            case "itemIdDESC":
                sortOrder = "itemId DESC, itemName";
                break;
            case "itemNameASC":
                sortOrder = "itemName ASC, itemId";
                break;
            case "itemNameDESC":
                sortOrder = "itemName DESC, itemId";
                break;
            case "categoryNameASC":
                sortOrder = "categoryName ASC, itemName";
                break;
            case "categoryNameDESC":
                sortOrder = "categoryName DESC, itemName";
                break;
            case "retailPriceASC":
                sortOrder = "retailPrice ASC, itemName";
                break;
            case "retailPriceDESC":
                sortOrder = "retailPrice DESC, itemName";
                break;
            default:
                sortOrder = "retailPrice";
                break;
        }




        String name = form.get("name");

        if (name == null)
        {
            name = "";
        }

        name = "%" + name + "%";


        TypedQuery<ItemDetail> query =
                db.em().createQuery("SELECT NEW ItemDetail(i.itemId, i.itemName, c.categoryName, i.retailPrice, i.unitPrice) " +
                        "FROM Item i JOIN Category c ON i.categoryId = c.categoryId " +
                        "WHERE itemName LIKE :name " +
                        "ORDER BY " + sortOrder
                        , ItemDetail.class);
        query.setParameter("name", name);
        List<ItemDetail> itemsearch = query.getResultList();

        return ok(views.html.itemsearch.render(itemsearch));
    }
}

//