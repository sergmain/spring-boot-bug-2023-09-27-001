package bug.ehcache3195.events;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 5:47 AM
 */
public class NewCompanyTxEvent {
    public String name;

    public NewCompanyTxEvent(String name) {
        this.name = name;
    }

    public NewCompanyEvent to() {
        return new NewCompanyEvent(name);
    }
}
