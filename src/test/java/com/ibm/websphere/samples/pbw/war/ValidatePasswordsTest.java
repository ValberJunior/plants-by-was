package com.ibm.websphere.samples.pbw.war;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class ValidatePasswordsTest {

    private ValidatePasswords validator;
    private FacesContext context;
    private UIInput component;

    @Before
    public void setUp() {
        validator = new ValidatePasswords();
        context = FacesContext.getCurrentInstance();
        component = new UIInput();
    }

    @Test
    public void testValidatePasswordsMatch() {
        component.getAttributes().put("otherPasswordID", "otherPassword");
        UIInput otherComponent = new UIInput();
        otherComponent.setValue("password123");
        context.getViewRoot().getChildren().add(otherComponent);

        try {
            validator.validate(context, component, "password123");
        } catch (ValidatorException e) {
            fail("Validation should pass when passwords match.");
        }
    }

    @Test(expected = ValidatorException.class)
    public void testValidatePasswordsDoNotMatch() {
        component.getAttributes().put("otherPasswordID", "otherPassword");
        UIInput otherComponent = new UIInput();
        otherComponent.setValue("password123");
        context.getViewRoot().getChildren().add(otherComponent);

        validator.validate(context, component, "password321");
    }
}
