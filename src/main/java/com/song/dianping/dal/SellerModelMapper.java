package com.song.dianping.dal;

import com.song.dianping.model.SellerModel;

import java.util.List;

public interface SellerModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 08:26:04 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 08:26:04 CST 2019
     */
    int insert(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 08:26:04 CST 2019
     */
    int insertSelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 08:26:04 CST 2019
     */
    SellerModel selectByPrimaryKey(Integer id);

    List<SellerModel> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 08:26:04 CST 2019
     */
    int updateByPrimaryKeySelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 08:26:04 CST 2019
     */
    int updateByPrimaryKey(SellerModel record);

    int countAllSeller();
}