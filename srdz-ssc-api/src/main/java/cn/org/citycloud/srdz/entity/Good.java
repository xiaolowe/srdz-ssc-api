package cn.org.citycloud.srdz.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the goods database table.
 * 
 */
@Entity
@Table(name="goods")
@NamedQuery(name="Good.findAll", query="SELECT g FROM Good g")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="goods_id")
	private int goodsId;

	@Column(name="already_sale")
	private int alreadySale;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="auto_down_time")
	private Date autoDownTime;

	@Column(name="brand_id")
	private int brandId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="discount_flg")
	private byte discountFlg;

	@Column(name="flow_template_id")
	private int flowTemplateId;

	@Column(name="goods_add_count")
	private int goodsAddCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="goods_addtime")
	private Date goodsAddtime;

	@Column(name="goods_class_id")
	private int goodsClassId;

	@Column(name="goods_class_name")
	private String goodsClassName;

	@Column(name="goods_class_three_id")
	private int goodsClassThreeId;

	@Column(name="goods_class_three_name")
	private String goodsClassThreeName;

	@Column(name="goods_class_two_id")
	private int goodsClassTwoId;

	@Column(name="goods_class_two_name")
	private String goodsClassTwoName;

	@Lob
	@Column(name="goods_desc")
	private String goodsDesc;

	@Column(name="goods_image")
	private String goodsImage;

	@Column(name="goods_name")
	private String goodsName;

	@Column(name="goods_status")
	private int goodsStatus;

	@Column(name="init_price")
	private BigDecimal initPrice;

	@Column(name="init_sale_count")
	private int initSaleCount;

	@Column(name="is_recommend")
	private int isRecommend;

	@Column(name="member_id")
	private int memberId;

	@Column(name="region_area")
	private int regionArea;

	@Column(name="region_area_name")
	private String regionAreaName;

	@Column(name="region_city")
	private int regionCity;

	@Column(name="region_city_name")
	private String regionCityName;

	@Column(name="region_prov")
	private int regionProv;

	@Column(name="region_prov_name")
	private String regionProvName;

	@Column(name="retail_rates")
	private BigDecimal retailRates;

	@Column(name="sale_price")
	private BigDecimal salePrice;

	@Column(name="saler_flg")
	private byte salerFlg;

	private String standard;

	@Column(name="supplier_id")
	private int supplierId;

	private int surplus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public Good() {
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getAlreadySale() {
		return this.alreadySale;
	}

	public void setAlreadySale(int alreadySale) {
		this.alreadySale = alreadySale;
	}

	public Date getAutoDownTime() {
		return this.autoDownTime;
	}

	public void setAutoDownTime(Date autoDownTime) {
		this.autoDownTime = autoDownTime;
	}

	public int getBrandId() {
		return this.brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public byte getDiscountFlg() {
		return this.discountFlg;
	}

	public void setDiscountFlg(byte discountFlg) {
		this.discountFlg = discountFlg;
	}

	public int getFlowTemplateId() {
		return this.flowTemplateId;
	}

	public void setFlowTemplateId(int flowTemplateId) {
		this.flowTemplateId = flowTemplateId;
	}

	public int getGoodsAddCount() {
		return this.goodsAddCount;
	}

	public void setGoodsAddCount(int goodsAddCount) {
		this.goodsAddCount = goodsAddCount;
	}

	public Date getGoodsAddtime() {
		return this.goodsAddtime;
	}

	public void setGoodsAddtime(Date goodsAddtime) {
		this.goodsAddtime = goodsAddtime;
	}

	public int getGoodsClassId() {
		return this.goodsClassId;
	}

	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public String getGoodsClassName() {
		return this.goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

	public int getGoodsClassThreeId() {
		return this.goodsClassThreeId;
	}

	public void setGoodsClassThreeId(int goodsClassThreeId) {
		this.goodsClassThreeId = goodsClassThreeId;
	}

	public String getGoodsClassThreeName() {
		return this.goodsClassThreeName;
	}

	public void setGoodsClassThreeName(String goodsClassThreeName) {
		this.goodsClassThreeName = goodsClassThreeName;
	}

	public int getGoodsClassTwoId() {
		return this.goodsClassTwoId;
	}

	public void setGoodsClassTwoId(int goodsClassTwoId) {
		this.goodsClassTwoId = goodsClassTwoId;
	}

	public String getGoodsClassTwoName() {
		return this.goodsClassTwoName;
	}

	public void setGoodsClassTwoName(String goodsClassTwoName) {
		this.goodsClassTwoName = goodsClassTwoName;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsImage() {
		return this.goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsStatus() {
		return this.goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public BigDecimal getInitPrice() {
		return this.initPrice;
	}

	public void setInitPrice(BigDecimal initPrice) {
		this.initPrice = initPrice;
	}

	public int getInitSaleCount() {
		return this.initSaleCount;
	}

	public void setInitSaleCount(int initSaleCount) {
		this.initSaleCount = initSaleCount;
	}

	public int getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getRegionArea() {
		return this.regionArea;
	}

	public void setRegionArea(int regionArea) {
		this.regionArea = regionArea;
	}

	public String getRegionAreaName() {
		return this.regionAreaName;
	}

	public void setRegionAreaName(String regionAreaName) {
		this.regionAreaName = regionAreaName;
	}

	public int getRegionCity() {
		return this.regionCity;
	}

	public void setRegionCity(int regionCity) {
		this.regionCity = regionCity;
	}

	public String getRegionCityName() {
		return this.regionCityName;
	}

	public void setRegionCityName(String regionCityName) {
		this.regionCityName = regionCityName;
	}

	public int getRegionProv() {
		return this.regionProv;
	}

	public void setRegionProv(int regionProv) {
		this.regionProv = regionProv;
	}

	public String getRegionProvName() {
		return this.regionProvName;
	}

	public void setRegionProvName(String regionProvName) {
		this.regionProvName = regionProvName;
	}

	public BigDecimal getRetailRates() {
		return this.retailRates;
	}

	public void setRetailRates(BigDecimal retailRates) {
		this.retailRates = retailRates;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public byte getSalerFlg() {
		return this.salerFlg;
	}

	public void setSalerFlg(byte salerFlg) {
		this.salerFlg = salerFlg;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getSurplus() {
		return this.surplus;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}