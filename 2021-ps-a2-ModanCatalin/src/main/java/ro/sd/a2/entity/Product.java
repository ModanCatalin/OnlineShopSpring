package ro.sd.a2.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product
{

    @Id
    private String productId;

    @Column(unique = true)
    private String productName;

    @Column
    private Date creationDate;

    private String productType;

    private String productDescription;

    private int stock;

    private int price;

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "id='" + productId + '\'' +
                ", name='" + productName + '\'' +
                '}';
    }
}
