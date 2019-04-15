package controllers;

import models.Customer;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.persistence.TypedQuery;

import static play.mvc.Results.ok;

public class DemoController
{
    @Transactional(readOnly = true)
    public Result getDemo()
    {
        return ok(views.html.DEMO.render());
    }
}
