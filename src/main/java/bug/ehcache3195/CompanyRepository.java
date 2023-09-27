package bug.ehcache3195;

import bug.ehcache3195.beans.Company;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 6:14 AM
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
