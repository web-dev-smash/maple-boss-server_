package com.maple.common.boss.domain;

import com.google.common.primitives.Longs;
import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BossFixItem extends BaseEntity {


    private String itemName;

    private int price;

    private int count;

    private String manyCount;

    public BossFixItem(String itemName, int price, int count, String manyCount) {
        checkArgument(Strings.isNotBlank(itemName), "아이템 이름은 공백이 올 수 없습니다.");
        checkArgument(count > 0, "아이템 개수는 0 이상이여야 합니다.");
        checkArgument(Longs.min(price, count) > -1, "음수는 올 수 없습니다.");

        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.manyCount = manyCount;
    }
}
