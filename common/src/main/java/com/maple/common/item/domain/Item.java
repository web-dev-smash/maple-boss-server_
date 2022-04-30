package com.maple.common.item.domain;

import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 아이템 (기본 메타데이터)
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    /**
     * 아이템 이름
     **/
    @Column(length = 50)
    private String name;

    /**
     * 아이템 부위
     **/
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ItemType type;

    /**
     * 아이템 상태
     **/
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ItemStatus status = ItemStatus.CREATED;

    /**
     * 생성 날짜
     **/
    private OffsetDateTime createAt = OffsetDateTime.now();

    public Item(String name, ItemType type) {
        checkNotNull(type, "타입 필수입니다.");

        checkArgument(Strings.isNotBlank(name), "이름 필수입니다.");

        this.name = name;
        this.type = type;
    }
}
