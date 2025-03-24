package ch.etmles.payroll.exceptions;

public class ResourceIDNotFound extends RuntimeException {
    public ResourceIDNotFound(Long id, String resourceName) {
        super("Could not find " + resourceName + " " + id);
    }
}
