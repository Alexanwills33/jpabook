package com.example.jpabook.chap6.manytomany.newentity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberProductId implements Serializable {

    private String member; // MemberProduct.member와 연결

    private String product; // MemberProduct.product와 연결

    // hashCod and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemberProductId that = (MemberProductId) o;

        if (!Objects.equals(member, that.member)) {
            return false;
        }
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = member != null ? member.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}