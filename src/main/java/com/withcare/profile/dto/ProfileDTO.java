package com.withcare.profile.dto;

public class ProfileDTO {

	private String id;
	private int cancerIdx;
	private int stageIdx;
	private String profile_photo;
	private String intro;
	private boolean profile_yn;
	private int accessCnt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCancerIdx() {
		return cancerIdx;
	}

	public void setCancerIdx(int cancerIdx) {
		this.cancerIdx = cancerIdx;
	}

	public int getStageIdx() {
		return stageIdx;
	}

	public void setStageIdx(int stageIdx) {
		this.stageIdx = stageIdx;
	}

	public String getProfile_photo() {
		return profile_photo;
	}

	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public boolean isProfile_yn() {
		return profile_yn;
	}

	public void setProfile_yn(boolean profile_yn) {
		this.profile_yn = profile_yn;
	}

	public int getAccessCnt() {
		return accessCnt;
	}

	public void setAccessCnt(int accessCnt) {
		this.accessCnt = accessCnt;
	}

}
