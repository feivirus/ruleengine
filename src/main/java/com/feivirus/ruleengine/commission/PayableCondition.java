package com.feivirus.ruleengine.commission;

import java.util.List;

import com.feivirus.ruleengine.rule.dto.SceneDetail;

/**
 * 
 * @author feivirus
 *
 */
public class PayableCondition {
    private List<SceneDetail> sceneList;   // 层级        

	public List<SceneDetail> getSceneList() {
		return sceneList;
	}

	public void setSceneList(List<SceneDetail> sceneList) {
		this.sceneList = sceneList;
	}
}
