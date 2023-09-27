package bug.ehcache3195.beans;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "MH_COMPANY")
@Data
@NoArgsConstructor
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = -159889135750827404L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Version
    public Integer version;

    public String name;

    public Company(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
               "id=" + id +
               ", version=" + version +
               ", name='" + name + '\'' +
               '}';
    }
}
