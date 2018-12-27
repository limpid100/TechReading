package com.dxl.techreading.model;

import com.dxl.techreading.bean.ImageInfo;

import java.util.List;

/**
 * Created by dxl on 2018/12/27 21:44
 */
public interface IDataModel extends IModel {

    List<ImageInfo> getBannerImage();

}
