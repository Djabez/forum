package com.danli.common.lang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * PV and UV view objects passed to the front end
 *
 * @author Yicong Wang
 * @date  2022
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class VisitorNum  implements Serializable {
    private static final long serialVersionUID = 1L;
    int uv;
    int pv;
}