/**
 *
 */
package com.taylor.common;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author haoli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4966751173897935557L;
    private int pageSize = 20;
    private int pageNum;
    private String sortKey; // order by column
    private String sortOrder; //ASC or DESC
    private int startItemIndex;


    /**
     * @return
     * @see getPageNum()
     */
    public int getCurrentPage() {
        return getPageNum();
    }


    public PageBean(int pageSize, int pageNum, String sortKey, String sortOrder) {
        super();
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.sortKey = sortKey;
        this.sortOrder = sortOrder;
    }

}
