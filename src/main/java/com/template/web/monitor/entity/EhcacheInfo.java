package com.template.web.monitor.entity;

public class EhcacheInfo {

	private String cacheName;//缓存名称
	private Double cacheHitPercent; //总命中率
	private Long cacheHits; //命中次数
	private Long cacheMisses; //失效次数
	private Long objectCount; //缓存总对象数
	private Long searchesPerSecond; //最后一秒查询完成的执行数
	private Long averageSearchTime; //最后一次采样的平均执行时间
	private Double averageGetTime; //平均获取时间

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public Double getCacheHitPercent() {
		return cacheHitPercent;
	}

	public void setCacheHitPercent(Double cacheHitPercent) {
		this.cacheHitPercent = cacheHitPercent;
	}

	public Long getCacheHits() {
		return cacheHits;
	}

	public void setCacheHits(Long cacheHits) {
		this.cacheHits = cacheHits;
	}

	public Long getCacheMisses() {
		return cacheMisses;
	}

	public void setCacheMisses(Long cacheMisses) {
		this.cacheMisses = cacheMisses;
	}

	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}

	public Long getSearchesPerSecond() {
		return searchesPerSecond;
	}

	public void setSearchesPerSecond(Long searchesPerSecond) {
		this.searchesPerSecond = searchesPerSecond;
	}

	public Long getAverageSearchTime() {
		return averageSearchTime;
	}

	public void setAverageSearchTime(Long averageSearchTime) {
		this.averageSearchTime = averageSearchTime;
	}

	public Double getAverageGetTime() {
		return averageGetTime;
	}

	public void setAverageGetTime(Double averageGetTime) {
		this.averageGetTime = averageGetTime;
	}

}
