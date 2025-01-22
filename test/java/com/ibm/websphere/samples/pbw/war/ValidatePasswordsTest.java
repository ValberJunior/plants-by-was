// Move to the test/java/com/ibm/websphere/samples/pbw/war folder
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
    public void testValidatePasswordsMatch() {
        component.getAttributes().put("otherPasswordID", "confirmPassword");
        UIInput confirmPasswordComponent = new UIInput();
        confirmPasswordComponent.setValue("password123");
        context.getViewRoot().getChildren().add(confirmPasswordComponent);

        assertDoesNotThrow(() -> validator.validate(context, component, "password123"));
    }

    @Test
    public void testValidatePasswordsDoNotMatch() {
        component.getAttributes().put("otherPasswordID", "confirmPassword");
        UIInput confirmPasswordComponent = new UIInput();
        confirmPasswordComponent.setValue("password123");
        context.getViewRoot().getChildren().add(confirmPasswordComponent);

        assertThrows(ValidatorException.class, () -> validator.validate(context, component, "password321"));
    }
}
