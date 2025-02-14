import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ValidatePasswordsTest {

    @Mock
    private FacesContext facesContext;

    @Mock
    private UIComponent component;

    @Mock
    private UIInput otherComponent;

    private ValidatePasswords validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new ValidatePasswords();
    }

    @Test
    public void testValidatePasswordsMatch() {
        when(component.getAttributes().get("otherPasswordID")).thenReturn("otherPassword");
        when(otherComponent.getValue()).thenReturn("password123");
        when(facesContext.getViewRoot().findComponent("otherPassword")).thenReturn(otherComponent);

        assertDoesNotThrow(() -> validator.validate(facesContext, component, "password123"));
    }

    @Test
    public void testValidatePasswordsDoNotMatch() {
        when(component.getAttributes().get("otherPasswordID")).thenReturn("otherPassword");
        when(otherComponent.getValue()).thenReturn("password123");
        when(facesContext.getViewRoot().findComponent("otherPassword")).thenReturn(otherComponent);

        assertThrows(ValidatorException.class, () -> validator.validate(facesContext, component, "password321"));
    }
}
