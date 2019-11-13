package com.pxd.base.commons.linklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkListNode {

    private int value;

    private LinkListNode before;

    private LinkListNode after;

}
