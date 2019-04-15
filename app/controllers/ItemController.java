package controllers;

import com.google.common.io.Files;
import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class ItemController extends BaseController
{
    private JPAApi db;

    private FormFactory formFactory;

    @Inject
    public ItemController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getItemEdit(int itemId)
    {
        TypedQuery<Item> query = db.em().createQuery("SELECT i FROM Item i WHERE itemId = :itemId", Item.class);
        query.setParameter("itemId", itemId);
        Item item = query.getSingleResult();

        return ok(views.html.itemedit.render(item));
    }


    @Transactional
    public Result postItemEdit(int itemId)
    {
        TypedQuery<Item> query = db.em().createQuery("SELECT i FROM Item i WHERE itemId = :itemId", Item.class);
        query.setParameter("itemId", itemId);
        Item item = query.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String itemName = form.get("itemName");
        int categoryId = Integer.parseInt(form.get("categoryId"));
        BigDecimal retailPrice = new BigDecimal(form.get("retailPrice"));
        BigDecimal unitPrice = new BigDecimal(form.get("unitPrice"));

        //edit attributes
        item.setItemName(itemName);
        item.setCategoryId(categoryId);
        item.setRetailPrice(retailPrice);
        item.setUnitPrice(unitPrice);
        db.em().persist(item);

        //Picture
        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();

        byte[] picture;

        try
        {
            picture = Files.toByteArray(file);

            if (picture != null && picture.length > 0)
            {
                item.setPicture(picture);
            }
        } catch (Exception e)
        {
            picture = null;
        }

        return redirect("/itemsearch");
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

    @Transactional
    public Result getItemAdd()
    {


        TypedQuery<Category> categoryQuery = db.em().createQuery("SELECT c FROM Category c ORDER BY categoryName", Category.class);
        List<Category> categories = categoryQuery.getResultList();

        return ok(views.html.itemadd.render(categories));
    }

    @Transactional
    public Result postItemAdd()
    {

        Item item = new Item();

        DynamicForm form = formFactory.form().bindFromRequest();


        String itemName = form.get("itemName");
        int categoryId = Integer.parseInt(form.get("categoryId"));
        BigDecimal retailPrice = new BigDecimal (form.get("retailPrice"));
        BigDecimal unitPrice = new BigDecimal(form.get("unitPrice"));



        item.setItemName(itemName);
        item.setCategoryId(categoryId);
        item.setRetailPrice(retailPrice);
        item.setUnitPrice(unitPrice);
        db.em().persist(item);

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();

        byte[] picture;

        try
        {
            picture = Files.toByteArray(file);

            if (picture != null && picture.length > 0)
            {
                item.setPicture(picture);
            }
        } catch (Exception e)
        {
            picture = null;
        }



        return redirect("/itemsearch");
    }

}

//