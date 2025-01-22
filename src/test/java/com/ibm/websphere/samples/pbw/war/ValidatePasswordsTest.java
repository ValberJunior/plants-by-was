import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class ValidatePasswordsTest {
    private ValidatePasswords validator;
    private FacesContext context;
    private UIInput component;

    @BeforeEach
    public void setUp() {
        validator = new ValidatePasswords();
        context = FacesContext.getCurrentInstance();
        component = new UIInput();
    }

    @Test
    public void testMatchingPasswords() {
        component.getAttributes().put("otherPasswordID", "password2");
        component.setValue("password1");
        UIInput otherComponent = new UIInput();
        otherComponent.setValue("password1");
        context.getViewRoot().getChildren().add(otherComponent);

        assertDoesNotThrow(() -> validator.validate(context, component, "password1"));
    }

    @Test
    public void testNonMatchingPasswords() {
        component.getAttributes().put("otherPasswordID", "password2");
        component.setValue("password1");
        UIInput otherComponent = new UIInput();
        otherComponent.setValue("password2");
        context.getViewRoot().getChildren().add(otherComponent);

        assertThrows(ValidatorException.class, () -> validator.validate(context, component, "password1"));
    }
}