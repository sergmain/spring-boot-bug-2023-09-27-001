package bug.ehcache3195;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 6:35 AM
 */
@Slf4j
public class Utils {

    public static void checkTxExists() {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            try {
                throw new IllegalStateException("There isn't a Tx");
            }
            catch (IllegalStateException e) {
                log.error("Error", e);
            }
            System.exit(-1);
        }
    }

    public static void checkTxNotExists() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            try {
                throw new IllegalStateException("Tx exists");
            }
            catch (IllegalStateException e) {
                log.error("Error", e);
            }
            System.exit(-1);
        }
    }

}
