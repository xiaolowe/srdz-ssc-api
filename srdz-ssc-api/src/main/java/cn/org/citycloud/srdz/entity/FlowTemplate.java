package cn.org.citycloud.srdz.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the flow_template database table.
 * 
 */
@Entity
@Table(name="flow_template")
@NamedQuery(name="FlowTemplate.findAll", query="SELECT f FROM FlowTemplate f")
public class FlowTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="flow_template_id")
	private int flowTemplateId;

	@Column(name="add_weight")
	private BigDecimal addWeight;

	@Column(name="add_weight_price")
	private BigDecimal addWeightPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="default_flag")
	private int defaultFlag;

	@Column(name="flow_price")
	private BigDecimal flowPrice;

	@Column(name="flow_weight")
	private BigDecimal flowWeight;

	@Column(name="free_flow")
	private BigDecimal freeFlow;

	@Column(name="self_count")
	private int selfCount;

	@Column(name="self_total_price")
	private BigDecimal selfTotalPrice;

	@Column(name="single_price")
	private BigDecimal singlePrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public FlowTemplate() {
	}

	public int getFlowTemplateId() {
		return this.flowTemplateId;
	}

	public void setFlowTemplateId(int flowTemplateId) {
		this.flowTemplateId = flowTemplateId;
	}

	public BigDecimal getAddWeight() {
		return this.addWeight;
	}

	public void setAddWeight(BigDecimal addWeight) {
		this.addWeight = addWeight;
	}

	public BigDecimal getAddWeightPrice() {
		return this.addWeightPrice;
	}

	public void setAddWeightPrice(BigDecimal addWeightPrice) {
		this.addWeightPrice = addWeightPrice;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public BigDecimal getFlowPrice() {
		return this.flowPrice;
	}

	public void setFlowPrice(BigDecimal flowPrice) {
		this.flowPrice = flowPrice;
	}

	public BigDecimal getFlowWeight() {
		return this.flowWeight;
	}

	public void setFlowWeight(BigDecimal flowWeight) {
		this.flowWeight = flowWeight;
	}

	public BigDecimal getFreeFlow() {
		return this.freeFlow;
	}

	public void setFreeFlow(BigDecimal freeFlow) {
		this.freeFlow = freeFlow;
	}

	public int getSelfCount() {
		return this.selfCount;
	}

	public void setSelfCount(int selfCount) {
		this.selfCount = selfCount;
	}

	public BigDecimal getSelfTotalPrice() {
		return this.selfTotalPrice;
	}

	public void setSelfTotalPrice(BigDecimal selfTotalPrice) {
		this.selfTotalPrice = selfTotalPrice;
	}

	public BigDecimal getSinglePrice() {
		return this.singlePrice;
	}

	public void setSinglePrice(BigDecimal singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}