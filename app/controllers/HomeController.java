package controllers;

import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class HomeController
{
    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public HomeController(FormFactory formFactory, JPAApi db)
    {
        this.formFactory = formFactory;
        this.db = db;
    }

    public Result getHome()
    {
        return ok(views.html.home.render());
    }
}
