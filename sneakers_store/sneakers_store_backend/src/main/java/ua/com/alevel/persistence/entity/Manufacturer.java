package ua.com.alevel.persistence.entity;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="manufacturers")
public class Manufacturer extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CountryCode factoryPlace;

    public Manufacturer() {
        super();
    }
}
