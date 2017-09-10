package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jt on 1/26/16.
 */
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;
    private String courseName;
    private String courseSubtitle;

    @Column(length = 2000)
    private String courseDescription;

    @ManyToOne
    private Author author;

    private BigDecimal price;

    @ManyToMany
    private List<ProductCategory> productCategories = new ArrayList<>();

    private String imageUrl;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }
}
